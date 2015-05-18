package client.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

import client.ClientMain;
import client.communication.Communication;

public class UIHandler extends WindowAdapter implements ActionListener{
	LoginUI login;
	RegisterUI register;
	Communication com;
	LobbyUI lobby;
	GameLobbyUI gameLobby;
	ClientMain client;

	Component[] componentsLogin;
	Component[] componentsRegister;
	Component[] componentsLobby;
	Component[] componentsGameLobby;
	JButton ButtonSignIn;
	JButton ButtonExit;
	JButton ButtonSignUpLogin;
	JButton ButtonSignUpRegister;
	JButton ButtonRefreshLobby;
	JButton ButtonCreateRoom;
	JButton ButtonLogoutLobby;
	JButton ButtonStart;
	JButton ButtonRefreshGameLobby;
	JButton ButtonLeaveGameLobby;

	public UIHandler(ClientMain client){
		this.client = client;
	}

	public void setup(LoginUI login, Communication com, RegisterUI register, LobbyUI lobby, GameLobbyUI gameLobby){
		this.login = login;
		this.register = register;
		this.lobby = lobby;
		this.gameLobby = gameLobby;
		this.com = com;
		componentsLogin = login.getContentPane().getComponents();
		componentsRegister = register.getContentPane().getComponents();
		componentsLobby = lobby.getPanel().getComponents();
		componentsGameLobby = gameLobby.getContentPane().getComponents();
		for(int i = 0; i < componentsLogin.length; i++){
			if(componentsLogin[i].getClass() == JButton.class){
				JButton btn = (JButton)componentsLogin[i];
				switch(btn.getText()){
				case "Sign In":
					ButtonSignIn = btn;
					break;
				case "Sign Up":
					ButtonSignUpLogin = btn;
					break;
				case "Exit":
					ButtonExit = btn;
					break;
				}
			}
		}
		for(int i = 0; i < componentsRegister.length; i++){
			if(componentsRegister[i].getClass() == JButton.class){
				JButton btn = (JButton)componentsRegister[i];
				ButtonSignUpRegister = btn;
			}
		}
		for(int i = 0; i < componentsLobby.length; i++){
			if(componentsLobby[i].getClass() == JButton.class){
				JButton btn = (JButton)componentsLobby[i];
				switch(btn.getText()){
				case "Create":
					ButtonCreateRoom = btn;
					break;
				case "Refresh":
					ButtonRefreshLobby = btn;
					break;
				case "Logout":
					ButtonLogoutLobby = btn;
					break;
				}
			}
		}
		for(int i = 0; i < componentsGameLobby.length; i++){
			if(componentsGameLobby[i].getClass() == JButton.class){
				JButton btn = (JButton)componentsGameLobby[i];
				switch(btn.getText()){
				case "Start":
					ButtonStart = btn;
					break;
				case "Leave":
					ButtonLeaveGameLobby = btn;
					break;
				case "Refresh":
					ButtonRefreshGameLobby = btn;
					break;
				}
			}
		}
	}	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == ButtonSignIn){
			if(com.loginUser(login.getUsername(), login.getPassword())){
				client.setUser(login.getUsername(), login.getPassword());
				login.setResponse("Login Succesful!");
				lobby.setVisible(true);
				login.setVisible(false);
				refreshLobby();
			}else{
				login.setResponse("Username and/or password incorrect!");
			}
		}
		if(e.getSource() == ButtonExit){
			login.dispatchEvent(new WindowEvent(login, WindowEvent.WINDOW_CLOSING));
		}
		if(e.getSource() == ButtonSignUpLogin){
			register.setVisible(true);
		}
		if(e.getSource() == ButtonSignUpRegister){
			String response = com.registerUser(register.getUsername(), register.getPassword());
			register.setResponse(response);
		}
		if(e.getSource() == ButtonRefreshLobby){
			refreshLobby();
		}
		if(e.getSource() == ButtonLogoutLobby){
			lobby.setVisible(false);
			login.setVisible(true);
			client.setUser("", "");
		}
		if(e.getSource() == ButtonCreateRoom){
			if(!lobby.getTextFieldGameName().equals("")){
				if(createRoom(lobby.getTextFieldGameName())){
					client.setGameName(lobby.getTextFieldGameName());
					gameLobby.setPlayerNames(client.getUsername(), "empty", "empty", "empty");
					gameLobby.setVisible(true);
				}
			}						
		}
		if(e.getSource() == ButtonRefreshGameLobby){
			refreshGameLobby(client.getGameName());
		}
		if(e.getSource() == ButtonLeaveGameLobby){
			com.leaveRoom(client.getUsername(), client.getPassword(), client.getGameName());
			client.setGameName("");
			gameLobby.setVisible(false);
			refreshLobby();
			lobby.setVisible(true);
		}
		
	}

	private boolean createRoom(String gameName){
		if(!com.createRoom(client.getUsername(), client.getPassword(), gameName)){
			lobby.sendError("Could not create game!");
			return false;
		}
		lobby.setVisible(false);
		return true;
	}
	
	public void joinRoom(String gameName){
		if(com.joinRoom(client.getUsername(), client.getPassword(), gameName)){
			client.setGameName(gameName);
			refreshGameLobby(gameName);
			lobby.setVisible(false);
		}		
	}
	
	private void refreshGameLobby(String gameName){
		List<String> players = new ArrayList<String>();
		players = com.refreshRoom(gameName);
		switch(players.size()){
		case 1:
			gameLobby.setPlayerNames(players.get(0), "empty", "empty", "empty");
			break;
		case 2:
			gameLobby.setPlayerNames(players.get(0), players.get(1), "empty", "empty");
			break;
		case 3:
			gameLobby.setPlayerNames(players.get(0), players.get(1), players.get(2), "empty");
			break;
		case 4:
			gameLobby.setPlayerNames(players.get(0), players.get(1), players.get(2), players.get(3));
			break;
		}
		gameLobby.setVisible(true);
	}
	
	private void refreshLobby(){
		List<String> gameNames = new ArrayList<String>();
		List<String> players = new ArrayList<String>();
		String gameName;
		int amountPlayers;
		String host;
		String status;
		String join;
		gameNames = com.refreshLobby();
		lobby.clearRows();
		if(gameNames != null){
			for(int i = 0; i < gameNames.size(); i++){
				gameName = gameNames.get(i);
				players = com.refreshRoom(gameName);
				host = players.get(0);
				amountPlayers = players.size();
				if(amountPlayers<4){
					status = "Waiting";
					join = "Join";
				}else{
					status = "Full";
					join = "-";
				}
				lobby.addRow(gameName, amountPlayers, host, status, join);
			}
		}
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		if(lobby.isVisible()){
			lobby.setVisible(false);
			login.setVisible(true);
			client.setUser("", "");
		}
		if(gameLobby.isVisible()){
			com.leaveRoom(client.getUsername(), client.getPassword(), client.getGameName());
			client.setGameName("");
			gameLobby.setVisible(false);
			refreshLobby();
			lobby.setVisible(true);
		}
	}

}
