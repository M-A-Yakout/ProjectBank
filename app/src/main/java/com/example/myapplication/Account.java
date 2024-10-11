package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String username; // اسم المستخدم
    private List<Card> cards; // قائمة البطاقات المرتبطة بالحساب

    // Constructor لإنشاء حساب جديد مع اسم المستخدم
    public Account(String username) {
        this.username = username;
        this.cards = new ArrayList<>(); // تهيئة قائمة البطاقات كقائمة فارغة
    }

    // Getter لاسم المستخدم
    public String getUsername() {
        return username; // إرجاع اسم المستخدم
    }

    // Getter لقائمة البطاقات
    public List<Card> getCards() {
        return cards; // إرجاع قائمة البطاقات
    }

    // طريقة لإضافة بطاقة جديدة إلى الحساب
    public boolean addCard(Card card) {
        // التحقق من أن البطاقة غير فارغة وأنها لا توجد بالفعل في القائمة
        if (card != null && !cards.contains(card)) {
            cards.add(card); // إضافة البطاقة إلى القائمة
            return true; // تمت الإضافة بنجاح
        }
        return false; // البطاقة موجودة بالفعل أو غير صالحة
    }
}
