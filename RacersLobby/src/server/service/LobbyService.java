package server.service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import server.ServerMain;


@Path("/service")
public class LobbyService {

	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String checkLogin(@FormParam("username") String username, @FormParam("password") String password) {
		boolean login = ServerMain.getServer().checkLogin(username, password);
		if (login) {
			return "success";
		} else {
			return "fail";
		}
	}

	@POST
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void logOut(@FormParam("username") String username, @FormParam("password") String password) {
		ServerMain.getServer().logOut(username, password);
	}

	@POST
	@Path("/createroom")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String createRoom(@FormParam("username") String username, @FormParam("password") String password, 
			@FormParam("gameName") String gameName) {
		boolean create = ServerMain.getServer().createGame(username, password, gameName);
		if (create) {
			return "success";
		} else {
			return "fail";
		}
	}

	@POST
	@Path("/joinroom")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String joinRoom(@FormParam("username") String username, @FormParam("password") String password, 
			@FormParam("gameName") String gameName) {
		boolean join = ServerMain.getServer().joinGame(username, password, gameName);
		if (join) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	@POST
	@Path("/leaveroom")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String leaveRoom(@FormParam("username") String username, @FormParam("password") String password, 
			@FormParam("gameName") String gameName) {
		boolean leave = ServerMain.getServer().leaveGame(username, password, gameName);
		if (leave) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	@GET
	@Path("/refreshlobby")
	@Produces(MediaType.APPLICATION_XML)
	public ResponseList refreshlobby() {
		ResponseList games = new ResponseList();		
		games.setList(ServerMain.getServer().getGames());
		return games;
	}
	
	@POST
	@Path("/refreshroom")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ResponseList refreshroom(@FormParam("gameName") String gameName) {
		ResponseList playerNames = new ResponseList();
		playerNames.setList(ServerMain.getServer().getGamePlayers(gameName));
		return playerNames;
	}

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) // consumes FORM parameters
	public void createStudent(@FormParam("username") String username, @FormParam("password") String password) {
		boolean register = ServerMain.getServer().register(username, password);
		if (!register) {
			throw new RuntimeException("Registration is unsuccessful!");
		}
	}
}
