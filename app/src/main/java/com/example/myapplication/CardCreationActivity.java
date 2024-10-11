package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CardCreationActivity extends AppCompatActivity {

    private CardManager cardManager; // Singleton instance of CardManager
    private EditText editTextCardHolder; // Input field for cardholder name
    private Spinner spinnerCardType; // Dropdown for card type
    private EditText editTextInitialBalance; // Input field for initial balance
    private Button buttonCreateCard; // Button to create the card
    private String username; // Username

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_creation); // Ensure you have this layout file

        // Initialize CardManager with the current context
        cardManager = CardManager.getInstance(this); // Pass the context

        // Retrieve username from Intent
        Intent intent = getIntent();
        username = intent.getStringExtra("username"); // Retrieve username

        // Bind UI elements
        editTextCardHolder = findViewById(R.id.editTextCardHolder);
        spinnerCardType = findViewById(R.id.spinnerCardType);
        editTextInitialBalance = findViewById(R.id.editTextInitialBalance);
        buttonCreateCard = findViewById(R.id.buttonCreateCard);

        // Setup card type selection dropdown
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.card_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCardType.setAdapter(adapter);

        // Set click listener for the button
        buttonCreateCard.setOnClickListener(v -> createCard());
    }

    private void createCard() {
        String cardHolder = editTextCardHolder.getText().toString().trim(); // Get cardholder name
        String cardType = spinnerCardType.getSelectedItem().toString(); // Get card type
        String initialBalanceStr = editTextInitialBalance.getText().toString().trim(); // Get initial balance

        // Validate inputs
        if (cardHolder.isEmpty()) {
            Toast.makeText(this, "Please enter the cardholder's name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (initialBalanceStr.isEmpty()) {
            Toast.makeText(this, "Please enter the initial balance", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convert initial balance to a number
        double initialBalance;
        try {
            initialBalance = Double.parseDouble(initialBalanceStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid initial balance", Toast.LENGTH_SHORT).show();
            return;
        }

        // Generate card number
        String cardNumber = generateCardNumber();

        // Create new card
        Card newCard = new Card(cardHolder, cardType, initialBalance, cardNumber);
        cardManager.addCard(username, newCard); // Add card to CardManager

        // Notify user
        Toast.makeText(this, "Card created successfully!", Toast.LENGTH_SHORT).show();

        // Set result and finish the activity after successfully creating the card
        Intent resultIntent = new Intent();
        setResult(RESULT_OK, resultIntent); // Return RESULT_OK to CardListActivity
        finish(); // Finish the activity
    }

    private String generateCardNumber() {
        // Logic to generate a unique card number (example using current time)
        return String.valueOf(System.currentTimeMillis()); // Using current time as an example
    }
}
