package server;

import java.util.ArrayList;
import java.util.List;

public class Game {
	List<User> players = new ArrayList<User>();
	String name;

	public Game(String name, User u){
		this.name = name;
		addUser(u);
	}

	public boolean addUser(User u){
		if(players.size() < 4){
			players.add(u);
			return true;
		}else{
			return false;
		}		
	}

	public void removeUser(String u){
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).getUsername().equals(u)){
				players.remove(players.get(i));
			}
		}
	}

	public User getUser(int i){
		if(players.get(i) != null){
			return players.get(i);
		}else{
			return null;
		}
	}

	public String getName(){
		return name;
	}

	public List<String> getPlayersNames(){
		List<String> names = new ArrayList<String>();
		for(int i = 0; i < players.size(); i++){
			names.add(players.get(i).getUsername());		
		}
		return names;
	}

}
