package gameConnection.threads;

import game.Game;
import gameConnection.Main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class ServerOutput implements Runnable{
	Game game;
	String sentence;
	int amountPlayers;
	byte[] sendData;
	DatagramSocket serverSocket;
	Main main;
	ArrayList<InetAddress> IPAddressList;
	ArrayList<Integer> portList;

	public ServerOutput(Game g, DatagramSocket s, Main m){
		game = g;
		amountPlayers = game.getPanel().getPlayersAmount();
		main = m;
		serverSocket = s;
	}


	@Override
	public void run() {
		while(true){
			IPAddressList = main.getIPList();
			portList = main.getPortList();
			if(amountPlayers >= 1){
				sentence = "1-" + game.getPanel().getPlayer1().getX() + "-" + game.getPanel().getPlayer1().getY();
				sendPacket(sentence);
				if(amountPlayers >= 2){
					sentence = "2-" + game.getPanel().getPlayer2().getX() + "-" + game.getPanel().getPlayer2().getY();
					sendPacket(sentence);
					if(amountPlayers >= 3){
						sentence = "3-" + game.getPanel().getPlayer3().getX() + "-" + game.getPanel().getPlayer3().getY();
						sendPacket(sentence);
						if(amountPlayers == 4){
							sentence = "4-" + game.getPanel().getPlayer4().getX() + "-" + game.getPanel().getPlayer4().getY();
							sendPacket(sentence);
						}
					}
				}
			}
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void sendPacket(String s){
		sendData = s.getBytes();
		for (int i = 0; i < IPAddressList.size(); i++) {
			DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, IPAddressList.get(i), portList.get(i));
			try {
				serverSocket.send(sendPacket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}