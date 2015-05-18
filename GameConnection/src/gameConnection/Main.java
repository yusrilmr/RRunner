package gameConnection;

import gameConnection.threads.ServerThread;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;


public class Main {

	ArrayList<InetAddress> IPAddressList;
	ArrayList<Integer> portList;
	boolean host;
	ClientSide client;
	Thread server;
	static Main m;

	public static void main(String[] args) throws Exception {
		m = new Main(true);
	}

	public Main(boolean host) throws Exception{
		IPAddressList = new ArrayList<InetAddress>();
		portList = new ArrayList<Integer>();
		client = new ClientSide(44445, this, "localhost");
		this.host = host;
		if(host){
			server = new Thread(new ServerThread());
			server.start();
		}
	}

	public void becomeServer() throws Exception{
		client.changeServerIP("localhost");
		server  = new Thread(new ServerThread());
		server.start();
	}
	
	public void switchServer(String ip) throws IOException{
		client.changeServerIP(ip);
	}
	
	public void close(){
			server.interrupt();
	}
	
	public boolean getHost(){
		return host;
	}
}
