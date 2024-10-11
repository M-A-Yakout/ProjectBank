package com.example.myapplication;

public class LoginCard {
    private String username; // The username for the account

    public LoginCard(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username; // Return the username
    }

    // Validate the username (you can add more complex validation if needed)
    public boolean isValid() {
        return username != null && !username.trim().isEmpty(); // Check if username is valid
    }
}
