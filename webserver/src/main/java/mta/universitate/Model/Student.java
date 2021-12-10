package mta.universitate.Model;

import java.sql.ResultSet;

public class Student extends Person {
    private String id;
    private StudyGroup studyGroup;
    private StudyYear studyYear;
    private int income;
    private Major major;
    

    public Student(String name, String surname,
                   StudyGroup studyGroup, StudyYear studyYear, int income, Major major)
    {
        super(name, surname);
        this.studyGroup = studyGroup;
        this.studyYear = studyYear;
        this.income = income;
        this.major = major;
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

    public Major getMajor() {
        return major;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public StudyYear getStudyYear() {
        return studyYear;
    }
}
