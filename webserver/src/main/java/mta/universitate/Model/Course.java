package mta.universitate.Model;

public class Course {
    private int id;
    private String name;
    private int credits;
    private Professor professor;

    public static Course fromDB(int id){
        Course C = new Course();
        C.setId(id);

        return Database.getInstance().get(C);
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

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
