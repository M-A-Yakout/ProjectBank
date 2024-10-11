package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistory {
    private List<String> history; // قائمة لتخزين تاريخ المعاملات

    // Constructor
    public TransactionHistory() {
        history = new ArrayList<>();
    }

    // دالة لإضافة معاملة جديدة إلى التاريخ
    public void addTransaction(String transaction) {
        history.add(transaction); // إضافة المعاملة
    }

    // دالة للحصول على تاريخ المعاملات
    public List<String> getHistory() {
        return history; // إرجاع قائمة تاريخ المعاملات
    }
}
