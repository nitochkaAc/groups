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

public class LessonsActivity extends AppCompatActivity {

        ListView listView;
        StudyGroups[] studs;
        static String[] studentsString;
        static String result = "";
        int Id;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lessons);
            listView = (ListView)findViewById(R.id.listStuds);
            Id = getIntent().getExtras().getInt("id");
           new StudentsAsyncTask().execute("http://192.168.31.93:8000/groups?");
        }
        public void onDChange(View view) {
            Intent createStudIntent = new Intent(this, ZhurnalActivity.class);

            createStudIntent.putExtra("id", Id);
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

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(LessonsActivity.this, android.R.layout.simple_list_item_1, studentsString);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent courseChange = new Intent(LessonsActivity.this, ChangeCourse.class);

                        courseChange.putExtra("edit", true);
                        //        courseChange.putExtra("id", courses[position].Id);
                        //       courseChange.putExtra("name", courses[position].Name.toString());
                        //      courseChange.putExtra("discipline", courses[position].FK_DisciplineId);
                        //      courseChange.putExtra("degree", courses[position].Degree_course);
                        //     if (courses[position].Description == null) {
                        //         courses[position].Description = "nothing";
                    }
                    //      courseChange.putExtra("description", courses[position].Description.toString());
                    //       startActivity(courseChange);
                    //}
                });



            }

        }




    }


