package mta.universitate.Model;


public class Secretary extends Employee {

    public Secretary(Employee E)
    {
        super(E);
    }

    public static Secretary fromDB(int id)
    {
        Employee E = Employee.fromDB(id);

        return fromEmployee(E);
    }


    public static Secretary fromEmployee(Employee E){
        if (E.getPosition().getDescription().contentEquals("Secretary"))
            return new Secretary(E);
        else
            return null;
    }

}
