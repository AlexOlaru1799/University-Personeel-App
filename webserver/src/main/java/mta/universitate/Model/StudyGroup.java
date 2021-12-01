package mta.universitate.Model;

public class StudyGroup {

    private Professor mentor;
    private Student students;
    private String groupName;

    public String getGroupName() { return this.groupName; }

    public Professor getMentor() {
        return this.mentor;
    }

    public Student getStudents() {
        return this.students;
    }
}
