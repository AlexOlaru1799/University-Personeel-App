package mta.universitate.Model;

public class Admin extends Employee {

    public Admin(String name, String surname, String password,
                     String birthDate, int salary)
    {
        super(name, surname, password, birthDate, Position.ADMIN, salary);
    }

    public void getProfessors() {
        System.out.print("Show professors");
    }

    public void getCourses() {
        System.out.print("Show courses");
    }

    public void deletePerson(Person P) {
        System.out.print("Delete persoan " + P.getName());
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
