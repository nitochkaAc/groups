package com.example.groups;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.security.NoSuchAlgorithmException;

import okhttp3.OkHttpClient;

public class StudentsChange extends AppCompatActivity {
    TextView idTV;
    static EditText nameET;
    static EditText sphoneET;
    static EditText nameperET;
    static EditText phoneperET;
    static EditText descriptionET;

    static boolean flag = false;
    static int id;
    static String name;
    static String b_day;
    static String sphone;
    static String namepar;
    static String phoneper;
    static String description;

    static Students[] students;

    String[] stud;

    Button saveBtn;
    String res;

    static OkHttpClient client = new OkHttpClient();
    static Students c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_course);
        initializeComponent();
        getKeys();
        saveBtn = (Button) findViewById(R.id.btnSave);
     //   new CoursesAsyncTask().execute("http://192.168.31.93:8000/students?");
        if (flag) {
            idTV.setText("" + id);
            nameET.setText(""+ name);
            sphoneET.setText(""+sphone);
            nameperET.setText(""+namepar);
            phoneperET.setText(""+phoneper);
           descriptionET.setText(""+description);
        } else {
            idTV.setEnabled(false);
            nameET.setText("");
            sphoneET.setText("");
            nameperET.setText("");
            phoneperET.setText("");
            descriptionET.setText("");
        }


    }
    // Метод для ініціалізації компонентів (прикріплення до змінних елементів)
    private void initializeComponent() {
        idTV = (TextView) findViewById(R.id.idShow);
         nameET= (EditText) findViewById(R.id.nameET);
        sphoneET = (EditText) findViewById(R.id.sphoneET);
        nameperET = (EditText)findViewById(R.id.nameParET);
        phoneperET = (EditText)findViewById(R.id.phoneParET);
        descriptionET = (EditText) findViewById(R.id.descriptionET);

    }
    // Метод, для отримання інформації для редагування з попередньої форми
    private void getKeys() {

        flag = getIntent().getExtras().getBoolean("edit");

        if (flag) {
            id = getIntent().getExtras().getInt("id");
            name = getIntent().getExtras().getString("name");
     //       FK_DesceplineId = getIntent().getExtras().getInt("discipline");
       //     degree = getIntent().getExtras().getInt("degree");
            description = getIntent().getExtras().getString("description");
        }
    }

    public static boolean isNotEmpty(EditText Name, EditText degree)
    {
        int n=1;
        if(Name.getText().toString().trim().length()==0)
        {
            Name.setError("Поле Назва не може бути порожнім!");
            n=n*0;
            Log.d("Input:", "It works##5"+n);

        }
        if(degree.getText().toString().trim().length()==0)
        {
            degree.setError("Поле Модуль не може бути порожнім!");
            n=n*0;
            Log.d("Input:", "It works##5"+n);
        }


        if(n==1) {
            Log.d("Input:", "It works##5"+n);
            return true;
        }
        else return false;
    }

    public void btnOnSave(View view) throws NoSuchAlgorithmException {
        Log.d("Input:", "ItWorks#2");
     //   if(isNotEmpty(Name, Degree))
//        {
//            dataToRequest(courses, id);
//
//        }
//        else {
//
//            Toast.makeText(getBaseContext(), "Заповніть, будь ласка, всі поля!", Toast.LENGTH_LONG).show();
//        }

    }

    public void setDate(View view) {
    }
   /* public Courses dataToRequest(Courses[]C, int id) throws NoSuchAlgorithmException {
        c = new Courses();
        name = Name.getText().toString().trim();
        degree = Integer.parseInt(Degree.getText().toString().trim());
        description = Description.getText().toString().trim();


        if (flag) {

            for (int i = 0; i < C.length; i++) {
                if (C[i].Id == id) {
                    c = C[i];
                    c.Name = name;
                    c.Degree_course = degree;
                    c.Description = description;
                    int desciplineIndex = DiciplineC.getSelectedItemPosition();
                    c.FK_DisciplineId = disciplines[desciplineIndex].Id;
                }
            }
            Log.d("Input:", "ItWorks#7:"+c.Name+c.Degree_course+c.Description+c.FK_DisciplineId);
            Log.d("Input:", "http://192.168.31.93:8000/updateCourse?Id="+id+"&Name="+name+"&FK_DisciplineId="+FK_DesceplineId+"&Degree_course="+degree+"&Description="+description+"");
            new ChangeCourse.PostAsyncTask().execute("http://192.168.31.93:8000/updateCourse?Id="+id+"&Name="+name+"&FK_DisciplineId="+FK_DesceplineId+"&Degree_course="+degree+"&Description="+description);

        } else {

            c.Name = name;
            c.Degree_course = degree;
            c.Description = description;
            Log.d("Input:", "ItWorks#7:"+c.Name+c.Degree_course+c.Description+c.FK_DisciplineId);
            Log.d("Input:","http://192.168.31.93:8000/saveCourse?Name="+name+"&FK_DisciplineId="+DiciplineC.getSelectedItemPosition()+"&Degree_course="+degree+"&Description="+description);

            new ChangeCourse.PostAsyncTask().execute("http://192.168.31.93:8000/saveCourse?Name="+name+"&FK_DisciplineId="+DiciplineC.getSelectedItemPosition()+"&Degree_course="+degree+"&Description="+description);
            int desciplineIndex = DiciplineC.getSelectedItemPosition();
            c.FK_DisciplineId = disciplines[desciplineIndex].Id;

        }


        return c;
    }

    private class CoursesAsyncTask extends AsyncTask<String, Void, Courses[]> {
        @Override
        protected Courses[] doInBackground(String... params) {
            res = MainActivity.GET(params[0]);
            Gson gson = new Gson();
            courses = gson.fromJson(res, Courses[].class);
            return courses;
        }
    }
    private class PostAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                return postChangeCourse(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            if (res.equals("User already exists"))
                Toast.makeText(getBaseContext(), "Користувач з таким логіном уже існує!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getBaseContext(), "Зміни внесені успішно!", Toast.LENGTH_LONG).show();
        }
    }

    public String postChangeCourse (String url) throws IOException, NoSuchAlgorithmException {
        try{
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            try (Response response = client.newCall(request).execute()) {

                // Toast.makeText(getBaseContext(), "Received!"+response.body().toString(), Toast.LENGTH_LONG).show();

                Log.d("Input:", response.body().string());
                return response.body().string();
            }
        } catch (IOException e)
        {

            return ""+e;
        }



    }

    public void btnOnDelete(View view) {
        finish();
        new ChangeCourse.DeleteUserAsyncTask().execute("http://192.168.31.93:8000/deleteCourse?Id="+id);
    }


    private class DeleteUserAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                return postChangeCourse(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return "";
        }
        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(getBaseContext(), "Обраного користувача видалено!", Toast.LENGTH_LONG).show();
        }
    }


    static Students study;
    String res;
    Button saveBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_change);
    }

    public void setDate(View view) {
    }

    public void btnOnSave(View view) {
    } */
}