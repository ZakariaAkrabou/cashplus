package com.orange.cashplus.dao;

import com.orange.cashplus.config.DatabaseConnection;
import com.orange.cashplus.config.EmailUtil;
import com.orange.cashplus.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {

    public static boolean registerUser(User user) {
        String sql = "INSERT INTO users (firstname, lastname, email, password, token) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            // Generate verification token
            String token = UUID.randomUUID().toString();
            user.setToken(token);

            // Hash password
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashedPassword); // Store hashed version only

            // Set parameters
            pst.setString(1, user.getFirstname());
            pst.setString(2, user.getLastname());
            pst.setString(3, user.getEmail());
            pst.setString(4, hashedPassword);
            pst.setString(5, token);

            int rowsAffected = pst.executeUpdate();
            
            if (rowsAffected > 0) {
                EmailUtil.sendVerificationEmail(user.getEmail(), token);
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Database error during registration: " + e.getMessage());
            // Consider throwing a custom exception here
        }
        return false;
    }

    public static User getUserByEmail(String email) {
        String sql = "SELECT id, firstname, lastname, email, password, token, is_verified FROM users WHERE email = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, email);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setFirstname(rs.getString("firstname"));
                    user.setLastname(rs.getString("lastname"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password")); // Hashed password
                    user.setToken(rs.getString("token"));
                    user.setVerified(rs.getBoolean("is_verified"));
                    return user;
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error fetching user: " + e.getMessage());
            // Consider throwing a custom exception here
        }
        return null;
    }

    public static boolean validateUser(String email, String password) {
        System.out.println("[DEBUG] Starting validation for: " + email);
        String sql = "SELECT password, is_verified FROM users WHERE email = ?"; // Removed is_verified check temporarily
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, email);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password");
                    boolean isVerified = rs.getBoolean("is_verified");
                    
                    System.out.println("[DEBUG] Found user - Verified: " + isVerified);
                    System.out.println("[DEBUG] Stored hash: " + storedHash);
                    
                    boolean passwordMatches = BCrypt.checkpw(password, storedHash);
                    System.out.println("[DEBUG] Password matches: " + passwordMatches);
                    
                    return passwordMatches && isVerified; // Now we can see which check failed
                } else {
                    System.out.println("[DEBUG] No user found with this email");
                }
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Database error: " + e.getMessage());
        }
        return false;
    }

    public static boolean verifyUser(String token) {
        String sql = "UPDATE users SET is_verified = TRUE WHERE token = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, token);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Database error during verification: " + e.getMessage());
            // Consider throwing a custom exception here
        }
        return false;
    }

    // Additional useful methods
    public static boolean emailExists(String email) {
        String sql = "SELECT 1 FROM users WHERE email = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            pst.setString(1, email);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Database error checking email: " + e.getMessage());
        }
        return false;
    }
}