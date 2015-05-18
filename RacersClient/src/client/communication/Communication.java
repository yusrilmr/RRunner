package client.communication;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import client.ClientMain;

public class Communication {
	ClientMain client;
	private WebTarget serviceTarget;
	
	public Communication(ClientMain cm){
		this.client = cm;
		Client serviceClient = ClientBuilder.newClient();
		String ip = client.getServerIP();
		URI baseURI = UriBuilder.fromUri(ip).build();
		serviceTarget = serviceClient.target(baseURI);
	}
	
	public String registerUser(String username, String password){
		Form form = new Form();
		form.param("username", username);
		form.param("password", password);
		
		WebTarget methodTarget = serviceTarget.path("RacersLobby").path("lobby").path("service").path("register");
		Builder requestBuilder = methodTarget.request();
		requestBuilder= requestBuilder.accept(MediaType.TEXT_PLAIN);
		Response response = requestBuilder.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
		
		if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode()){
			return("User has been registered!");
		} else {
			return("The user cannot be registered!");
		}
	}
	
	public boolean loginUser(String username, String password){
		Form form = new Form();
		form.param("username", username);
		form.param("password", password);
		
		WebTarget methodTarget = serviceTarget.path("RacersLobby").path("lobby").path("service").path("login");
		Builder requestBuilder = methodTarget.request();
		requestBuilder = requestBuilder.accept(MediaType.TEXT_PLAIN);
		Response response = requestBuilder.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
		
		if(!response.readEntity(String.class).equals("fail")){
			return true;
		}else{
			return false;
		}
	}
	
	public List<String> refreshLobby(){
		WebTarget methodTarget = serviceTarget.path("RacersLobby").path("lobby").path("service").path("refreshlobby");
		Builder requestBuilder = methodTarget.request();
		requestBuilder= requestBuilder.accept(MediaType.APPLICATION_XML);
		Response response = requestBuilder.get();
		ResponseList gameNames = response.readEntity(ResponseList.class);
		return gameNames.getList();		
	}
	
	public List<String> refreshRoom(String gameName){
		Form form = new Form();
		form.param("gameName", gameName);
		WebTarget methodTarget = serviceTarget.path("RacersLobby").path("lobby").path("service").path("refreshroom");
		Builder requestBuilder = methodTarget.request();
		requestBuilder= requestBuilder.accept(MediaType.APPLICATION_XML);
		Response response = requestBuilder.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
		ResponseList gameNames = response.readEntity(ResponseList.class);
		return gameNames.getList();		
	}
	
	public boolean createRoom(String username, String password, String gameName){
		Form form = new Form();
		form.param("username", username);
		form.param("password", password);
		form.param("gameName", gameName);
		
		WebTarget methodTarget = serviceTarget.path("RacersLobby").path("lobby").path("service").path("createroom");
		Builder requestBuilder = methodTarget.request();
		requestBuilder = requestBuilder.accept(MediaType.TEXT_PLAIN);
		Response response = requestBuilder.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
		
		if(!response.readEntity(String.class).equals("fail")){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean joinRoom(String username, String password, String gameName){
		Form form = new Form();
		form.param("username", username);
		form.param("password", password);
		form.param("gameName", gameName);
		
		WebTarget methodTarget = serviceTarget.path("RacersLobby").path("lobby").path("service").path("joinroom");
		Builder requestBuilder = methodTarget.request();
		requestBuilder = requestBuilder.accept(MediaType.TEXT_PLAIN);
		Response response = requestBuilder.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
		
		if(!response.readEntity(String.class).equals("fail")){
			return true;
		}else{
			return false;
		}		
	}
	
	public boolean leaveRoom(String username, String password, String gameName){
		Form form = new Form();
		form.param("username", username);
		form.param("password", password);
		form.param("gameName", gameName);
		
		WebTarget methodTarget = serviceTarget.path("RacersLobby").path("lobby").path("service").path("leaveroom");
		Builder requestBuilder = methodTarget.request();
		requestBuilder = requestBuilder.accept(MediaType.TEXT_PLAIN);
		Response response = requestBuilder.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
		
		if(!response.readEntity(String.class).equals("fail")){
			return true;
		}else{
			return false;
		}		
	}
	
}
