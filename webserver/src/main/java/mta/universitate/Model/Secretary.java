package mta.universitate.Model;


public class Secretary extends Employee {

    public Secretary(Employee E)
    {
        super(E);
    }

    public static Secretary fromEmployee(Employee E){
        if (E.getPosition().getDescription().contentEquals("Secretary"))
            return new Secretary(E);
        else
            return null;
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
