package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CardAdapter extends ArrayAdapter<Card> {

    public CardAdapter(Context context, List<Card> cards) {
        super(context, 0, cards);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // الحصول على الكارت
        Card card = getItem(position);

        // إعداد العرض للكارت
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        // ربط البيانات بعناصر واجهة المستخدم
        TextView textView1 = convertView.findViewById(android.R.id.text1);
        TextView textView2 = convertView.findViewById(android.R.id.text2);

        textView1.setText("رقم الكارت: " + card.getCardNumber());
        textView2.setText("الرصيد: " + card.getBalance() + " " + card.getCardType());

        return convertView;
    }
}
