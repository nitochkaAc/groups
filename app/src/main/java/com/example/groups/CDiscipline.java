package com.example.groups;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.security.NoSuchAlgorithmException;

import okhttp3.OkHttpClient;

public class CDiscipline extends AppCompatActivity {
    TextView idTV;
    static EditText nameET;
    static EditText AgeStartET;
    static EditText AgeFinishET;
    static EditText DescriptionET;
    static boolean flag = false;
    static int id;
    static int AgeStart;
    static int AgeFinish;
    static String Description;
    static Discipline [] disc;
    static Discipline d;
    String res;
    Button saveBtn;
    static OkHttpClient client = new OkHttpClient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdiscipline);
        initializeComponent();
      //  getKeys();
        saveBtn = (Button) findViewById(R.id.btnSave);
        new CoursesAsyncTask().execute("http://192.168.31.93:8000/discipNew?");
   /*     if (flag) {
            idTV.setText("" + id);
            nameET.setText("" + login);
            passwordET.setText("" + password);
        }
        else{

            idTV.setEnabled(false);
            nameET.setText("");
        AgeStartET.setText(""+0);
        AgeFinishET.setText(""+99);
        DescriptionET.setText("");
        } */
    }

    private void initializeComponent()
    {
        idTV = (TextView)findViewById(R.id.idShow);
        nameET = (EditText)findViewById(R.id.nameET);
        AgeStartET = (EditText)findViewById(R.id.degreeET);
        AgeFinishET = (EditText)findViewById(R.id.sAgeET);
        DescriptionET = (EditText)findViewById(R.id.descriptionET);
    }
    public void btnOnDelete(View view) {
    }
    private class CoursesAsyncTask extends AsyncTask<String, Void, Discipline[]> {
        @Override
        protected Discipline[] doInBackground(String... params) {
            res = MainActivity.GET(params[0]);
            Gson gson = new Gson();
            disc = gson.fromJson(res, Discipline[].class);
            return disc;
        }
    }

    public static boolean isNotEmpty(EditText Name, EditText Password, EditText pole)
    {
        int n=1;
        if(Name.getText().toString().trim().length()==0)
        {
            Name.setError("Поле Name не може бути порожнім!");
            n=n*0;


        }
        if(Password.getText().toString().trim().length()==0)
        {
            Password.setError("Поле Age не може бути порожнім!");
            n=n*0;

        }
        if(pole.getText().toString().trim().length()==0)
        {
            Password.setError("Поле Age не може бути порожнім!");
            n=n*0;

        }


        if(n==1) {

            return true;
        }
        else return false;
    }

    public void btnOnSave(View view) throws NoSuchAlgorithmException {

        if(isNotEmpty(nameET, AgeStartET, AgeFinishET))
        {
        //    dataToRequest(d, id);
        //    dataToRequest(d, id);

        }
        else {

            Toast.makeText(getBaseContext(), "Заповніть, будь ласка, всі поля!", Toast.LENGTH_LONG).show();
        }

    }
//    public users dataToRequest(users[] u, int id) throws NoSuchAlgorithmException {
//        p = new users();
//        login = loginEt.getText().toString().trim();
//        password = passwordET.getText().toString().trim();
//        if (flag) {
//
//            for (int i = 0; i < u.length; i++) {
//                if (u[i].Id == id) {
//                    p = u[i];
//                    p.Login = login;
//                    p.Password = password;
//                }
//            }
//
//            new ChangeUser.PostAsyncTask().execute("http://192.168.31.93:8000/usersUpdate?Id="+id+"&Login="+login+"&Password="+password);
//
//        }
//        else {
//            p.Login = login;
//            p.Password = password;
//            Log.d("Input:", "http://192.168.31.93:8000/usersNew?Login=" + login + "&Password=" + password);
//
//            new ChangeUser.PostAsyncTask().execute("http://192.168.31.93:8000/usersNew?Login=" + login + "&Password=" + password);
//        }
//        return p;
//    }
//    private class PostAsyncTask extends AsyncTask<String, Void, String> {
//        @Override
//        protected String doInBackground(String... params) {
//            try {
//                return postChangeCourse(params[0]);
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (NoSuchAlgorithmException e) {
//                e.printStackTrace();
//            }
//            return "";
//        }


        protected void onPostExecute(String s) {
            if (res.equals("User already exists"))
                Toast.makeText(getBaseContext(), "Користувач з таким логіном уже існує!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getBaseContext(), "Зміни внесені успішно!", Toast.LENGTH_LONG).show();
        }
    }

//    public String postChangeCourse (String url) throws IOException, NoSuchAlgorithmException {
//        try{
//            Request request = new Request.Builder()
//                    .url(url)
//                    .build();
//            try (Response response = client.newCall(request).execute()) {
//
//                // Toast.makeText(getBaseContext(), "Received!"+response.body().toString(), Toast.LENGTH_LONG).show();
//
//                Log.d("Input:", response.body().string());
//                return response.body().string();
//            }
//        } catch (IOException e)
//        {
//
//            return ""+e;
//        }
//
//    }

