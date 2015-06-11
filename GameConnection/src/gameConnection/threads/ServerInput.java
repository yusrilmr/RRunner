package gameConnection.threads;

import game.Game;
import game.Player;
import gameConnection.Client;
import gameConnection.Main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class ServerInput implements Runnable{
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
	Main main;

	//constructor
	public ServerInput(Game g, DatagramSocket s, Main m) throws IOException{
		//initialize sockets and other data
		main = m;
		System.out.println("Server Loading");
		serverSocket = s;
		ModelClient = new Client();
		
		game = g;
		
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
			IPAddressList = main.getIPList();
			portList = main.getPortList();
			ModelClient.setIPAddress(receivePacket.getAddress());
			ModelClient.setPort(receivePacket.getPort());
			if(!(IPAddressList.contains(ModelClient.getIPAddress()) && portList.contains(ModelClient.getPort()))){
				String sentence = "number-" + IPAddressList.size();
				sendData = sentence.getBytes();
				DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, receivePacket.getAddress(), receivePacket.getPort());
				try {
					serverSocket.send(sendPacket);
				} catch (IOException e) {
					e.printStackTrace();
				}
				main.addIP(ModelClient.getIPAddress());
				main.addPort(ModelClient.getPort());
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
						if(sentence.contains("-")){
							String[] parts = sentence.split("-");
							if(!parts[1].equals("")){
								int move = Integer.parseInt(parts[1]);
								if(parts[0].equals("1")){
									updatePlayer(game.getPanel().getPlayer1(), move);								
								}
								if(parts[0].equals("2")){
									updatePlayer(game.getPanel().getPlayer2(), move);								
								}
								if(parts[0].equals("3")){
									updatePlayer(game.getPanel().getPlayer3(), move);								
								}
								if(parts[0].equals("4")){
									updatePlayer(game.getPanel().getPlayer4(), move);								
								}							
							}
						}												
					}
				}
			}
		}
		//---------------------------------------------------------//
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
	
	private void updatePlayer(Player p, int x){
		switch(x){
		case 39: 
			p.setRight(true);
			game.getPanel().update();
			p.setRight(false);
			break;
		case 37:
			p.setLeft(true);
			game.getPanel().update();
			p.setLeft(false);
			break;
		case 38:
			p.setJumping(true);
			break;
		}
	}
}
