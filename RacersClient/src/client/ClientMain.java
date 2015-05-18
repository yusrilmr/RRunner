package client;

import javax.swing.JOptionPane;

import client.communication.Communication;
import client.ui.GameLobbyUI;
import client.ui.LobbyUI;
import client.ui.LoginUI;
import client.ui.RegisterUI;
import client.ui.UIHandler;

public class ClientMain {
	Communication com;
	UIHandler uiHandler;
	LoginUI loginUI;
	RegisterUI register;
	LobbyUI lobby;
	GameLobbyUI gameLobby;
	String username;
	String password;
	String serverIP;
	String gameName;

	public static void main(String[] args) {
		ClientMain client = new ClientMain();
	}

	public ClientMain(){
		setServerIP(askIP());
		com = new Communication(this);
		uiHandler = new UIHandler(this);
		loginUI = new LoginUI(uiHandler);
		register = new RegisterUI(uiHandler);
		lobby = new LobbyUI(uiHandler);
		gameLobby = new GameLobbyUI(uiHandler);
		uiHandler.setup(loginUI, com, register, lobby, gameLobby);
		//com.registerUser("teun", "sinkeldam");
		//com.registerUser("joey", "teuns");
		//com.loginUser("teun", "sinkeldam");
	}

	public void setUser(String username, String password){
		this.username = username;
		this.password = password;
	}

	public String getUsername(){
		return username;
	}

	public String getPassword(){
		return password;
	}

	public String getServerIP(){
		return serverIP;
	}
	
	public String getGameName(){
		return gameName;
	}
	
	public void setGameName(String s){
		gameName = s;
	}

	private void setServerIP(String s){
		serverIP = "http://" + s + ":8088/";
		System.out.println(serverIP);
	}

	private String askIP(){
		String s = (String)JOptionPane.showInputDialog(null,"Fill in the IP of the server you want to connect to: ",JOptionPane.PLAIN_MESSAGE);
		return s;
	}

}
