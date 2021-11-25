package mta.universitate.Model;


public abstract class Person implements IPerson {
    private String name;
    private String surname;
    private String birthDate;

    public Person()
    {

    }
    public Person(String name, String surname,
                  String birtDate)
    {
        this.name = name;
        this.surname = surname;
        this.birthDate = birtDate;
    }

    public String getName()
    {
        return name;
    }

    public String getSurname()
    {
        return surname;
    }

    public String getBirthDate()
    {
        return birthDate;
    }
}
