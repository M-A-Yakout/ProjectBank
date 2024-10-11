package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class CardListActivity extends AppCompatActivity {

    private CardManager cardManager; // Singleton instance of CardManager
    private ListView listViewCards; // ListView to display cards
    private String username; // Username to retrieve cards
    private boolean hasShownToast = false; // Track if a toast has been shown

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list); // Ensure you have this layout file

        // Initialize CardManager with the current context
        cardManager = CardManager.getInstance(this); // Pass the context

        // Retrieve username from Intent
        Intent intent = getIntent();
        username = intent.getStringExtra("username"); // Retrieve username

        // Bind ListView
        listViewCards = findViewById(R.id.listViewCards);

        // Load cards for the account
        loadCards();

        // Setup buttons to navigate to create and transfer activities
        Button buttonCreateCard = findViewById(R.id.buttonCreateCard);
        Button buttonTransfer = findViewById(R.id.buttonTransfer);

        buttonCreateCard.setOnClickListener(v -> navigateToCardCreation());
        buttonTransfer.setOnClickListener(v -> navigateToTransfer());
    }

    private void loadCards() {
        // Check if the username is null
        if (username == null) {
            if (!hasShownToast) {
                Toast.makeText(this, "Username not found.", Toast.LENGTH_SHORT).show(); // Show message if username is null
                hasShownToast = true; // Mark toast as shown
            }
            return; // Exit method if username is null
        }

        // Retrieve cards for the specified account
        List<Card> cards = cardManager.getCardsForAccount(username);
        if (cards.isEmpty()) {
            if (!hasShownToast) {
                Toast.makeText(this, "No cards available.", Toast.LENGTH_SHORT).show(); // No cards available
                hasShownToast = true; // Mark toast as shown
            }
        } else {
            hasShownToast = false; // Reset if there are cards available
            ArrayAdapter<Card> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, cards);
            listViewCards.setAdapter(adapter); // Set adapter to ListView
        }
    }

    // Method to navigate to the card creation activity
    private void navigateToCardCreation() {
        Intent intent = new Intent(CardListActivity.this, CardCreationActivity.class);
        intent.putExtra("username", username); // Pass username to CardCreationActivity
        startActivityForResult(intent, 1); // Start CardCreationActivity with request code
    }

    // Method to navigate to the transfer activity
    private void navigateToTransfer() {
        Intent intent = new Intent(CardListActivity.this, TransferActivity.class);
        intent.putExtra("username", username); // Pass username to TransferActivity
        startActivityForResult(intent, 2); // Start TransferActivity with request code
    }

    // Handle the result from started activities
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check if the result is successful
        if (resultCode == RESULT_OK) {
            loadCards(); // Update the card list after creating a new card or performing a transfer
        }
    }


    // Optionally hide the keyboard when navigating to another activity
    @Override
    public void onUserLeaveHint() {
        super.onUserLeaveHint();
        hideKeyboard(); // Hide keyboard when user leaves the activity
    }

    // Method to hide the keyboard
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null && getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0); // Hide keyboard
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCards(); // Reload cards when returning to this activity
    }
}
