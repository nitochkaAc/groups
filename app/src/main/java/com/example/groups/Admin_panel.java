package com.example.groups;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Admin_panel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

    }
    @Override
    public void onBackPressed() {
        openQuitDialog();
    }

    private void openQuitDialog()
    {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(Admin_panel.this);
        quitDialog.setTitle("Ви впевнені, що хочете вийти?");
        quitDialog.setPositiveButton("Так", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                Intent i = new Intent(Admin_panel.this, MainActivity.class);
                startActivity(i);
            }
        });
        quitDialog.setNegativeButton("Ні", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        quitDialog.show();
    }


    public void onClients(View view) {
        Intent clientIntent = new Intent(Admin_panel.this, ClientActivity.class);
        startActivity(clientIntent);
    }

    public void onGroups(View view) {
        Intent sgIntent = new Intent(Admin_panel.this, StudyGroupActivity.class);
        startActivity(sgIntent);
    }

    public void onTeachers(View view) {
        Intent tIntent = new Intent(Admin_panel.this, TeachersActivity.class);
        startActivity(tIntent);
    }

    public void onCourses(View view) {
        Intent courseIntent = new Intent(Admin_panel.this, CourseActivity.class);
        startActivity(courseIntent);
    }

    public void onDiscipline(View view) {
        Intent dIntent = new Intent(Admin_panel.this, DisciplineActivity.class);
        startActivity(dIntent);
    }

    public void onUsers(View view) {
        Intent userIntent = new Intent(Admin_panel.this, ActivityUsers.class);
        startActivity(userIntent);
    }
}