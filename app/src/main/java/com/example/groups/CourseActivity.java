package com.example.groups;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;



import com.google.gson.Gson;

public class CourseActivity extends Activity {
    ListView listView;
    Courses[] courses;
    static String[] coursesString;
    static String result = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        listView = (ListView)findViewById(R.id.listStuds);
        new CourseActivity.CoursesAsyncTask().execute("http://192.168.31.93:8000/courses?");

    }
    public void onCreateCourse(View view) {
        Intent createCourseIntent = new Intent(this, ChangeCourse.class);
        createCourseIntent.putExtra("edit", false);
        startActivity(createCourseIntent);
    }
    private class CoursesAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            Log.d("Input: ", params[0]);
            return MainActivity.GET(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            Log.d("Input: ", s);
            Gson gson = new Gson();
            courses = gson.fromJson(s, Courses[].class);
           coursesString = new String[courses.length];
            for (int i = 0; i < courses.length; i++) {
                coursesString[i] = " " +courses[i].Name + courses[i].Degree_course;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(CourseActivity.this, android.R.layout.simple_list_item_1,coursesString);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.d("Input:", ""+courses[position].Id+courses[position].Name.toString()+courses[position].FK_DisciplineId+courses[position].Degree_course+courses[position].Description );

                    Intent courseChange = new Intent(CourseActivity.this, ChangeCourse.class);

                    courseChange.putExtra("edit", true);
                   courseChange.putExtra("id", courses[position].Id);
                    courseChange.putExtra("name", courses[position].Name.toString());
                    courseChange.putExtra("discipline", courses[position].FK_DisciplineId);
                   courseChange.putExtra("degree", courses[position].Degree_course);
                   if(courses[position].Description==null)
                   {
                       courses[position].Description="nothing";
                   }
                    courseChange.putExtra("description", courses[position].Description.toString());
                    startActivity(courseChange);


                }
            });

        }

    }
}