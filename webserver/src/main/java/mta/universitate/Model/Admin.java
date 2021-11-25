package mta.universitate.Model;

public class Admin extends Employee {

    public Admin()
    {
        super();
    }
    public Admin(String _nume, String _prenume,
                 String _dataNasterii)
    {
        super(_nume,_prenume,_dataNasterii);
    }

    public void showProfessors()
    {
        System.out.print("Show professors");
    }

    public void showCourses()
    {
        System.out.print("Show courses");
    }

    public void deletePerson(Person P)
    {
        System.out.print("Delete persoan " + P.getName());
    }

    public void showStudent(Student S){System.out.print("Showing student " + S.getName());
    }

}
