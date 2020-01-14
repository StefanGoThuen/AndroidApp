package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         editText = findViewById(R.id.edit);
         textView = findViewById(R.id.text);
    }

    public void onclick(View view) {
       String text = editText.getText().toString();
       textView.setText(text);
       editText.setText("");
    }
}
