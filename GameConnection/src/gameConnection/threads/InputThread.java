package gameConnection.threads;

import game.Player;
import game.Player2;
import game.TileMap;
import gameConnection.Main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JTextField;

//the thread that will handle the input of the user and send it to the host
public class InputThread extends WindowAdapter implements Runnable{
	//the string that will contain the message to send
	String sentence = "";
	//byte arrays for sending and receiving data
	byte[] sendData = new byte[1024];
	byte[] receiveData = new byte[1024];
	//info needed for sending
	DatagramSocket clientSocket;
	InetAddress IPAddress;
	JFrame frame;
	Main m;
	int port = 9876;
	boolean sendRight = false;
	boolean sendLeft = false;

	//constructor
	public InputThread(DatagramSocket d, String ip, Main ma) throws IOException {
		//initialization
		clientSocket = d;
		IPAddress = InetAddress.getByName(ip);
		this.m = ma;

		//create a JFrame that can hold an object to put a key listener on
		frame = new JFrame("Key Listener client");
		Container contentPane = frame.getContentPane();
		KeyListener listener = new KeyListener() {

			//whenever a key is pressed, send a packet with that keycode
			@Override
			public void keyPressed(KeyEvent event) {
				switch(event.getKeyCode()){
				case 37:
					sendLeft = true;
					break;
				case 39:
					sendRight = true;
					break;
				case 38:
					sendPacket(String.valueOf(38));
					break;
				}
			}

			//override needed for the listener
			@Override
			public void keyReleased(KeyEvent event) {
				if(event.getKeyCode() == 37){
					sendLeft = false;
				}else{
					if(event.getKeyCode() == 39){
						sendRight = false;
					}
				}
			}

			//override needed for the listener
			@Override
			public void keyTyped(KeyEvent e) {

			}
		};

		//the textfield with the listener
		JTextField textField = new JTextField();
		textField.addKeyListener(listener);
		contentPane.add(textField, BorderLayout.NORTH);
		frame.pack();
		frame.setVisible(true);
		frame.addWindowListener(this);

		//when done initializing the frame, send an empty packet to let the server know that you're there
		sendPacket("");		
	}

	//the method that is called when the thread is started
	public void run(){
		while(true){
			if(sendRight){
				sendPacket(String.valueOf(39));
			}
			if(sendLeft){
				sendPacket(String.valueOf(37));
			}
			//as long as it is not interrupted, keep waiting
			//if it is interrupted, it means it needs to get new ip and port numbers
			//after Thread.interrupted() the interrupted flag is reset so that it
			//can be called again
			if(Thread.interrupted()){
				clientSocket = m.getSocket();
				try {
					IPAddress = InetAddress.getByName(m.getIP());
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
	}


	//sends a packet with string s as contents to the server
	private void sendPacket(String s){
		sentence = s;
		sendData = sentence.getBytes();
		DatagramPacket sendEmptyPacket = new DatagramPacket(sendData,sendData.length, IPAddress, port);
		try{
			clientSocket.send(sendEmptyPacket);
		}catch(Exception e){

		}
	}

	//window event listener to execute code when someone closes their client
	@Override
	public void windowClosing(WindowEvent e) {
		//if this client was also the host, then call the main 
		//to handle host switching
		if(m.getHost()){
			m.close();
			sendPacket("");
			//if this client was not the host, then just send disconnect to remove the client
		}else{
			sendPacket("disconnect");
		}
		System.exit(0);
	}

}