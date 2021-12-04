package com.example.application.views.BackEnd;


import java.util.ArrayList;

public class Major {
    private ArrayList<StudyGroup> groups = new ArrayList<>();
    private ArrayList<Course> courses = new ArrayList<>();
    private String name;

    public ArrayList<StudyGroup> getStudyGroups() {
        return this.groups;
    }

    public ArrayList<Course> getCourses() {
        return this.courses;
    }

    public String getName() {
        return this.name;
    }
}
