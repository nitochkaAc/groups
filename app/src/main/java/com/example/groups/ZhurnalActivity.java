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

public class ZhurnalActivity extends AppCompatActivity {
    ListView listView;
    Lessons[] studs;
    static String[] studentsString;
    static String result = "";
    static StudyGroups[] sg;
    String[]sgList;
    int[] FK_Sg;

    int Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhurnal);
        listView = (ListView)findViewById(R.id.listStuds);
        Id = getIntent().getExtras().getInt("id");
        Log.d("Input: ", Id+"what's wrong?");
        new StudentsAsyncTask().execute("http://192.168.31.93:8000/lessons?Id="+(Id-1));


    }

// Метод для отримання існуючих дисциплін
private class DisciplineAsyncTask extends AsyncTask<String, Void, StudyGroups[]> {
    @Override
    protected StudyGroups[] doInBackground(String... params) {
        String res = MainActivity.GET(params[0]);
        Gson gson = new Gson();
        sg = gson.fromJson(res, StudyGroups[].class);
        return sg;
    }


    @Override
    protected void onPostExecute(StudyGroups[] d) {
        sgList = new String[sg.length];
        for (int i = 0; i < sg.length; i++) {
            sgList[i] = sg[i].Name;
        }
    }





}

    private class StudentsAsyncTask extends AsyncTask<String, Void, Lessons[]> {
        @Override
        protected Lessons[] doInBackground(String... params) {
            String res = MainActivity.GET(params[0]);
            Gson gson = new Gson();
            studs = gson.fromJson(res, Lessons[].class);

            return studs;
        }

        @Override
        protected void onPostExecute(Lessons[] teachers) {
            studentsString = new String[studs.length];
            FK_Sg = new int[studs.length];
            for (int i = 0; i < studentsString.length; i++) {
                FK_Sg[i] = teachers[i].FK_StudyGroupId;
           //         new DisciplineAsyncTask().execute("http://192.168.31.93:8000/sgroup?Id="+FK_Sg[i]);
                studentsString[i] = " " + teachers[i].Data+ " .Заняття №"+teachers[i].Number;
            }



            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ZhurnalActivity.this, android.R.layout.simple_list_item_1, studentsString);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Intent courseChange = new Intent(ZhurnalActivity.this, ChangeCourse.class);

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