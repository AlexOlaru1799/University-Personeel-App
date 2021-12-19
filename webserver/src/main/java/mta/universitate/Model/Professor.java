package mta.universitate.Model;

import java.sql.SQLException;
import java.time.LocalDate;

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

    public boolean giveGrade(String name, String surname, int grade, String course, LocalDate date) throws SQLException {
        Database db = Database.getInstance();

        Grade G=new Grade();
        G.setValue(grade);
        G.setDate(date);

        Student S=new Student();
        S.setId(db.getStudentID(name,surname));

        Course C=new Course();
        C.setId(db.getCourseID(course));

        G.setStudent(S);
        G.setCourse(C);

        if (db.addGrade(G))
            return true;

        return false;
    }

}
