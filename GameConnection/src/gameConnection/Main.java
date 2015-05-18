package gameConnection;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;


public class Main {

	ArrayList<InetAddress> IPAddressList;
	ArrayList<Integer> portList;
	boolean host;
	ClientSide client;
	ServerSide server;

	public static void main(String[] args) throws Exception {
		Main m = new Main(true);
	}

	public Main(boolean host) throws Exception{
		IPAddressList = new ArrayList<InetAddress>();
		portList = new ArrayList<Integer>();
		client = new ClientSide(44445, this, "localhost");
		this.host = host;
		if(host){
			server = new ServerSide();
		}
	}

	public void becomeServer() throws Exception{
		client.changeServerIP("localhost");
		server = new ServerSide();
	}
	
	public void switchServer(String ip) throws IOException{
		client.changeServerIP(ip);
	}
	
	public void close(){
		server.isRunning = false;
	}
}
