package com.orange.cashplus.dao;

import com.orange.cashplus.config.DatabaseConnection;
import com.orange.cashplus.config.EmailUtil;
import com.orange.cashplus.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {

    public static boolean registerUser(User user) {
        String sql = "INSERT INTO users (firstname, lastname, email, password, token) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            
            
            String token = UUID.randomUUID().toString();
            user.setToken(token);

            
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashedPassword); 

        
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
                    user.setPassword(rs.getString("password")); 
                    user.setToken(rs.getString("token"));
                    user.setVerified(rs.getBoolean("is_verified"));
                    return user;
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error fetching user: " + e.getMessage());
            
        }
        return null;
    }

    public static boolean validateUser(String email, String password) {
        System.out.println("[DEBUG] Starting validation for: " + email);
        String sql = "SELECT password, is_verified FROM users WHERE email = ?"; 
        
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
                    
                    return passwordMatches && isVerified;
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
           
        }
        return false;
    }

    
    public static boolean emailExists(String email) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(
                 "SELECT id FROM users WHERE email=?")) {
            
            pst.setString(1, email);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Error checking email existence: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    public static boolean createPasswordResetToken(String email) {
        String token = UUID.randomUUID().toString();
        Timestamp expiryDate = new Timestamp(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
        
        System.out.println("[DEBUG] Generated token: " + token);
        System.out.println("[DEBUG] Token expiry: " + expiryDate);
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(
                 "UPDATE users SET reset_token=?, reset_token_expiry=? WHERE email=?")) {
            
            pst.setString(1, token);
            pst.setTimestamp(2, expiryDate);
            pst.setString(3, email);
            
            int rowsUpdated = pst.executeUpdate();
            System.out.println("[DEBUG] Token storage affected rows: " + rowsUpdated);
            
            if (rowsUpdated > 0) {
                return EmailUtil.sendPasswordResetEmail(email, token);
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Token creation failed: " + e.getMessage());
        }
        return false;
    }
    public static boolean isValidResetToken(String token) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(
                 "SELECT email FROM users WHERE reset_token=? AND reset_token_expiry > NOW()")) {
            
            pst.setString(1, token);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next(); 
            }
        } catch (SQLException e) {
            System.err.println("Error validating reset token: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    private static void checkTokenStatus(String token) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(
                 "SELECT email, reset_token_expiry FROM users WHERE reset_token=?")) {
            
            pst.setString(1, token);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                Timestamp expiry = rs.getTimestamp("reset_token_expiry");
                Timestamp now = new Timestamp(System.currentTimeMillis());
                System.out.println("[DEBUG] Token expiry: " + expiry);
                System.out.println("[DEBUG] Current time: " + now);
                System.out.println("[DEBUG] Token valid: " + expiry.after(now));
            } else {
                System.out.println("[DEBUG] No user found with this token");
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Error checking token status: " + e.getMessage());
        }
    }
    public static boolean updatePasswordWithToken(String token, String newPassword) {
        System.out.println("[DEBUG] Attempting password update for token: " + token);
        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(
                 "UPDATE users SET password=?, reset_token=NULL, reset_token_expiry=NULL " +
                 "WHERE reset_token=? AND reset_token_expiry > NOW()")) {
            
            pst.setString(1, hashedPassword);
            pst.setString(2, token);
            
            int rowsUpdated = pst.executeUpdate();
            System.out.println("[DEBUG] Rows updated: " + rowsUpdated);
            
            if (rowsUpdated > 0) {
                System.out.println("[DEBUG] Password updated successfully");
                return true;
            } else {
                // Check why no rows were updated
                checkTokenStatus(token);
                return false;
            }
        } catch (SQLException e) {
            System.err.println("[ERROR] Database error during password update: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}
