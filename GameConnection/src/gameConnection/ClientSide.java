package gameConnection;
import game.Game;
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
	String ip;
	Game game;
	
	//The class that handles the threads of the client side of the application
	//p = port number of the server to connect to
	//ma is the main of this application
	//ip is the ip address to connect to
	public ClientSide(int p, Main ma, String ip, Game g) throws IOException {
		//initialize variables needed for the clientside
		this.m = ma;
		clientSocket = new DatagramSocket(p);
		this.ip = ip;
		this.game = g;
		
		//create the threads needed for the input and output of the client
		input = new Thread(new InputThread(clientSocket, this.ip, this.m));
		output = new Thread(new OutputThread(clientSocket, this.m, this.game));
		
		//start the threads
		input.start();
		output.start();
	}
	
	//changes the ip (s) and the port number (p) to which the client has to send packages
	public void changeServerIP(String s, int p) throws IOException{
		clientSocket = new DatagramSocket(p);
		ip = s;
		input.interrupt();		
	}
	
	//returns the ip that the client has to send to
	public String getIP(){
		return ip;
	}
	
	//returns the socket with the portnumber in it that the client has to sent to
	public DatagramSocket getSocket(){
		return clientSocket;
	}
}
