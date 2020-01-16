package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    Boolean aBoolean = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        editText = findViewById(R.id.edit);
        textView = findViewById(R.id.text);
        String string = getIntent().getExtras().getString(MainActivity.MYSTRING);
        if (string != null) {
            textView.setText(string);
        }
        Toast.makeText(this, getString(R.string.toastMessage), Toast.LENGTH_LONG).show();
    }

    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.checkBox:
                CheckBox checkBox = findViewById(R.id.checkBox);
                aBoolean = checkBox.isChecked();
                break;
            case R.id.button:
                String text = editText.getText().toString();
                if (aBoolean) {
                    text = text.toUpperCase();
                }
                textView.setText(text);
                editText.setText("");
        }
    }
}
