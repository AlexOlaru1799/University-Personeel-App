package mta.universitate.Model;

import mta.universitate.Utils.JsonParser;

public class Course extends JsonParser {
    private Integer id;
    private String name;
    private Integer credits;
    private Professor professor;

    public static Course fromDB(int id){
        Course C = new Course();
        C.setId(id);

        return Database.getInstance().get(C);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
