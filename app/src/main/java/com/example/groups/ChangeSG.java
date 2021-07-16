package com.example.groups;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;

public class ChangeSG extends AppCompatActivity {
    TextView idTV;
    static EditText nameET;
    static Spinner nkS;
    static Button startDate;
    static Button endDate;
    static Spinner termS;
    static Spinner auditoryS;
    static Spinner teacherS;

    static boolean flag = false;
    static int id;
    static String name;
    static int FK_nk;
    static String start;
    static String end;
    static int FK_term;
    static int FK_auditory;
    static int FK_teacher;

    static StudyGroups[] sg;
    static Courses[] cses;
    static TimeTable[] tt;
    static  Auditory[] auditories;
    static  Teacher[] teachers;

    String[] course;
    String[] timeTable;
    String[] audit;
    String[] teach;

    static StudyGroups sgroups;
    String res;
    Button saveBtn;

    static OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeComponent();
        setContentView(R.layout.activity_change_s_g);
    //    getKeys();
        new CoursesAsyncTask().execute("http://192.168.31.93:8000/courses?");
        new TermAsyncTask().execute("http://192.168.31.93:8000/terms?");
     //   new AuditoryAsyncTask().execute("http://192.168.31.93:8000/auditories?");
      //  new TeacherAsyncTask().execute("http://192.168.31.93:8000/teachers?");
    }

    private class CoursesAsyncTask extends AsyncTask<String, Void, StudyGroups[]> {
        @Override
        protected StudyGroups[] doInBackground(String... params) {
            res = MainActivity.GET(params[0]);
            Gson gson = new Gson();
            sg = gson.fromJson(res, StudyGroups[].class);
            return sg;
        }
    }

    private class TermAsyncTask extends AsyncTask<String, Void, TimeTable[]> {
        @Override
        protected TimeTable[] doInBackground(String... params) {
            res = MainActivity.GET(params[0]);
            Gson gson = new Gson();
            tt = gson.fromJson(res, TimeTable[].class);
            return tt;
        }
    }

    private void initializeComponent()
    {
        idTV = (TextView)findViewById(R.id.idShow);
        nameET = (EditText)findViewById(R.id.nameET);
        nkS = (Spinner)findViewById(R.id.knSpinner);
        startDate = (Button)findViewById(R.id.dateStart);
        endDate =(Button)findViewById(R.id.dateEnd) ;
        termS = (Spinner)findViewById(R.id.termSpinner);
        auditoryS = (Spinner)findViewById(R.id.audSpinner);
        teacherS = (Spinner)findViewById(R.id.teacherSpinner);
    }

    public void onStart(View view) {
    }

    public void onEnd(View view) {
    }

    public void btnOnSave(View view) {
    }

    public void btnOnDelete(View view) {
    }
    //
       // getKeys();
       // saveBtn = (Button) findViewById(R.id.btnSave);
       // new CoursesAsyncTask().execute("http://192.168.31.93:8000/users?");
      /*  if (flag) {
            idTV.setText("" + id);
            loginEt.setText("" + login);

            passwordET.setText("" + password);
        }
        else{
            idTV.setEnabled(false);
            loginEt.setText("");
            passwordET.setText("");
        } */
 //   }

 /*
    private void getKeys()
    {
        flag = getIntent().getExtras().getBoolean("edit");
        if(flag)
        {
            id=getIntent().getExtras().getInt("id");
            login = getIntent().getExtras().getString("login");

        }
    }
    public static boolean isNotEmpty(EditText Name, EditText Password)
    {
        int n=1;
        if(Name.getText().toString().trim().length()==0)
        {
            Name.setError("Поле Login не може бути порожнім!");
            n=n*0;


        }
        if(Password.getText().toString().trim().length()==0)
        {
            Password.setError("Поле Password не може бути порожнім!");
            n=n*0;

        }


        if(n==1) {

            return true;
        }
        else return false;
    }

    public void btnOnSave(View view) throws NoSuchAlgorithmException {
//        if(isNotEmpty(loginEt, passwordET))
//        {
//            dataToRequest(use, id);
//
//        }
//        else {
//
//            Toast.makeText(getBaseContext(), "Заповніть, будь ласка, всі поля!", Toast.LENGTH_LONG).show();
//        }
//
//    }
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
//
//        @Override
//        protected void onPostExecute(String s) {
//            if (res.equals("User already exists"))
//                Toast.makeText(getBaseContext(), "Користувач з таким логіном уже існує!", Toast.LENGTH_SHORT).show();
//            else
//                Toast.makeText(getBaseContext(), "Зміни внесені успішно!", Toast.LENGTH_LONG).show();
//        }
//    }
//
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
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_change_s_g);
//    }

    public void btnOnSave(View view) {
    }

    public void btnOnDelete(View view) {
    }

    public void onStart(View view) {
    }

    public void onEnd(View view) {
    } */
}