package mta.universitate.Model;

public class Admin extends Employee {

    public Admin(Employee E)
    {
        super(E);
    }

    public static Admin fromEmployee(Employee E){
        if (E.getPosition().getDescription().contentEquals("Administrator"))
            return new Admin(E);
        else
            return null;
    }

    public void getProfessors() {
        System.out.print("Show professors");
    }

    public void getCourses() {
        System.out.print("Show courses");
    }

    public void deletePerson() {
        System.out.print("Delete persoan ");
    }

    public void getStudent(Student S) {
        System.out.print("Showing student " + S.getName());
    }

    public void getStudentsByMajor(Major M){

    }

    public void deleteEmployee(Employee E) {
    }

    public void addEmployee(Employee E) {
    }

    public void resetUserPassword(String name, String surname, String newPassword) {
    }
}
