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
	
	public ClientSide(int p, Main ma, String ip) throws IOException {
		
		//send and receive empty packet to give IP and port
		this.m = ma;
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		InetAddress IPAddress;
		clientSocket = new DatagramSocket(p);
		
		input = new Thread(new InputThread(clientSocket, ip, this.m));
		output = new Thread(new OutputThread(clientSocket, this.m));
		
		input.start();
		output.start();
	}
	
	public void changeServerIP(String s) throws IOException{
		input.stop();
		input = new Thread(new InputThread(clientSocket, s, m));
		input.start();
	}
}
