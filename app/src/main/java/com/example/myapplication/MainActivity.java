package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    public static final String MYSTRING = "mystring";
    ArrayList<HashMap<String, String>> quoteList;

    private String TAG = MainActivity.class.getSimpleName();
    private ListView lv;
    TextView random;
    Button show1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

        //hide image and button method
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

        //button for a random quote from office api
        random = findViewById(R.id.quote);
        show1 = findViewById(R.id.quoteKnapp);
        show1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                quoteKnapp();
            }
        });
    }

    private void quoteKnapp(){
        new GetQuotes().execute();
        random.setVisibility(View.VISIBLE);
    }


    public void onclick(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra(MYSTRING, "start string");
        startActivity(intent);
    }


private class GetQuotes extends AsyncTask<Void, Void, String> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(MainActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();

    }

    @Override
    protected String doInBackground(Void... voids) {
        HttpHandler sh = new HttpHandler();
        String url = "https://the-office-api.herokuapp.com/season/1/format/quotes";
        String jsonStr = sh.makeServiceCall(url);
        Random rand = new Random();
        int random = rand.nextInt(10);

        //return jsonStr;
        Log.e(TAG, "Response from url: " + jsonStr);
        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);
                // Getting JSON Array node
                JSONArray data = jsonObj.getJSONArray("data").getJSONArray(0);
                for (int i = 0; i < data.length(); i++) {
                    JSONObject q = data.getJSONObject(i);
                    String quotes = q.getString("quotes");


                    HashMap<String, String> list = new HashMap<>();
                    list.put("quotes", quotes);
                    quoteList.add(list);
                }

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

        return String.valueOf(quoteList);

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //ListAdapter adapter = new SimpleAdapter(MainActivity.this, quoteList,
           //     R.layout.activity_main, new String[]{ "quote"},
         //       new int[]{R.id.quote});
       // lv.setAdapter(adapter);
       TextView textView = findViewById(R.id.quote);
       textView.setText(s);

    }
}
}
