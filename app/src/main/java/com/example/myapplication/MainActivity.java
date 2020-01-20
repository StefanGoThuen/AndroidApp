package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {
    public static final String MYSTRING = "mystring";
    ArrayList<HashMap<String, String>> contactList;
    private RequestQueue queue;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);

        GetQuotes quotes = new GetQuotes();
        quotes.execute();


        final ImageView sampleImage;
        final Button show;
        sampleImage = findViewById(R.id.vector);
        show = findViewById(R.id.button1);
        show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                sampleImage.setVisibility(View.VISIBLE);

                String[] array = getResources().getStringArray(R.array.vectorArray);
                for (String s : array) {
                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                }
                show.setVisibility(View.GONE);

            }
        });


    }

    public void onclick(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra(MYSTRING, "start string");
        startActivity(intent);
    }


    private class GetQuotes extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();
            String url = "https://the-office-api.herokuapp.com/season/1/format/quotes";
            String jsonStr = sh.makeServiceCall(url);

            return jsonStr;
           /* Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray quotes = jsonObj.getJSONArray("quotes");


                    return null;
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            }

            return null;*/

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            TextView textView = findViewById(R.id.quote);
            textView.setText(s);
        }
    }
}
