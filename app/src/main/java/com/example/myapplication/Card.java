package com.example.myapplication;

public class Card {
    private String cardHolder; // اسم حامل البطاقة
    private String cardType;   // نوع البطاقة
    private double balance;     // رصيد البطاقة
    private String cardNumber;  // رقم البطاقة

    // Constructor لإنشاء بطاقة جديدة
    public Card(String cardHolder, String cardType, double balance, String cardNumber) {
        this.cardHolder = cardHolder;  // تعيين اسم حامل البطاقة
        this.cardType = cardType;      // تعيين نوع البطاقة
        this.balance = balance;        // تعيين الرصيد
        this.cardNumber = cardNumber;  // تعيين رقم البطاقة
    }

    // Getter لاسم حامل البطاقة
    public String getCardHolder() {
        return cardHolder;
    }

    // Getter لنوع البطاقة
    public String getCardType() {
        return cardType;
    }

    // Getter لرصيد البطاقة
    public double getBalance() {
        return balance;
    }

    // Getter لرقم البطاقة
    public String getCardNumber() {
        return cardNumber;
    }

    // طريقة لإضافة رصيد إلى البطاقة
    public void addBalance(double amount) {
        if (amount > 0) { // التحقق من أن المبلغ موجب
            balance += amount; // زيادة الرصيد
        }
    }

    // طريقة لسحب المبلغ من البطاقة
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) { // التحقق من أن المبلغ موجب وأقل من أو يساوي الرصيد
            balance -= amount; // تقليل الرصيد
            return true; // سحب ناجح
        }
        return false; // فشل السحب
    }

    // إعادة كتابة toString لطباعة معلومات البطاقة
    @Override
    public String toString() {
        return "Card Holder: " + cardHolder + ", Card Type: " + cardType + ", Balance: " + balance; // طباعة معلومات البطاقة
    }

    // إعادة كتابة equals للتحقق من المساواة بين البطاقات
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // إذا كانت الكائنات هي نفسها
        if (obj == null || getClass() != obj.getClass()) return false; // تحقق من النوع

        Card card = (Card) obj;
        return cardNumber.equals(card.cardNumber); // مقارنة رقم البطاقة
    }

    // إعادة كتابة hashCode لتوليد قيمة هاش
    @Override
    public int hashCode() {
        return cardNumber.hashCode(); // استخدام رقم البطاقة كقيمة هاش
    }
}
