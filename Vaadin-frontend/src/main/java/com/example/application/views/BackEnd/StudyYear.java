package com.example.application.views.BackEnd;


import com.example.application.views.BackEnd.Major;

import java.util.ArrayList;

public class StudyYear {

    int year;
    ArrayList<Major> majors = new ArrayList<>();

    public StudyYear(int ye)
    {
        this.year = ye;
    }


    public int getYear()
    {
        return this.year;
    }

    public ArrayList<Major> getMajors() {
        return this.majors;
    }
}
