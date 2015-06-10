package gameConnection;

import game.Game;
import gameConnection.threads.ServerThread;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

//the main class that overlooks the client and server side of the application
public class Main {
	//check to see whether this instance is a host or not
	boolean host;
	//the client side
	ClientSide client;
	//the server thread
	Thread server;
	static Main m;
	//port number
	int port = 44444;
	Game game;

	//standard main that creates an instance
	public static void main(String[] args) throws Exception {
		m = new Main(true);
	}

	//constructor. The host boolean determines whether this instance is going to be a host or not
	public Main(boolean host) throws Exception{
		game = new Game();
		client = new ClientSide(port, this, "localhost", game);
		this.host = host;
		//if this is a host, then start the server thread
		if(host){
			server = new Thread(new ServerThread(game));
			server.start();
		}
	}

	//changes this instance to host,
	//starts server thread,
	//changes the ip and port to which the client has to connect to itself
	public void becomeServer() throws Exception{
		host = true;
		server = new Thread(new ServerThread(game));
		server.start();
		client.changeServerIP("localhost", port);
	}
	
	//changes the ip and port of the client to a new host
	public void switchServer(String ip, int p) throws IOException{
		System.out.println(ip + " " + p);
		client.changeServerIP(ip, p);
	}
	
	//interrupts the server so that the host can be switched
	public void close(){
		server.interrupt();
	}
	
	//returns whether its a host or not
	public boolean getHost(){
		return host;
	}
	
	//returns the ip to connect to
	public String getIP(){
		return client.getIP();
	}
	
	//returns the socket with the portnumber to connect to
	public DatagramSocket getSocket(){
		return client.getSocket();
	}
}
