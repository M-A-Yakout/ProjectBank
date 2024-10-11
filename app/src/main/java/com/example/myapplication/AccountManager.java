package com.example.myapplication;

import java.util.HashMap;
import java.util.Map;

public class AccountManager {
    private static AccountManager instance; // Singleton instance
    private final Map<String, String> accounts; // To store usernames and passwords

    // Private constructor to enforce singleton pattern
    private AccountManager() {
        accounts = new HashMap<>();
    }

    // Method to get the singleton instance
    public static AccountManager getInstance() {
        if (instance == null) {
            instance = new AccountManager();
        }
        return instance;
    }

    // Method to create an account
    public boolean createAccount(String username, String password) {
        // Check if the username already exists
        if (accounts.containsKey(username)) {
            return false; // Account creation failed (username exists)
        }

        // Add the new account
        accounts.put(username, password);
        return true; // Account creation succeeded
    }

    // Optional: Method to validate login (if needed)
    public boolean validateLogin(String username, String password) {
        return accounts.containsKey(username) && accounts.get(username).equals(password);
    }
}
