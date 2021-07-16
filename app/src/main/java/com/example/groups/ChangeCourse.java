package com.example.groups;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChangeCourse extends Activity implements  AdapterView.OnItemSelectedListener
{
    //Поля для вводу інформації
    TextView idTV;
    static EditText Name;
    static EditText Degree;
    static EditText Description;
    static Spinner DiciplineC;

    //Значення для збереження
    static boolean flag = false;
    static int id;
    static int degree;
    static String name;
    static String description;
    static int FK_DesceplineId;

    // Модель даних дисциплін +масив значень Дисциплін
    static Discipline[] disciplines;
    static Courses[] courses;
    String[] disciplinesList;

    Button saveBtn;
    String res;

    static OkHttpClient client = new OkHttpClient();
    static Courses c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_course);
        initializeComponent();
        getKeys();
        saveBtn = (Button) findViewById(R.id.btnSave);
        new CoursesAsyncTask().execute("http://192.168.31.93:8000/courses?");
        new DisciplineAsyncTask().execute("http://192.168.31.93:8000/disciplines?");
      if (flag) {
            idTV.setText("" + id);
            Name.setText(""+ name);

           Degree.setText(""+degree);
            Description.setText(""+description);
            DiciplineC.setEnabled(false);
        } else {
            idTV.setEnabled(false);
            Name.setText("");
            Degree.setText("");
            Description.setText("");
            DiciplineC.setEnabled(true);
        }
      Log.d("Input:", "It works#1");
    }





    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        desciplineItemChange();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // Метод для отримання існуючих дисциплін
    private class DisciplineAsyncTask extends AsyncTask<String, Void, Discipline[]> {
        @Override
        protected Discipline[] doInBackground(String... params) {
            String res = MainActivity.GET(params[0]);
            Gson gson = new Gson();
            disciplines = gson.fromJson(res, Discipline[].class);
            return disciplines;
        }


        @Override
        protected void onPostExecute(Discipline[] d) {
            disciplinesList = new String[disciplines.length];
            for (int i = 0; i < disciplines.length; i++) {
                disciplinesList[i] = disciplines[i].Name;
            }
            Log.d("Input:", "It works#3");
            ArrayAdapter<String> desceplinesAdapter = new ArrayAdapter<String>(ChangeCourse.this, android.R.layout.simple_list_item_1, disciplinesList);
            desceplinesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            DiciplineC.setAdapter(desceplinesAdapter);
           desciplineItemChange();

        }





    }
    // Метод для вибору дисципліни
    public void desciplineItemChange() {
        if (flag) {
            for (int i = 0; i < disciplines.length; i++) {
                if (FK_DesceplineId == disciplines[i].Id) {
                    DiciplineC.setSelection(i);
                    Log.d("Input:", "It works#4");
                }
            }
        }
        else
            DiciplineC.setSelection(0);
        DiciplineC.setEnabled(true);
        DiciplineC.setVisibility(View.VISIBLE);
    }
    // Метод для ініціалізації компонентів (прикріплення до змінних елементів)
    private void initializeComponent() {
        idTV = (TextView) findViewById(R.id.idShow);
        Name = (EditText) findViewById(R.id.nameET);
        Degree = (EditText) findViewById(R.id.degreeET);
        Description = (EditText) findViewById(R.id.descriptionET);
        DiciplineC = (Spinner) findViewById(R.id.disciplinesSpinner);

    }
    // Метод, для отримання інформації для редагування з попередньої форми
    private void getKeys() {

        flag = getIntent().getExtras().getBoolean("edit");

        if (flag) {
            id = getIntent().getExtras().getInt("id");
           name = getIntent().getExtras().getString("name");
            FK_DesceplineId = getIntent().getExtras().getInt("discipline");
           degree = getIntent().getExtras().getInt("degree");
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
        if(isNotEmpty(Name, Degree))
        {
            dataToRequest(courses, id);

        }
        else {

            Toast.makeText(getBaseContext(), "Заповніть, будь ласка, всі поля!", Toast.LENGTH_LONG).show();
        }

    }
    public Courses dataToRequest(Courses[]C, int id) throws NoSuchAlgorithmException {
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
            new PostAsyncTask().execute("http://192.168.31.93:8000/updateCourse?Id="+id+"&Name="+name+"&FK_DisciplineId="+FK_DesceplineId+"&Degree_course="+degree+"&Description="+description);

        } else {

            c.Name = name;
            c.Degree_course = degree;
            c.Description = description;
            Log.d("Input:", "ItWorks#7:"+c.Name+c.Degree_course+c.Description+c.FK_DisciplineId);
            Log.d("Input:","http://192.168.31.93:8000/saveCourse?Name="+name+"&FK_DisciplineId="+DiciplineC.getSelectedItemPosition()+"&Degree_course="+degree+"&Description="+description);

            new PostAsyncTask().execute("http://192.168.31.93:8000/saveCourse?Name="+name+"&FK_DisciplineId="+DiciplineC.getSelectedItemPosition()+"&Degree_course="+degree+"&Description="+description);
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
        new DeleteUserAsyncTask().execute("http://192.168.31.93:8000/deleteCourse?Id="+id);
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

}
