package mta.universitate.Model;
import mta.universitate.Utils.JsonParser;

import java.util.Date;

public class Grade extends JsonParser {
    private Integer id;
    private Integer value;
    private Course course;
    private Date date;
    private Student student;

    public static Grade fromDB(int id){
        Grade G = new Grade();
        G.id = id;

        return Database.getInstance().get(G);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
