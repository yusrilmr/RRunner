package gameConnection;
import gameConnection.threads.InputThread;
import gameConnection.threads.OutputThread;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class ClientSide {
	Thread input;
	Thread output;
	DatagramSocket clientSocket;
	Main m;
	
	public ClientSide(int p, Main m, String ip) throws IOException {
		
		//send and receive empty packet to give IP and port
		this.m = m;
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		InetAddress IPAddress;
		clientSocket = new DatagramSocket(p);
		
		input = new Thread(new InputThread(clientSocket, ip, m));
		output = new Thread(new OutputThread(clientSocket, m));
		
		input.start();
		output.start();
	}
	
	public void changeServerIP(String s) throws IOException{
		input = new Thread(new InputThread(clientSocket, s, m));
	}
}
