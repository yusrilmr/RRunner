package server.database;

import java.sql.*;

public class DatabaseHandler {

	Connection conn;
	String url = "jdbc:mysql://145.93.49.172:3306/"; 
	String dbName = "racerunner";
	String driver = "com.mysql.jdbc.Driver";
	String sqlUser = "racerunner"; 
	String sqlPassword = "racerunner";
	
	public DatabaseHandler(){
		System.out.println("hoi3");
		try{
		System.out.println("hoi");
		Class.forName(driver).newInstance();
		conn = DriverManager.getConnection(url + dbName, sqlUser, sqlPassword);
		System.out.println("hoi1");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void instertUser(String username, String password){
		try {
			
			String statement = "INSERT INTO users (username, password) VALUES (?, ?)";

			PreparedStatement insert = conn.prepareStatement(statement);
			insert.setString(1, username);
			insert.setString(2, password);

			insert.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isUserThere(String username){
		try{
			String statement = "SELECT * FROM users WHERE username = ?";

			PreparedStatement select = conn.prepareStatement(statement);
			select.setString(1, username);

			ResultSet result = select.executeQuery();
			
			if(!result.next()){
				return false;
			}
			
			return true;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
	
	public boolean isUserThere(String username, String password){
		try{
			String statement = "SELECT * FROM users WHERE username = ? AND password = ?";

			PreparedStatement select = conn.prepareStatement(statement);
			select.setString(1, username);
			select.setString(2, password);

			ResultSet result = select.executeQuery();
			
			if(!result.next()){
				return false;
			}
			
			return true;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
}
