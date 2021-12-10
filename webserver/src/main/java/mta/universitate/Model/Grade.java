package mta.universitate.Model;
import java.util.Date;

public class Grade {
    private int id;
    private int value;
    private Course course;
    private Date date;
    private Student student;

    public static Grade fromDB(int id){
        Grade G = new Grade();
        G.id = id;

        return Database.getInstance().get(G);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
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
