package gameConnection.threads;

import gameConnection.Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class ServerThread implements Runnable{
	
	ArrayList<InetAddress> IPAddressList;
	ArrayList<Integer> portList;
	DatagramSocket serverSocket;
	byte[] receiveData;
	byte[] sendData;
	Client ModelClient;

	public ServerThread() throws IOException{
		serverSocket = new DatagramSocket(9876);
		IPAddressList = new ArrayList<InetAddress>();
		portList = new ArrayList<Integer>();
		ModelClient = new Client();

		serverSocket.setBroadcast(true);
		receiveData = new byte[1024];
		sendData = new byte[1024];
	}
	
	@Override
	public void run(){
		while (!Thread.currentThread().isInterrupted()) {
			DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);

			try {
				serverSocket.receive(receivePacket);
			} catch (IOException e) {
				e.printStackTrace();
			}

			ModelClient.setIPAddress(receivePacket.getAddress());
			ModelClient.setPort(receivePacket.getPort());

			if(IPAddressList.contains(ModelClient.getIPAddress()) && portList.contains(ModelClient.getPort())){

			}else{
				IPAddressList.add(ModelClient.getIPAddress());
				portList.add(ModelClient.getPort());
			}

			String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());

			System.out.println("data received: " + sentence);

			System.out.println("Port: " + ModelClient.getPort());
			System.out.println("Ip address" + ModelClient.getIPAddress());

			sendData = sentence.getBytes();
			System.out.println("");

			for (int i = 0; i < IPAddressList.size(); i++) {
				System.out.println("Client list " + (i+1) +" : "+IPAddressList.get(i)+ " " + portList.get(i));
				DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, IPAddressList.get(i), portList.get(i));
				try {
					serverSocket.send(sendPacket);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println();

		}
		if(IPAddressList.size() > 1){			
			String sentence = "host";
			sendData = sentence.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, IPAddressList.get(1), portList.get(1));
			try {
				serverSocket.send(sendPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(IPAddressList.size() > 2){
				for (int i = 2; i < IPAddressList.size(); i++) {
					sentence = "change-"+IPAddressList.get(1).toString();
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
