package mta.universitate.Model;

public class Student extends Person {
    private String identifier;

    //To do adaugare grupa aici
    //private Grupa grupa;

    public Student(String name, String surname, String password,
                   String birthDate)
    {
        super(name, surname, password, birthDate);
    }

    @Override
    public void addDocument(){
        System.out.print("AddDocument");
    }

    public void fillReport()
    {
        System.out.print("fillReport");
    }

}
