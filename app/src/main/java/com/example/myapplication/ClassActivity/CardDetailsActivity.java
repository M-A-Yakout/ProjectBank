package com.example.myapplication.ClassActivity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class CardDetailsActivity extends AppCompatActivity {

    private TextView cardNumberTextView;
    private TextView currencyTextView;
    private TextView cardHolderNameTextView;
    private TextView cvvTextView;
    private TextView expiryDateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);

        // Initialize TextViews
        initializeTextViews();

        // Get the card details from the intent
        displayCardDetails();
    }

    private void initializeTextViews() {
        cardNumberTextView = findViewById(R.id.cardNumberTextView);
        currencyTextView = findViewById(R.id.currencyTextView);
        cardHolderNameTextView = findViewById(R.id.cardHolderNameTextView);
        cvvTextView = findViewById(R.id.cvvTextView);
        expiryDateTextView = findViewById(R.id.expiryDateTextView);
    }

    private void displayCardDetails() {
        String cardNumber = getIntent().getStringExtra("CARD_NUMBER");
        String currency = getIntent().getStringExtra("CURRENCY");
        String cardHolderName = getIntent().getStringExtra("CARD_HOLDER_NAME");
        String cvv = getIntent().getStringExtra("CVV");
        String expiryDate = getIntent().getStringExtra("EXPIRY_DATE");

        // Set the text views with the card details
        cardNumberTextView.setText("Card Number: " + cardNumber);
        currencyTextView.setText("Currency: " + currency);
        cardHolderNameTextView.setText("Card Holder: " + cardHolderName);
        cvvTextView.setText("CVV: " + cvv);
        expiryDateTextView.setText("Expiry Date: " + expiryDate);
    }
}
