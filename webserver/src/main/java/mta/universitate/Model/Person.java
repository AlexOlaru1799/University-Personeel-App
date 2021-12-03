package mta.universitate.Model;


public abstract class Person implements IPerson {
    private String name;
    private String surname;
    private String password;
    private String birthDate;

    public Person(String name, String surname, String password,
                  String birthDate)
    {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.birthDate = birthDate;
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
