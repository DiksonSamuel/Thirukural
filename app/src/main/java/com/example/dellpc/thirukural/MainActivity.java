package com.example.dellpc.thirukural;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    public static String jbl;
    //String ans;
    TextView tv;
    Toolbar toolbar;
    //private AppCompatDelegate delegate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar= (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        tv = (TextView) findViewById(R.id.textView);


        Button se = (Button) findViewById(R.id.button2);

        Button bt = (Button) findViewById(R.id.button);
        if (bt != null) {
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int random = (int) (Math.random() * 1331 + 1);
                    String ou1 = output(random);

                    Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtra("weapontitle", ou1);
                    startActivity(intent);
                    //alaram();

                }
            });
        }
        jbl= loadJSONFromAsset();
        alaram();
        se.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Search.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public  String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = this.getAssets().open("thirukkural.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return json;
    }

    public static String output(int ran) {
        String ans="";
        try {
            //String finaa = loadJSONFromAsset();
            JSONArray ar = new JSONArray(jbl);
            StringBuffer nw = new StringBuffer();

            for (int i = 0; i < 1331; i++) {
                if (i == ran) {
                    JSONObject obj = ar.getJSONObject(i);

                    int number = Integer.parseInt(obj.optString("Number"));
                    String line1 = obj.optString("Line1").toString();
                    String line2 = obj.optString("Line2").toString();
                    String translation = obj.optString("Translation").toString();
                    String mv = obj.optString("mv").toString();
                    String sp = obj.optString("sp").toString();
                    String mk = obj.optString("mk").toString();
                    String exp = obj.optString("explanation").toString();
                    String cop = obj.optString("couplet").toString();
                    String tran1 = obj.optString("transliteration1").toString();
                    String tran2 = obj.optString("transliteration2").toString();
                    nw.append(number + "\n" + line1 + "\n" + line2 + "\n" + "\n" + "TRANSLATION" + "\n" + translation + "\n" + "\n" + "EXPLANATION(Tamil):" + "\n" + mv + "\n" + sp + "\n" + mk + "\n" + "\n" + "EXPLANATION(eng):" + "\n" + exp + "\n" + cop + "\n" + "\n" + "TRANSLITERATION:" + "\n" + tran1 + "\n" + tran2);
                    ans = nw.toString();


                }


            } }catch(JSONException e){
            e.printStackTrace();
        }
        return ans;
    }
    public static String outpu(int ran) {
        String ans="";
        try {
            //String finaa = loadJSONFromAsset();
            JSONArray ar = new JSONArray(jbl);
            StringBuffer nw = new StringBuffer();

            for (int i = 0; i < 1331; i++) {
                if (i == ran) {
                    JSONObject obj = ar.getJSONObject(i);

                    int number = Integer.parseInt(obj.optString("Number"));
                    String line1 = obj.optString("Line1").toString();
                    String line2 = obj.optString("Line2").toString();
                    String translation = obj.optString("Translation").toString();
                    String mv = obj.optString("mv").toString();
                    String sp = obj.optString("sp").toString();
                    String mk = obj.optString("mk").toString();
                    String exp = obj.optString("explanation").toString();
                    String cop = obj.optString("couplet").toString();
                    String tran1 = obj.optString("transliteration1").toString();
                    String tran2 = obj.optString("transliteration2").toString();
                    nw.append(number + "\n" + line1 + "\n" + line2 + "\n" + "EXPLANATION:" + "\n" + mv + "\n" + sp + "\n" + mk + "\n" + "\n"  + exp + "\n" );


                }


            } }catch(JSONException e){
            e.printStackTrace();
        }
        return ans;
    }

    public void alaram() {
        Calendar calen= Calendar.getInstance();
        calen.set(Calendar.HOUR_OF_DAY,8);
        calen.set(Calendar.MINUTE,1);
        calen.set(Calendar.SECOND,15);
        Intent alar = new Intent(getApplicationContext(),NotificationReciever.class);
        PendingIntent pend= PendingIntent.getBroadcast(getApplicationContext(),100,alar,PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager ala = (AlarmManager) getSystemService(ALARM_SERVICE);
        ala.setRepeating(AlarmManager.RTC_WAKEUP, calen.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pend);
    }

    @Override
    public void onSupportActionModeStarted(ActionMode mode) {

    }

    @Override
    public void onSupportActionModeFinished(ActionMode mode) {

    }

    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        return null;
    }
}
