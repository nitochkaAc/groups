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

public class TeachersActivity extends AppCompatActivity {
    ListView listView;
    Teacher[] courses;
    static String[] coursesString;
    static String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);
        listView = (ListView)findViewById(R.id.listStuds);
        new CoursesAsyncTask().execute("http://192.168.31.93:8000/teachers?");

    }
    public void onTChange(View view) {

        Intent createCourseIntent = new Intent(this, CreateTeachActivity.class);
        createCourseIntent.putExtra("edit", false);
        startActivity(createCourseIntent);
    }
    private class CoursesAsyncTask extends AsyncTask<String, Void, Teacher[]> {
        @Override
        protected Teacher[] doInBackground(String... params) {
            Log.d("Input: ", params[0]);
            String res = MainActivity.GET(params[0]);
            Gson gson = new Gson();
            courses = gson.fromJson(res, Teacher[].class);

            return courses;
        }

        @Override
        protected void onPostExecute(Teacher[] teachers) {
            coursesString = new String[courses.length];
            for (int i = 0; i < courses.length; i++) {
                coursesString[i] = " " + courses[i].Name;
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(TeachersActivity.this, android.R.layout.simple_list_item_1, coursesString);
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

}