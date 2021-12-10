package mta.universitate.Model;


/**
 * Clasa Secretar
 *
 * Clasa utilizata pentru a implementa utilitatile secretarului
 *
 */

public class Secretary extends Employee {

    public Secretary(String name, String surname, int salary)
    {
        super(name, surname, new Role("Secretary"), salary);
    }


    public void viewStudentProfile()
    {

    }

    //TO DO
    public void showStatistics(Student S)
    {
        System.out.print("Showing stats for student "+
                S.getName());
    }


    public void showStatistics(StudyGroup G)
    {
        System.out.print("Showing stats for study group");
    }

    //TO DO statistici pentru an de studiu
    public void showStatistics(StudyYear A)
    {
        System.out.print("Year "+
                A.getYear());
    }
}
