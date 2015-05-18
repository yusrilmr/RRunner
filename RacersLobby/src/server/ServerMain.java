package server;

import java.util.ArrayList;
import java.util.List;

import server.database.DatabaseHandler;
import server.service.LobbyService;

public class ServerMain {
	List<User> loggedInUsers = new ArrayList<User>();
	List<Game> games = new ArrayList<Game>();
	DatabaseHandler database;

	static ServerMain server = null;

	public static void main(String[] args) {
		ServerMain server = new ServerMain();
		
	}

	public static ServerMain getServer(){
		if (server == null){
			server = new ServerMain();
		}
		return server;
	}

	public ServerMain(){
		LobbyService lobbyService = new LobbyService();
		System.out.println("hoi34");
		database = new DatabaseHandler();
		System.out.println("hoi1111");
	}

	//Register a user
	public boolean register(String username, String password){
		if(!username.equals("empty")){
			if(!database.isUserThere(username)){
				database.instertUser(username, password);
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	//Check whether the username and password match an entry in the arraylist that is stored
	public boolean checkLogin(String username, String password){
		User teun = new User(username, password);
		if(database.isUserThere(username, password)){
			loggedInUsers.add(teun);
			return true;
		}else{
			return false;
		}
	}

	public void logOut(String username, String password){
		User teun = getLoggedInUser(username, password);
		if(teun.equals(null)){
			return;
		}else{
			loggedInUsers.remove(teun);
		}
	}

	private User getLoggedInUser(String username, String password){
		for(int i = 0; i < loggedInUsers.size(); i++){
			if(loggedInUsers.get(i).getUsername().equals(username) && loggedInUsers.get(i).getPassword().equals(password)){
				return loggedInUsers.get(i);
			}
		}
		return null;
	}

	public boolean createGame(String username, String password, String roomName){
		if(!games.isEmpty()){
			for(int i = 0; i < games.size(); i++){
				if(games.get(i).getName().equals(roomName)){					
					return false;
				}
			}
		}
		User teun = getLoggedInUser(username, password);
		Game room = new Game(roomName, teun);
		games.add(room);
		return true;
	}

	public boolean joinGame(String username, String password, String roomName){
		if(!games.isEmpty()){
			for(int i = 0; i < games.size(); i++){
				if(games.get(i).getName().equals(roomName)){					
					User teun = getLoggedInUser(username, password);
					return getGame(roomName).addUser(teun);
				}
			}
		}
		return false;
	}

	public boolean leaveGame(String username, String password, String roomName){
		if(!games.isEmpty()){
			for(int i = 0; i < games.size(); i++){
				if(games.get(i).getName().equals(roomName)){					
					getGame(roomName).removeUser(username);
					if(getGamePlayers(roomName).isEmpty()){
						games.remove(getGame(roomName));
					}
				}
			}
		}
		return false;
	}

	private List<String> getGameTitles(){
		List<String> names = new ArrayList<String>();
		for(int i = 0; i < games.size(); i++){
			names.add(games.get(i).getName());
		}
		return names;
	}

	public List<String> getGames(){
		return getGameTitles();
	}

	private Game getGame(String s){
		if(!games.isEmpty()){
			for(int i = 0; i < games.size(); i++){
				if(games.get(i).getName().equals(s)){					
					return games.get(i);
				}
			}
		}		
		return null;
	}

	public List<String> getGamePlayers(String s){
		return getGame(s).getPlayersNames();
	}
}
