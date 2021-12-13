package mta.universitate.Model;

import mta.universitate.Utils.JsonParser;

public class Module extends JsonParser {
    private int id;
    private String kind;
    private Course course;
    private Professor professor;

    public static Module fromDB(int id){
        Module M=new Module();
        M.setId(id);

        return Database.getInstance().get(M);
    }

    public int getId() {
        return id;
    }

    public String getKind() {
        return kind;
    }

    public Course getCourse() {
        return course;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
