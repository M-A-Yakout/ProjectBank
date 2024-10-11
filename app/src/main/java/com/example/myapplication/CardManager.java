package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CardManager {
    private static CardManager instance; // Singleton instance of CardManager
    private List<Account> accounts; // List of accounts
    private static final String PREFS_NAME = "BankAppPrefs"; // SharedPreferences file name
    private static final String ACCOUNTS_KEY = "AccountsKey"; // Key for storing accounts
    private Gson gson; // Gson instance for serialization
    private Context context; // Context for accessing SharedPreferences

    private CardManager(Context context) {
        this.context = context; // Store the context
        gson = new Gson(); // Initialize Gson
        accounts = new ArrayList<>(); // Initialize the account list
        loadAccounts(); // Load accounts from SharedPreferences
    }

    // Singleton pattern to get instance
    public static synchronized CardManager getInstance(Context context) {
        if (instance == null) {
            instance = new CardManager(context); // Create a new instance if not already created
        }
        return instance;
    }

    // Load accounts from SharedPreferences
    private void loadAccounts() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        String json = prefs.getString(ACCOUNTS_KEY, null);
        Type type = new TypeToken<List<Account>>() {}.getType();
        accounts = json != null ? gson.fromJson(json, type) : new ArrayList<>(); // Deserialize accounts
    }

    // Save accounts to SharedPreferences
    private void saveAccounts() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        String json = gson.toJson(accounts); // Serialize accounts
        editor.putString(ACCOUNTS_KEY, json);
        editor.apply(); // Commit changes
    }

    public boolean createAccount(String username) {
        if (findAccount(username) == null) {
            accounts.add(new Account(username)); // Add a new account
            Log.i("CardManager", "Account created: " + username);
            saveAccounts(); // Save accounts after creation
            return true; // Account created successfully
        }
        Log.e("CardManager", "Failed to create account: Account already exists: " + username);
        return false; // Account already exists
    }

    public Account findAccount(String username) {
        for (Account account : accounts) {
            if (account.getUsername().equals(username)) {
                return account; // Return the account if found
            }
        }
        return null; // Account not found
    }

    public Card findCardByNumber(String cardNumber) {
        for (Account account : accounts) {
            for (Card card : account.getCards()) {
                if (card.getCardNumber().equals(cardNumber)) {
                    return card; // Return the card if found
                }
            }
        }
        return null; // Card not found
    }

    // Updated addCard method to ensure it works as expected
    public boolean addCard(String username, Card card) {
        Account account = findAccount(username); // Find the account by username
        if (account != null) { // Ensure the account exists
            boolean added = account.addCard(card); // Add the card to the account
            if (added) {
                saveAccounts(); // Save accounts after adding a card
            }
            return added;
        }
        Log.e("CardManager", "Failed to add card: Account does not exist for username: " + username);
        return false; // Account does not exist
    }

    // Method to get cards for a specific account
    public List<Card> getCardsForAccount(String username) {
        Account account = findAccount(username); // Find the account by username
        if (account != null) { // Ensure the account exists
            return account.getCards(); // Return the list of cards for that account
        }
        Log.e("CardManager", "Failed to get cards: Account does not exist for username: " + username);
        return new ArrayList<>(); // Return an empty list if the account does not exist
    }
}
