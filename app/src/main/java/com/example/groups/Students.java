package com.example.groups;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Students {
    int Id;
    String Name;
    String b_day;
    int FK_StudyGroupId;
    int phone_stud;
    String Name_parent;
    int phone_par;
    String Description;

    public Date getbd() throws ParseException
    {
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
        return df.parse(b_day);
    }
}
