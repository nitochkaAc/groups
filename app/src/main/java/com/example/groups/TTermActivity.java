package com.example.groups;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class TTermActivity extends AppCompatActivity {
EditText et1, et2, et3, et4, et5, et6, et7;
String t1="",t2="",t3="",t4="",t5="",t6="",t7="";
    StudyGroups[] studs;
    static String[] studentsString;
    static String result = "";
    int Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_term);
        Id = getIntent().getExtras().getInt("id");
        et1 = (EditText)findViewById(R.id.firstET);
        et2 = (EditText)findViewById(R.id.secondET);
        et3 = (EditText)findViewById(R.id.thirdET);
        et4 = (EditText)findViewById(R.id.fourthET);
        et5 = (EditText)findViewById(R.id.fivethET);
        et6 = (EditText)findViewById(R.id.sixthET);
        et7 = (EditText)findViewById(R.id.seventhET);
        new StudentsAsyncTask().execute("http://192.168.31.93:8000/groupsFind?Id="+(Id-1));
    }
    private class StudentsAsyncTask extends AsyncTask<String, Void, StudyGroups[]> {
        @Override
        protected StudyGroups[] doInBackground(String... params) {
            Log.d("Input:", params[0]);
            String res = MainActivity.GET(params[0]);
            Log.d("Input:", res);
            Gson gson = new Gson();
            studs = gson.fromJson(res, StudyGroups[].class);

            return studs;
        }
 public void makeContent()
 {
     et1.setText(""+t1);
     et2.setText(""+t2);
     et3.setText(""+t3);
     et4.setText(""+t4);
     et5.setText(""+t5);
     et6.setText(""+t6);
     et7.setText(""+t7);



 }
        @Override
        protected void onPostExecute(StudyGroups[] teachers) {
            studentsString = new String[studs.length];
            for (int i = 0; i < studentsString.length; i++) {
                if (teachers[i].FK_TimetableId<=3)
                {
                    t1 += "Група: "+teachers[i].Name+" в аудиторії№"+teachers[i].FK_AuditoryId+"\n";
                }
                if (teachers[i].FK_TimetableId<=6&&teachers[i].FK_TimetableId>=4)
                {
                    t2 += "Група: "+teachers[i].Name+" в аудиторії№"+teachers[i].FK_AuditoryId+"\n";
                }
                if (teachers[i].FK_TimetableId<=9&&teachers[i].FK_TimetableId>=7)
                {
                    t3 += "Група: "+teachers[i].Name+" в аудиторії№"+teachers[i].FK_AuditoryId+"\n";
                }
                if (teachers[i].FK_TimetableId<=12&&teachers[i].FK_TimetableId>=10)
                {
                    t4 += "Група: "+teachers[i].Name+" в аудиторії№"+teachers[i].FK_AuditoryId+"\n";
                }
                if (teachers[i].FK_TimetableId<=15&&teachers[i].FK_TimetableId>=13)
                {
                    t5 += "Група: "+teachers[i].Name+" в аудиторії№"+teachers[i].FK_AuditoryId+"\n";
                }
                if (teachers[i].FK_TimetableId<=18&&teachers[i].FK_TimetableId>=16)
                {
                    t6 += "Група: "+teachers[i].Name+" в аудиторії№"+teachers[i].FK_AuditoryId+"\n";
                }
                if (teachers[i].FK_TimetableId<=21&&teachers[i].FK_TimetableId>=19)
                {
                    t7 += "Група: "+teachers[i].Name+" в аудиторії№"+teachers[i].FK_AuditoryId+"\n";
                }

            }
            makeContent();
        }
    }
}