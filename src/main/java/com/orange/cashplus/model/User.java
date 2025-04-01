package com.orange.cashplus.model;
import java.io.Serializable;

public class User implements Serializable {
	 private static final long serialVersionUID = 1L; 
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private transient String password;  // Won't be serialized
    private transient String token;     // Won't be serialized
    private boolean isVerified;

    
    public User() {}

    public User(String firstname, String lastname, String email, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

   
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public boolean isVerified() { return isVerified; }
    public void setVerified(boolean isVerified) { this.isVerified = isVerified; }
}
