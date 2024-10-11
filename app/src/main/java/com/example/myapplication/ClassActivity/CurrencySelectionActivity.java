package com.example.myapplication.ClassActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Database.FirebaseRealtimeDatabaseHelper;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Random;

public class CurrencySelectionActivity extends AppCompatActivity {

    private Spinner currencySpinner;
    private Button saveButton;
    private ImageButton backButton;
    private EditText cardHolderNameEditText; // New EditText for card holder name
    private FirebaseRealtimeDatabaseHelper firebaseDatabaseHelper;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_selection);

        currencySpinner = findViewById(R.id.currencySpinner);
        saveButton = findViewById(R.id.saveButton);
        backButton = findViewById(R.id.imageButton5);
        cardHolderNameEditText = findViewById(R.id.cardHolderNameEditText); // Initialize the new EditText

        // Initialize Firebase
        firebaseDatabaseHelper = new FirebaseRealtimeDatabaseHelper();
        auth = FirebaseAuth.getInstance();

        // Populate the spinner with currency options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.currency_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currencySpinner.setAdapter(adapter);

        saveButton.setOnClickListener(v -> saveCardInformation());

        // Set OnClickListener for back button
        backButton.setOnClickListener(v -> onBackPressed());
    }

    private void saveCardInformation() {
        String cardNumber = generateRandomCardNumber(); // Generate random card number
        String currency = currencySpinner.getSelectedItem() != null ? currencySpinner.getSelectedItem().toString() : null;
        String cardHolderName = cardHolderNameEditText.getText().toString().trim(); // Retrieve card holder name
        String cvv = generateRandomCVV(); // Generate random CVV
        String expiryDate = generateRandomExpiryDate(); // Generate random expiry date

        if (currency == null) {
            Toast.makeText(this, "Please select a currency", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            firebaseDatabaseHelper.addCard(email, cardNumber, currency, cardHolderName, cvv, expiryDate);
            Toast.makeText(this, "Card information saved successfully", Toast.LENGTH_SHORT).show();

            // Start CardDetailsActivity and pass card details
            Intent intent = new Intent(CurrencySelectionActivity.this, CardDetailsActivity.class);
            intent.putExtra("CARD_NUMBER", cardNumber);
            intent.putExtra("CURRENCY", currency);
            intent.putExtra("CARD_HOLDER_NAME", cardHolderName); // Pass card holder name
            intent.putExtra("CVV", cvv); // Pass CVV
            intent.putExtra("EXPIRY_DATE", expiryDate); // Pass expiry date
            startActivity(intent);

            finish(); // Close this activity
        } else {
            Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show();
        }
    }

    private String generateRandomCardNumber() {
        Random random = new Random();
        int number = random.nextInt(999999); // Generate a random number between 0 and 999999
        return String.format("%06d", number); // Format it to ensure it's 6 digits
    }

    // Method to generate random CVV
    private String generateRandomCVV() {
        Random random = new Random();
        int cvv = 100 + random.nextInt(900); // Generate a random CVV between 100 and 999
        return String.valueOf(cvv); // Convert to string
    }

    // Method to generate random expiry date (MM/YYYY)
    private String generateRandomExpiryDate() {
        Random random = new Random();
        int month = 1 + random.nextInt(12); // Generate a random month between 1 and 12
        int year = 24 + random.nextInt(6); // Generate a random year (2024-2029)
        return String.format("%02d/%04d", month, year); // Format to MM/YYYY
    }
}
