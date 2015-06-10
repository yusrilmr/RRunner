package gameConnection.threads;

import game.Game;
import gameConnection.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

//the thread that handles the messages being sent from the server and outputs them to the client
public class OutputThread implements Runnable{
	//the string that will contain the message to send
	String sentence = "";
	//byte arrays for sending and receiving data
	byte[] sendData = new byte[1024];
	byte[] receiveData = new byte[1024];
	//info needed for sending
	DatagramSocket clientSocket;
	Main m;
	Game game;

	//constructor
	public OutputThread(DatagramSocket d, Main m, Game g) throws IOException{
		clientSocket = d;
		this.m = m;
		this.game = g;
	}

	//the method that is called when the thread is started
	public void run() {
		//always keep listening on the socket to see if a message is received
		while (true) {
			if(Thread.interrupted()){
				clientSocket = m.getSocket();
			}
			try {
				DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
				clientSocket.receive(receivePacket);
				String modifiedSentence = new String(receivePacket.getData(), 0,receivePacket.getLength());
				//as long as the message isn't empty
				if(!modifiedSentence.equals("")){
					//if the message is equal to host then it means that this client has to become the host
					if(modifiedSentence.equals("host")){
						System.out.println("FROM SERVER:" + modifiedSentence);
						m.becomeServer();
					}else{
						//split the string to gather the data from the package
						String[] parts = modifiedSentence.split("-");
						//if the first part is "change", then it means the server has changed ip or port
						//and the client has to change to these new values
						//which will also be sent in this package
						if(parts[0].equals("change")){						
							parts[1] = parts[1].substring(1, parts[1].length() - 1);
							System.out.println("FROM SERVER:" + parts[0] + parts[1]);		
							m.switchServer(parts[1], Integer.parseInt(parts[2]));						
						}else{
							//print the data from the package
							if(parts[0].equals("1")){
								game.getPanel().getPlayer1().setx(Double.valueOf(parts[1]).intValue());
								game.getPanel().getPlayer1().sety(Double.valueOf(parts[2]).intValue());
							}else{
								if(parts[0].equals("2")){
									game.getPanel().getPlayer2().setx(Double.valueOf(parts[1]).intValue());
									game.getPanel().getPlayer2().sety(Double.valueOf(parts[2]).intValue());
								}
							}
						}
					}
				}
			} catch (Exception e) {

			}
		}
	}
}
