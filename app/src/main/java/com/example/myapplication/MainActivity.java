package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
public static final String MYSTRING = "mystring";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onclick(View view) {
        Intent intent = new Intent(this,Main2Activity.class);
        intent.putExtra(MYSTRING, "start string");
        startActivity(intent);
    }
}
