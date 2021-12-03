package mta.universitate.Model;


public abstract class Person implements IPerson {
    private String name;
    private String surname;

    public Person()
    {

    }
    public Person(String name, String surname)
    {
        this.name = name;
        this.surname = surname;
    }

    public String getName()
    {
        return name;
    }

    public String getSurname()
    {
        return surname;
    }

}
