package com.example.groups;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    EditText login, password;
    String log, pass;
    int userID;
    static String result = "";
    static OkHttpClient client = new OkHttpClient();
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeComponent();
    }
    private void initializeComponent()
    {
        login = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
    }
    public boolean isConnected()
    {
        ConnectivityManager connMgr = (ConnectivityManager)getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if(networkInfo!=null&&networkInfo.isConnected())
        {
            return  true;
        }
        else
        {
            return false;
        }
    }



    public static  String GET(String url){
        try{
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try (Response response = client.newCall(request).execute()) {
            //    Log.d("Input:", response.body().string());
            // Toast.makeText(getBaseContext(), "Received!"+response.body().toString(), Toast.LENGTH_LONG).show();


            return response.body().string();
        }
    } catch (IOException e)
    {

        return ""+e;
    }


}

private class HttpAsyncTask extends AsyncTask<String, Void, String>
{
    @Override
    protected String doInBackground(String... params) {
        //  Toast.makeText(getBaseContext(), "Received!"+params[0], Toast.LENGTH_LONG).show();
        //    Log.d("Input:", params[0]);
        return GET(params[0]);

    }
    @Override
    protected void onPostExecute(String result) {

        Log.d("Input:", result);

        Gson gson = new Gson();

        users user = gson.fromJson(result, users.class);

        Log.d("Input:", user.Login);
        String name=user.Login;
        userID= user.Id;
        if(userID==1) {

            Log.d("Input:", result);
            Intent adminIntent = new Intent(MainActivity.this, Admin_panel.class);
            startActivity(adminIntent);
        }
        else if(userID>1) {

            Log.d("Input:", result);
            Intent teacherIntent = new Intent(MainActivity.this, MainTeacher.class);
            teacherIntent.putExtra("id", userID);
            teacherIntent.putExtra("name", name);
            startActivity(teacherIntent);
        }
        else
        {

        }
    }
}
    public void onBtnAutorization(View view) throws InterruptedException {
        log = login.getText().toString().trim();
        pass=password.getText().toString().trim();
        if(isConnected())
        {
            String url ="http://192.168.31.93:8000/login?login="+log+"&password="+pass;
            Log.d("InputStream:",url);
            new HttpAsyncTask().execute(url);

        }
         else
       Toast.makeText(getBaseContext(), "NO connection", Toast.LENGTH_LONG).show();

    }
}