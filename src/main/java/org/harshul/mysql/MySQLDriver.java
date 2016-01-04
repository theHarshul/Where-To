package org.harshul.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.harshul.model.User;

import com.mysql.jdbc.Driver;

public class MySQLDriver {

	private Connection con;
	private final static String selectAllUsers = "SELECT * FROM user.user;";
	private final static String verifyUser = "SELECT userID FROM user.user u WHERE u.username = ? AND u.pass = ?";
	private final static String getLastUserID = "SELECT userID FROM user.user ORDER BY userID DESC LIMIT 1";
	private final static String addUser = "INSERT INTO user (username,pass) VALUES(?,?)";

	public MySQLDriver() {
		try {
			new Driver();
		} catch (SQLException e) {
			System.out.println("MySQLDriver instantiation problem");
			e.printStackTrace();
		}
	}

	public void connect() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user?user=root&password=root");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void stop() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<User> getAllUsers() {
		
		ArrayList<User> users = new ArrayList<User>();
		
		try {
			PreparedStatement ps = con.prepareStatement(selectAllUsers);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				
				int id = result.getInt("userID");
				String username = result.getString("username");
				String password = result.getString("pass");
				
				users.add(new User(id,username,password));
				
				System.out.println(username);
			}

			result.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}
	
	public boolean verifyUser(String username, String password) {
		
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(verifyUser);

			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			

			// if such a user exists
			if (rs.next()) {
				return true;
			}
			
			else return false;
			

		} catch (SQLException e) {
			System.out.println("SQLException in verifyUser() " + e.getMessage());
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}
	
	public boolean verifyLogin(String username, String password){
		try{
			PreparedStatement ps = con.prepareStatement(verifyUser);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			
			if(rs.next()){
				return true;
			}
			
			rs.close();
			
		}catch(SQLException e){
			System.out.println("SQLException in alreadyExists(): " + e.getMessage());
		}
		
		
		return false;
	}
	
	public void addUser(String username, String password) {
		
		
		
		try {
			PreparedStatement insert = con.prepareStatement(addUser);

			insert.setString(1, username);
			insert.setString(2, password);
			insert.executeUpdate();

			insert.close();
			
			
			

		} catch (SQLException e) {
			System.out.println("addUser SQL Exception: " + e.getMessage());

		}
		
	}
	
	public int numberOfUsers(){
		
		int userID = 0;
		
		try{
			PreparedStatement lastUserID = con.prepareStatement(getLastUserID);
			
			ResultSet rs = lastUserID.executeQuery();

			userID = -1;

			if (rs.next()) {
				userID = rs.getInt("userID");
			
			}
			
			lastUserID.close();
			
		} catch (SQLException e) {
			System.out.println("addUser SQL Exception: " + e.getMessage());

		}
		
		return userID;
	}
	

	public static void main(String[] args) {
		
		MySQLDriver driver = new MySQLDriver();
		driver.connect();
		driver.stop();
		
	}

}
