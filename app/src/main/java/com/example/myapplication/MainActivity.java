package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.widget.ImageView;



public class MainActivity extends AppCompatActivity {
public static final String MYSTRING = "mystring";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView sampleImage;
        final Button show;
        sampleImage = (ImageView) findViewById(R.id.vector);
        show = (Button)findViewById(R.id.button1);
        show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                sampleImage.setVisibility(View.VISIBLE);

                String [] array = getResources().getStringArray(R.array.vectorArray);
                for (String s : array){
                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                }
                show.setVisibility(View.GONE);

            }
        });



    }

    public void onclick(View view) {
        Intent intent = new Intent(this,Main2Activity.class);
        intent.putExtra(MYSTRING, "start string");
        startActivity(intent);
    }
}
