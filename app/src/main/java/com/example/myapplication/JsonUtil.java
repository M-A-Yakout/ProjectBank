package com.example.myapplication; // تأكد من أنك في نفس الحزمة

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class JsonUtil {
    private static final Gson gson = new Gson(); // إنشاء مثيل Gson

    // طريقة لتسلسل قائمة الحسابات إلى سلسلة JSON
    public static String serializeAccounts(List<Account> accounts) {
        return gson.toJson(accounts); // تحويل الكائنات إلى JSON
    }

    // طريقة لفك تسلسل سلسلة JSON إلى قائمة الحسابات
    public static List<Account> deserializeAccounts(String json) {
        Type accountListType = new TypeToken<List<Account>>() {}.getType(); // نوع القائمة
        return gson.fromJson(json, accountListType); // تحويل JSON إلى كائنات
    }
}
