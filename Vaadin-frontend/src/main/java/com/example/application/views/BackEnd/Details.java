package com.example.application.views.BackEnd;

public class Details {
    private Course materie;
    private String ora;
    private Classroom sala;
    private Professor profesor;

    public Details(){

    }

    public Details(Course materie, String ora,Classroom sala, Professor prof){
        this.materie=materie;
        this.ora=ora;
        this.sala=sala;
        this.profesor=prof;
    }

    public Course getCourse(){
        return this.materie;
    }

    public String getOra(){
        return this.ora;
    }

    public Classroom getClassroom(){
        return this.sala;
    }

    public Professor getProfesor(){
        return this.profesor;
    }
}
