package mta.universitate.Model;

public class Employee extends Person {
    private Position position;
    private int salary;

    public Employee(String name, String surname, String password,
                    String birthDate, Position position, int salary)
    {
        super(name, surname, password, birthDate);
        this.position = position;
        this.salary = salary;
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
