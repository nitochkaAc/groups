package com.example.groups;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class StudyGroupActivity extends AppCompatActivity {


    ListView listView;
    StudyGroups[] studs;
    static String[] studentsString;
    static String result = "";
    int Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_group);
        listView = (ListView)findViewById(R.id.listStuds);
        new StudentsAsyncTask().execute("http://192.168.31.93:8000/groups?");
    }
    public void onDChange(View view) {
        Intent createStudIntent = new Intent(this, SGChangeActivity.class);
        createStudIntent.putExtra("edit", false);
        startActivity(createStudIntent);
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

        @Override
        protected void onPostExecute(StudyGroups[] teachers) {
            studentsString = new String[studs.length];
            for (int i = 0; i < studentsString.length; i++) {
                studentsString[i] = " " + teachers[i].Name;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(StudyGroupActivity.this, android.R.layout.simple_list_item_1, studentsString);
            listView.setAdapter(adapter);
                 listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                   Intent courseChange = new Intent(StudyGroupActivity.this, ChangeCourse.class);

                    courseChange.putExtra("edit", true);
                    courseChange.putExtra("id", teachers[position].Id);
                    courseChange.putExtra("name", teachers[position].Name.toString());
                    courseChange.putExtra("nks", teachers[position].FK_StudyCourseId);
                    courseChange.putExtra("sDate",teachers[position].DateStart);
                    courseChange.putExtra("eDate",teachers[position].DateEnd);
                    courseChange.putExtra("term", teachers[position].FK_TimetableId);
                    courseChange.putExtra("auditory",teachers[position].FK_AuditoryId);
                    courseChange.putExtra("teacher",teachers[position].FK_TeachersgId);

                    startActivity(courseChange);
  }
                });



        }

    }




}