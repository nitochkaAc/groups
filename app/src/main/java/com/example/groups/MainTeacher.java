package com.example.groups;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainTeacher extends AppCompatActivity {
int Id;
String name;
    TextView tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);
        tv2 = (TextView)findViewById(R.id.textView2);
       Id= getIntent().getExtras().getInt("id");
       name = getIntent().getExtras().getString("name");
       tv2.setText("Вхід було здійснено під користувачем "+name);
        Log.d("Input:", ""+Id);
    }

    public void onLessons(View view) {
        Intent lesIntent = new Intent(MainTeacher.this, LessonsActivity.class);
        lesIntent.putExtra("id", Id);
        startActivity(lesIntent);
    }

    public void onGroups(View view) {
        Intent groupIntent = new Intent(MainTeacher.this, GroupsActivity.class);
        groupIntent.putExtra("id", Id);
        startActivity(groupIntent);
    }

    public void onTTerm(View view) {
        Intent ttIntent = new Intent(MainTeacher.this, TTermActivity.class);
        ttIntent.putExtra("id", Id);
        startActivity(ttIntent);
    }
}