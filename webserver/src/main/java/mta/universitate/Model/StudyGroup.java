package mta.universitate.Model;

public class StudyGroup {
    private int id;
    private Professor mentor;
    private Student students;
    private String groupName;

    public Professor getMentor() {
        return this.mentor;
    }

    public Student getStudents() {
        return this.students;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getId() {
        return id;
    }
}
