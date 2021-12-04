package mta.universitate.Model;

public class Professor extends Employee {

    public Professor(String name, String surname, String password,
                     String birthDate, int salary) {
        super(name, surname, Role.PROFESSOR, salary);
    }


    public void giveGrade(Student S, int grade)
    {
        System.out.print("Professor gave grade " +
                grade + "to student " +S.getName());
    }

}
