package gameConnection;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import gameConnection.Client;

class ServerSide {
	ArrayList<InetAddress> IPAddressList;
	ArrayList<Integer> portList;
	
	public ServerSide() throws Exception {
		start();
	}
	
	public void start() throws IOException{
		DatagramSocket serverSocket = new DatagramSocket(9876);
		IPAddressList = new ArrayList<InetAddress>();
		portList = new ArrayList<Integer>();
		Client ModelClient = new Client();

		serverSocket.setBroadcast(true);
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];

		while (true) {
			DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);

			serverSocket.receive(receivePacket);
			
			ModelClient.setIPAddress(receivePacket.getAddress());
			ModelClient.setPort(receivePacket.getPort());
			
			if(IPAddressList.contains(ModelClient.getIPAddress()) && portList.contains(ModelClient.getPort())){
					
			}else{
				IPAddressList.add(ModelClient.getIPAddress());
				portList.add(ModelClient.getPort());
			}
			
			String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
			
			System.out.println("data received: " + sentence);
			
			sendData = sentence.getBytes();
			
			for (int i = 0; i < IPAddressList.size(); i++) {
				System.out.println("Player: " + (i+1));
				DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, IPAddressList.get(i), portList.get(i));
				serverSocket.send(sendPacket);
			}
			System.out.println();

		}
	}
}