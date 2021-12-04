package com.example.application.views.BackEnd;

public class Professor extends Employee {

    public Professor()
    {
        super();
    }
    public Professor(String name, String surname)
    {
        super(name, surname);
    }

    public void giveGrade(Student S, int grade)
    {
        System.out.print("Professor gave grade " +
                grade + "to student " +S.getName());
    }

}
