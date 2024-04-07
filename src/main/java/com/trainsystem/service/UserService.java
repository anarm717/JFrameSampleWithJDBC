package com.trainsystem.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.trainsystem.backend.entity.User;
import com.trainsystem.backend.helper.DatabaseConnection;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService {

	public User finUserByNameAndSurname(String username, String password) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "select id,username, name,surname from users where username=? and password=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
            	String encryptedPassword = hashPassword(password);
                statement.setString(1, username);
                statement.setString(2, encryptedPassword);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next())
                {
                	User user = new User();
                	user.setId(resultSet.getLong("id"));
                	user.setUsername(resultSet.getString("username"));
                	user.setName(resultSet.getString("name"));
                	user.setSurname(resultSet.getString("surname"));
                	return user;
                }
                return null;
            }
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
	
	 public User createUser(String username, String password, String name, String surname) {
	        User createdUser = null;
	        String sql = "INSERT INTO users (username, password, name, surname) VALUES (?, ?, ?, ?) RETURNING id";
	        
	        String encryptedPassword = hashPassword(password);
	        try (Connection connection = DatabaseConnection.getConnection();
	             PreparedStatement statement = connection.prepareStatement(sql)) {
	            
	            statement.setString(1, username);
	            statement.setString(2, encryptedPassword);
	            statement.setString(3, name);
	            statement.setString(4, surname);
	            
	            // Execute the insert query and retrieve the autogenerated ID
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    int generatedId = resultSet.getInt(1); // Autogenerated ID
	                    createdUser = new User(generatedId, username, encryptedPassword, name, surname);
	                }
	            }
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        return createdUser;
	        
	    }
	 public static String hashPassword(String password) {
	        try {
	            // Create MessageDigest instance for SHA-256
	            MessageDigest md = MessageDigest.getInstance("SHA-256");
	            
	            // Add password bytes to digest
	            md.update(password.getBytes());
	            
	            // Get the hash's bytes
	            byte[] bytes = md.digest();
	            
	            // Convert bytes to hexadecimal format
	            StringBuilder sb = new StringBuilder();
	            for (byte aByte : bytes) {
	                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
	            }
	            
	            // Return hashed password
	            return sb.toString();
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	
}
