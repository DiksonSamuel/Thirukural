package com.example.dellpc.thirukural;

import android.app.Activity;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class Search extends Activity {
    String answ;
    EditText num;
    TextView out;
    Button bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        num= (EditText) findViewById(R.id.editText);
        out= (TextView) findViewById(R.id.textView4);
        out.setMovementMethod(new ScrollingMovementMethod());
        bt=(Button) findViewById(R.id.button3);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int numb= Integer.parseInt(num.getText().toString());
                //MainActivity ms = new MainActivity();
                if(numb==0 || numb>1330){
                    Toast.makeText(getApplicationContext(),"THIRRUKURAL HAS ONLY 1330 KURRALS",
                            Toast.LENGTH_LONG).show();
                } else {
                    String an = out(numb - 1);
                    out.setText(an);
                }
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }
    public String loadJSON() {
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

    public String out(int ran) {
        try {
            String finaa = loadJSON();
            JSONArray ar = new JSONArray(finaa);
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
                    //nw.append(number + "\n" + line1 + "\n" + line2 + "\n" + "\n" + "TRANSLATION" + "\n" + translation + "\n" + "\n" + "EXPLANATION(Tamil):" + "\n" + mv + "\n" + sp + "\n" + mk + "\n" + "\n" + "EXPLANATION(eng):" + "\n" + exp + "\n" + cop + "\n" + "\n" + "TRANSLITERATION:" + "\n" + tran1 + "\n" + tran2);
                    //answ = nw.toString();
                    answ= nw.append(number + "\n" + line1 + "\n" + line2).toString();

                }


            } }catch(JSONException e){
            e.printStackTrace();
        }
        return answ;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_exit) {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
            return true;
        }
        if(id==R.id.action_copy){
            String gt= out.getText().toString();
            ClipboardManager clip= (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            clip.setText(gt);
            Toast.makeText(getApplicationContext(),"your kurral is copied to clipboard",Toast.LENGTH_LONG).show();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


}

