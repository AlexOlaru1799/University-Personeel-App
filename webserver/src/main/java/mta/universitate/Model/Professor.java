package mta.universitate.Model;

public class Professor extends Employee {

    public Professor()
    {
        super();
    }
    public Professor(String name, String surname,
                     String birthDate)
    {
        super(name, surname, birthDate);
    }

    public void giveGrade(Student S, int grade)
    {
        System.out.print("Professor gave grade " +
                grade + "to student " +S.getName());
    }

}
