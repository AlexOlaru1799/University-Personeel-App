package com.example.application.views.BackEnd;


public class Student extends Person {
    private String id;
    private StudyGroup studyGroup;
    private StudyYear studyYear;
    private int income;
    private int specializationId;


    public Student(String name, String surname,
                   StudyGroup studyGroup, StudyYear studyYear, int solda, int specializationId)
    {
        super(name, surname);
        this.studyGroup = studyGroup;
        this.studyYear = studyYear;
        this.income = solda;
        this.specializationId = specializationId;
    }

    @Override
    public void addDocument(){
        System.out.print("AddDocument");
    }

    public void fillReport()
    {
        System.out.print("fillReport");
    }

    public int getIncome() {
        return income;
    }

    public int getSpecialization() {
        return specializationId;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public StudyYear getStudyYear() {
        return studyYear;
    }
}
