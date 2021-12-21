package com.example.application.components;

public class Course {
    private String name;
    private int credits;
    private String professorName;
    private String professorSurname;



    public String getCourse() {
        return name;
    }

    public void setCourse(String name) {
        this.name = name;
    }

    public String getSurname() {
        return professorSurname;
    }

    public void setSurname(String surname) {
        this.professorSurname = surname;
    }

    public String getName() {
        return professorName;
    }

    public void setName(String name) {
        this.professorName = name;
    }


    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits_) {
        this.credits = credits_;
    }
}
