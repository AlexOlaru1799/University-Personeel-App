package mta.universitate.Model;

public class Employee extends Person {
    private Position position;
    private int salary;

    public Employee(String name, String surname, Position position, int salary) {
        super(name, surname);
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

    public int getSalary() {
        return salary;
    }

    public Position getPosition() {
        return position;
    }
}
