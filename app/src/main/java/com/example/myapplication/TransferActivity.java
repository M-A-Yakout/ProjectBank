package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class TransferActivity extends AppCompatActivity {

    private CardManager cardManager;
    private Spinner spinnerFromCard;
    private Spinner spinnerToCard;
    private EditText editTextAmount;
    private Button buttonTransfer;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer2);

        cardManager = CardManager.getInstance(this);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        spinnerFromCard = findViewById(R.id.spinnerFromCard);
        spinnerToCard = findViewById(R.id.spinnerToCard);
        editTextAmount = findViewById(R.id.editTextAmount);
        buttonTransfer = findViewById(R.id.buttonTransfer);

        loadUserCards();

        buttonTransfer.setOnClickListener(v -> transferMoney());
    }

    private void loadUserCards() {
        List<Card> cards = cardManager.getCardsForAccount(username);
        ArrayAdapter<Card> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, cards);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFromCard.setAdapter(adapter);
        spinnerToCard.setAdapter(adapter);
    }

    private void transferMoney() {
        Card fromCard = (Card) spinnerFromCard.getSelectedItem();
        Card toCard = (Card) spinnerToCard.getSelectedItem();
        double amount;

        // Validate amount input
        try {
            amount = Double.parseDouble(editTextAmount.getText().toString().trim());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid amount", Toast.LENGTH_SHORT).show();
            return;
        }

        // Perform transfer
        if (fromCard != null && toCard != null) {
            if (fromCard.withdraw(amount)) {
                toCard.addBalance(amount);
                Toast.makeText(this, "Money transferred successfully", Toast.LENGTH_SHORT).show();

                // Optionally, update the UI here
                loadUserCards(); // Refresh the card list
                setResult(RESULT_OK); // Indicate success
                finish(); // Finish this activity
            } else {
                Toast.makeText(this, "Insufficient balance in the sending card", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please check the entered card numbers", Toast.LENGTH_SHORT).show();
        }
    }
}
