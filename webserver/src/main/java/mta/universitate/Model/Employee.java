package mta.universitate.Model;

public class Employee extends Person {
    private Role role;
    private int salary;

    public Employee(String name, String surname, Role role, int salary) {
        super(name, surname);
        this.role = role;
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

    public Role getPosition() {
        return role;
    }
}
