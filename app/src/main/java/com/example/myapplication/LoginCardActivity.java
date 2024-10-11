package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginCardActivity extends AppCompatActivity {

    private CardManager cardManager; // Card manager instance
    private EditText editTextUsername; // Input field for username
    private Button buttonLogin; // Button to log in
    private Button buttonSignUp; // Button to sign up

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_card); // Ensure layout file exists

        // Initialize CardManager with context
        cardManager = CardManager.getInstance(this);

        // Link UI elements
        editTextUsername = findViewById(R.id.editTextUsername);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonSignUp = findViewById(R.id.buttonSignUp);

        // Set up button click listeners
        buttonLogin.setOnClickListener(v -> login()); // Call method to log in
        buttonSignUp.setOnClickListener(v -> signUp()); // Call method to sign up
    }

    private void login() {
        String username = editTextUsername.getText().toString().trim(); // Get username

        // Check if the username is valid
        if (!username.isEmpty()) {
            // Check if the account exists
            Account account = cardManager.findAccount(username);
            if (account != null) {
                // Successful login
                Toast.makeText(this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginCardActivity.this, CardListActivity.class);
                intent.putExtra("username", username); // Pass username to CardListActivity
                startActivity(intent); // Navigate to CardListActivity
                finish(); // Finish LoginCardActivity
            } else {
                // Account not found
                Toast.makeText(this, "Username not found", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter a valid username", Toast.LENGTH_SHORT).show();
        }
    }

    private void signUp() {
        String username = editTextUsername.getText().toString().trim(); // Get username

        // Check if the username is valid
        if (!username.isEmpty()) {
            // Check if the account already exists
            Account account = cardManager.findAccount(username);
            if (account == null) {
                // Create the account
                boolean accountCreated = cardManager.createAccount(username);
                if (accountCreated) {
                    Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show();

                    // Navigate to CardListActivity
                    Intent intent = new Intent(LoginCardActivity.this, CardListActivity.class);
                    intent.putExtra("username", username); // Pass username to CardListActivity
                    startActivity(intent);
                    finish(); // Finish LoginCardActivity
                } else {
                    // Handle account creation failure
                    Toast.makeText(this, "Failed to create account. Please try again.", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Account already exists
                Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please enter a valid username", Toast.LENGTH_SHORT).show();
        }
    }
}
