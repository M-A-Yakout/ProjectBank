package com.example.myapplication.ClassDB;

public class Card {
    private String cardNumber;
    private String currency;
    private String cardHolderName; // Add card holder name field
    private String cvv; // Add CVV field
    private String expiryDate; // Add expiry date field

    // Default constructor required for calls to DataSnapshot.getValue(Card.class)
    public Card() {
    }

    public Card(String cardNumber, String currency, String cardHolderName, String cvv, String expiryDate) {
        this.cardNumber = cardNumber;
        this.currency = currency;
        this.cardHolderName = cardHolderName; // Initialize card holder name
        this.cvv = cvv; // Initialize CVV
        this.expiryDate = expiryDate; // Initialize expiry date
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public String getCardHolderName() { // Getter for card holder name
        return cardHolderName;
    }

    public String getCvv() { // Getter for CVV
        return cvv;
    }

    public String getExpiryDate() { // Getter for expiry date
        return expiryDate;
    }
}
