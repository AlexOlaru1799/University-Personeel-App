package mta.universitate.Model;

public class Professor extends Employee {

    public Professor(Employee E) {
        super(E);
    }


    public static Professor fromDB(int id)
    {
        Employee E = Employee.fromDB(id);

        return fromEmployee(E);
    }

    public static Professor fromEmployee(Employee E){
        if (E.getPosition().getDescription().contentEquals("Professor"))
            return new Professor(E);
        else
            return null;
    }

    public void giveGrade(Student S, int grade)
    {
        System.out.print("Professor gave grade " +
                grade + "to student " + S.getName());
    }

}
