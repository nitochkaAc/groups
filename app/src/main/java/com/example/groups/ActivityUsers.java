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

public class ActivityUsers extends AppCompatActivity {
    ListView listView;
    users[] person;
    static String[] userString;
    static String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        listView = (ListView)findViewById(R.id.listUsers);
        new UsersAsyncTask().execute("http://192.168.31.93:8000/users?");
    }


    public void onCreateUser(View view) {
        Intent createUserIntent = new Intent(this, ChangeUser.class);
        createUserIntent.putExtra("edit", false);
        startActivity(createUserIntent);
    }

    private class UsersAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            return MainActivity.GET(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            Gson gson = new Gson();
            person = gson.fromJson(s, users[].class);
            userString = new String[person.length];
            for (int i = 0; i < person.length; i++) {
                userString[i] = " " + person[i].Login;
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ActivityUsers.this, android.R.layout.simple_list_item_1, userString);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent userChange = new Intent(ActivityUsers.this, ChangeUser.class);

                    userChange.putExtra("edit", true);
                    userChange.putExtra("id", person[position].Id);
                    userChange.putExtra("login", person[position].Login.toString());


                    startActivity(userChange);
                }
            });

        }
    }
}