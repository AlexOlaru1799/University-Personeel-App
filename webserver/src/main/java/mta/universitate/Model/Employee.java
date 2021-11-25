package mta.universitate.Model;

public class Employee extends Person {
    private Position position;

    public Employee()
    {
        super();
    }
    public Employee(String name, String surname,
                    String birthDate)
    {
        super(name, surname, birthDate);
    }


    //TO DO Grupa
    //private Grupa grupa
    @Override
    public void addDocument() {
        System.out.print("addDocument");
    }

    //TO DO --> parametru anDeStudiu
    public void showGrades()
    {

    }

    //TO DO --> parametru Grupa
//    public void showDrages(Grupa G)
//    {
//
//    }

    public void showGrades(Student S)
    {

    }
}
