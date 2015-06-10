package gameConnection.threads;

import game.Game;
import gameConnection.Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class ServerThread implements Runnable{
	//lists for the clients to be put in
	ArrayList<InetAddress> IPAddressList;
	ArrayList<Integer> portList;
	//the socket where the host listens
	DatagramSocket serverSocket;
	//byte arrays for sending and receiving data
	byte[] receiveData;
	byte[] sendData;
	Client ModelClient;
	Game game;

	//constructor
	public ServerThread(Game g) throws IOException{
		//initialize sockets and other data
		System.out.println("Server Loading");
		serverSocket = new DatagramSocket(9876);
		IPAddressList = new ArrayList<InetAddress>();
		portList = new ArrayList<Integer>();
		ModelClient = new Client();
		
		game = g;
		serverSocket.setBroadcast(true);
		receiveData = new byte[1024];
		sendData = new byte[1024];
		System.out.println("Loading finished");
	}

	@Override
	public void run(){
		System.out.println("Server Running");
		while (!Thread.currentThread().isInterrupted()) {
			//wait for a packet to arrive//
			//---------------------------------------------------------//
			DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
			try {
				serverSocket.receive(receivePacket);
			} catch (IOException e) {
				e.printStackTrace();
			}

			//receive packet and identify client//
			//if client is a new one, add it to the list//
			//---------------------------------------------------------//
			ModelClient.setIPAddress(receivePacket.getAddress());
			ModelClient.setPort(receivePacket.getPort());
			if(!(IPAddressList.contains(ModelClient.getIPAddress()) && portList.contains(ModelClient.getPort()))){
				IPAddressList.add(ModelClient.getIPAddress());
				portList.add(ModelClient.getPort());
			}

			//process the content of the packet//
			//if the packet says "disconnect", then remove this client from the list//
			//else the packet gets added the player number from who its from//
			//---------------------------------------------------------//
			String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
			System.out.println("data received: " + sentence);
			for(int i = 0; i < IPAddressList.size(); i++){
				if(IPAddressList.get(i).equals(receivePacket.getAddress()) && portList.get(i).equals(receivePacket.getPort())){
					if(sentence.equals("disconnect")){
						IPAddressList.remove(i);
						portList.remove(i);
					}else{
						sentence = sentence + "-" + (i+1);
						String[] parts = sentence.split("-");
						if(!parts[0].equals("")){
							if(parts[1].equals("1")){
								switch(Integer.parseInt(parts[0])){
								case 39: 
									game.getPanel().getPlayer1().setRight(true);
									game.getPanel().update();
									game.getPanel().getPlayer1().setRight(false);
									break;
								case 37:
									game.getPanel().getPlayer1().setLeft(true);
									game.getPanel().update();
									game.getPanel().getPlayer1().setLeft(false);
									break;
								case 38:
									game.getPanel().getPlayer1().setJumping(true);
									break;
								}
								sentence = "1-" + game.getPanel().getPlayer1().getX() + "-" + game.getPanel().getPlayer1().getY();
							}else{
								switch(Integer.parseInt(parts[0])){
								case 39: 
									game.getPanel().getPlayer2().setRight(true);
									game.getPanel().update();
									game.getPanel().getPlayer2().setRight(false);
									break;
								case 37:
									game.getPanel().getPlayer2().setLeft(true);
									game.getPanel().update();
									game.getPanel().getPlayer2().setLeft(false);
									break;
								case 38:
									game.getPanel().getPlayer2().setJumping(true);
									break;
								}
								sentence = "2-" + game.getPanel().getPlayer2().getX() + "-" + game.getPanel().getPlayer2().getY();
							}
						}
						
					}
				}
			}
			
			//turn the string of the packet into a send able packet//
			//send it to all the clients in the list//
			//---------------------------------------------------------//
			sendData = sentence.getBytes();
			for (int i = 0; i < IPAddressList.size(); i++) {
				System.out.println("Player: " + (i+1));
				DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, IPAddressList.get(i), portList.get(i));
				try {
					serverSocket.send(sendPacket);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		//this code is reached whenever the host closes his client
		//if there are is just himself, then this IF will be skipped and nothing will happen
		//it means the host is the last player, so the program may quit
		if(IPAddressList.size() > 1){
			//there is at least one other player.
			//this means that the next player in the list will become the host
			//a package of "host" will be sent to him
			String sentence = "host";
			sendData = sentence.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, IPAddressList.get(1), portList.get(1));
			try {
				serverSocket.send(sendPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(IPAddressList.size() > 2){
				//if there are more than just the two, then all other clients will be notified
				//to change their ip and port to the new host
				for (int i = 2; i < IPAddressList.size(); i++) {
					sentence = "change-"+IPAddressList.get(1).toString()+"-"+portList.get(1).toString();
					sendData = sentence.getBytes();
					sendPacket = new DatagramPacket(sendData,sendData.length, IPAddressList.get(i), portList.get(i));
					try {
						serverSocket.send(sendPacket);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("There is at least one other");
			}
			System.out.println("There is only one other");
		}
		System.out.println("There is only me");		
	}

}
