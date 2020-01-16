package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
public static final String MYSTRING = "mystring";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String [] array = getResources().getStringArray(R.array.vectorArray);
        for (String s : array){
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        }
    }

    public void onclick(View view) {
        Intent intent = new Intent(this,Main2Activity.class);
        intent.putExtra(MYSTRING, "start string");
        startActivity(intent);
    }
}
