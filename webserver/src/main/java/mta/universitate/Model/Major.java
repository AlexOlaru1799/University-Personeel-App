package mta.universitate.Model;
import java.util.ArrayList;

public class Major {
    private int id;
    private String name;
    private Faculty faculty;
    private ArrayList<Course> courses = new ArrayList<>();

    public static Major fromDB(int id)
    {
        Major M = new Major();
        M.id = id;

        return Database.getInstance().get(M);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }
}
