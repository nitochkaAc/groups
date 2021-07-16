package com.example.groups;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class ClientActivity extends AppCompatActivity {
    ListView listView;
    Students[] studs;
    static String[] studentsString;
    static String result = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_client);
        listView = (ListView)findViewById(R.id.listStuds);
        new StudentsAsyncTask().execute("http://192.168.31.93:8000/students?");

    }
    public void onCreateStudents(View view) {
        Intent createStudIntent = new Intent(this, StudentsChange.class);
        createStudIntent.putExtra("edit", false);
        startActivity(createStudIntent);
    }
    private class StudentsAsyncTask extends AsyncTask<String, Void, Students[]> {
        @Override
        protected Students[] doInBackground(String... params) {

            String res = MainActivity.GET(params[0]);
            Gson gson = new Gson();
            studs = gson.fromJson(res, Students[].class);

            return studs;
        }

        @Override
        protected void onPostExecute(Students[] students) {
            studentsString = new String[studs.length];
            for (int i = 0; i < studs.length; i++) {
                studentsString[i] = " " + studs[i].Name;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ClientActivity.this, android.R.layout.simple_list_item_1, studentsString);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               /*     Intent courseChange = new Intent(CourseActivity.this, ChangeCourse.class);

                    courseChange.putExtra("edit", true);
                    courseChange.putExtra("id", courses[position].Id);
                    courseChange.putExtra("name", courses[position].Name.toString());
                    courseChange.putExtra("discipline", courses[position].FK_DisciplineId);
                    courseChange.putExtra("degree", courses[position].Degree_course);
                    if (courses[position].Description == null) {
                        courses[position].Description = "nothing";
                    }
                    courseChange.putExtra("description", courses[position].Description.toString());
                    startActivity(courseChange);

*/
                }
            });

        }

    }
/* Gson gson = new Gson();
            lessonsSequences = gson.fromJson(s, LessonsSequence[].class);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            String shortStartTimeStr, shortEndTimeStr;
            dataAdapter = new String[lessonsSequences.length];

            for(int i=0; i<lessonsSequences.length; i++)
            {
                try {
                    Date StartTime = lessonsSequences[i].getStartTime();
                    shortStartTimeStr = sdf.format(StartTime);
                    Date EndTime = lessonsSequences[i].getEndTime();
                    shortEndTimeStr = sdf.format(EndTime);
                    dataAdapter[i] = lessonsSequences[i].Number+"-й урок: " + shortStartTimeStr + " - " + shortEndTimeStr;
                } */



      /*      ArrayAdapter<String> adapter = new ArrayAdapter<String>(ClientActivity.this, android.R.layout.simple_list_item_1,dataAdapter);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  //  Log.d("Input:", ""+studs[position].Id+courses[position].Name.toString()+courses[position].FK_DisciplineId+courses[position].Degree_course+courses[position].Description );

                    Intent studChange = new Intent(ClientActivity.this, StudentsChange.class);

                    studChange.putExtra("edit", true);
                    studChange.putExtra("id", studs[position].Id);
                    studChange.putExtra("name", studs[position].Name.toString());
                    studChange.putExtra("bd", studs[position].b_day);
                    studChange.putExtra("sg", studs[position].FK_StudyGroupId);
                    studChange.putExtra("ps", studs[position].phone_stud);
                    studChange.putExtra("namep", studs[position].Name_parent);
                    studChange.putExtra("pp", studs[position].phone_par);
                    if(studs[position].Description==null)
                    {
                        studs[position].Description="nothing";
                    }
                    studChange.putExtra("desc", studs[position].Description);
                    startActivity(studChange);
                }
            });

        } */



}