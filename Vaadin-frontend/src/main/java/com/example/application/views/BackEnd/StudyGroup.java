package com.example.application.views.BackEnd;


public class StudyGroup {
    private int id;
    private Professor mentor;
    private Student students;
    private String groupName;

    public StudyGroup(int id)
    {
        this.id = id;
    }

    public Professor getMentor() {
        return this.mentor;
    }

    public Student getStudents() {
        return this.students;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getId() {
        return id;
    }
}
