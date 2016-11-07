package com.example.dellpc.thirukural;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class Today extends Activity {
    String ans;
    TextView tv7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

        tv7= (TextView)findViewById(R.id.textView7);
        tv7.setMovementMethod(new ScrollingMovementMethod());
        // tv7.setText("SUCCESS");
        MainActivity ma = new MainActivity();
        int random = (int) (Math.random() * 1331 + 1);
        String ou = ma.output(random);
        tv7.setText(ou);
    }



}
