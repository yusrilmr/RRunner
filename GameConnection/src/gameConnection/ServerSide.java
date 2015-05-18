package gameConnection;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import gameConnection.Client;

class ServerSide {
	ArrayList<InetAddress> IPAddressList;
	ArrayList<Integer> portList;
	public boolean isRunning = true;

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

		while (isRunning) {
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

			System.out.println("Port: " + ModelClient.getPort());
			System.out.println("Ip address" + ModelClient.getIPAddress());

			sendData = sentence.getBytes();
			System.out.println("");

			for (int i = 0; i < IPAddressList.size(); i++) {
				System.out.println("Client list " + (i+1) +" : "+IPAddressList.get(i)+ " " + portList.get(i));
				DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, IPAddressList.get(i), portList.get(i));
				serverSocket.send(sendPacket);
			}
			System.out.println();

		}
		if(IPAddressList.size() > 1){
			String sentence = "host";
			sendData = sentence.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, IPAddressList.get(1), portList.get(1));
			serverSocket.send(sendPacket);
			if(IPAddressList.size() > 2){
				for (int i = 2; i < IPAddressList.size(); i++) {
					sentence = "change-"+IPAddressList.get(1).toString();
					sendData = sentence.getBytes();
					sendPacket = new DatagramPacket(sendData,sendData.length, IPAddressList.get(i), portList.get(i));
					serverSocket.send(sendPacket);
				}
			}
		}



	}
}