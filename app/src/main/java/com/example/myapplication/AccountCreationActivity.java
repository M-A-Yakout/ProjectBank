package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AccountCreationActivity extends AppCompatActivity {

    private EditText editTextUsername; // EditText for username input
    private EditText editTextPassword; // EditText for password input
    private Button buttonCreateAccount; // Button to create account
    private AccountManager accountManager; // Instance of AccountManager

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation); // Ensure you have this layout file

        // Initialize AccountManager
        accountManager = AccountManager.getInstance();

        // Bind UI elements
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonCreateAccount = findViewById(R.id.buttonCreateAccount);

        // Set click listener for the create account button
        buttonCreateAccount.setOnClickListener(v -> createAccount());
    }

    private void createAccount() {
        // Get user inputs
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Validate inputs
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "الرجاء إدخال اسم المستخدم.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "الرجاء إدخال كلمة المرور.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create account
        boolean isAccountCreated = accountManager.createAccount(username, password);
        if (isAccountCreated) {
            Toast.makeText(this, "تم إنشاء الحساب بنجاح!", Toast.LENGTH_SHORT).show();
            // Navigate to CardListActivity
            Intent intent = new Intent(this, CardListActivity.class);
            intent.putExtra("username", username); // Pass username to CardListActivity
            startActivity(intent); // Start CardListActivity
            finish(); // End this activity
        } else {
            Toast.makeText(this, "فشل إنشاء الحساب، يرجى المحاولة مرة أخرى.", Toast.LENGTH_SHORT).show();
        }
    }
}
