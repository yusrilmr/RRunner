package gameConnection.threads;

import gameConnection.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class OutputThread implements Runnable{
	BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
	String sentence = "";
	byte[] sendData = new byte[1024];
	byte[] receiveData = new byte[1024];
	DatagramSocket clientSocket;
	Main m;


	public OutputThread(DatagramSocket d, Main m) throws IOException{
		clientSocket = d;
		this.m = m;
	}

	public void run() {

		while (true) {

			try {
				DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
				clientSocket.receive(receivePacket);
				String modifiedSentence = new String(receivePacket.getData(), 0,receivePacket.getLength());
				if(!modifiedSentence.equals("")){
					if(modifiedSentence.equals("host")){
						System.out.println("FROM SERVER:" + modifiedSentence);
						m.becomeServer();
					}
					String[] parts = modifiedSentence.split("-");
					if(parts[0].equals("change")){
						System.out.println("FROM SERVER:" + modifiedSentence);
						m.switchServer(parts[1]);
					}
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
