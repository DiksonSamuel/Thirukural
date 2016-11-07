package com.example.dellpc.thirukural;

        import android.support.v7.app.ActionBarActivity;
        import android.support.v7.widget.ShareActionProvider;
        import android.app.Activity;
        import android.content.ClipboardManager;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.v4.view.MenuItemCompat;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.text.method.ScrollingMovementMethod;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

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

public class Main2Activity extends Activity {
    public static TextView tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        MainActivity m = new MainActivity();
        //m.dispDetail();
        Intent intent =getIntent();
        String txt =intent.getStringExtra("weapontitle");


        tv2 = (TextView) findViewById(R.id.textView2);



        tv2.setMovementMethod(new ScrollingMovementMethod());
        tv2.setText(txt);

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_exit) {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
            return true;
        }
        if(id==R.id.action_copy){
            String gt= tv2.getText().toString();
            ClipboardManager clip= (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            clip.setText(gt);
            Toast.makeText(getApplicationContext(), "your kurral is copied to clipboard", Toast.LENGTH_LONG).show();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

}





