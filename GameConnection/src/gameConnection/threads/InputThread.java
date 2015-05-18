package gameConnection.threads;

import gameConnection.Main;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class InputThread extends WindowAdapter implements Runnable{
	BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
	String sentence = "";
	byte[] sendData = new byte[1024];
	byte[] receiveData = new byte[1024];
	DatagramSocket clientSocket;
	InetAddress IPAddress;
	JFrame frame;
	Main m;


	public InputThread(DatagramSocket d, String ip, Main ma) throws IOException {
		clientSocket = d;
		IPAddress = InetAddress.getByName(ip);
		this.m = ma;

		frame = new JFrame("Key Listener client");

		Container contentPane = frame.getContentPane();

		KeyListener listener = new KeyListener() {

			@Override
			public void keyPressed(KeyEvent event) {
				sendPacket(String.valueOf(event.getKeyCode()));
			}

			@Override
			public void keyReleased(KeyEvent event) {

			}

			@Override
			public void keyTyped(KeyEvent e) {

			}
		};

		JTextField textField = new JTextField();
		textField.addKeyListener(listener);
		contentPane.add(textField, BorderLayout.NORTH);
		frame.pack();
		frame.setVisible(true);
		frame.addWindowListener(this);
		sendPacket("");		
	}

	public void run(){
		sendPacket("");
	}

	
	private void sendPacket(String s){
		sentence = s;
		sendData = sentence.getBytes();
		DatagramPacket sendEmptyPacket = new DatagramPacket(sendData,sendData.length, IPAddress, 9876);
		try{
			clientSocket.send(sendEmptyPacket);
		}catch(Exception e){

		}
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		if(m.getHost()){
			m.close();
			sendPacket("");
		}else{
			sendPacket("disconnect");
		}
		System.exit(0);
	}
	
}