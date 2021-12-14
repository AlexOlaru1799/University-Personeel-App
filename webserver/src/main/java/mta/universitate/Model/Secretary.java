package mta.universitate.Model;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import mta.universitate.Utils.Hasher;

import java.sql.SQLException;
import java.util.Locale;

public class Secretary extends Employee {

    public Secretary(Employee E) {
        super(E);
    }

    public static Secretary fromDB(int id) {
        Employee E = Employee.fromDB(id);

        return fromEmployee(E);
    }

    public static Secretary fromEmployee(Employee E){
        if (E.getUser().getRole().getDescription().contentEquals("Secretary"))
            return new Secretary(E);
        else
            return null;
    }


    public boolean deleteProfessor(String name, String surname) {
        try
        {
            Database db = Database.getInstance();
            Employee E = Employee.fromDB(db.getEmployeeID(name, surname));
            if (db.delete(E))
                return true;
        }
        catch (SQLException e){}

        return false;
    }

    public boolean createProfessor(String name, String surname, String password, String position, int salary) {
        try
        {
            Database db = Database.getInstance();
            Employee E = new Employee();
            E.setName(name);
            E.setSurname(surname);
            E.setSalary(salary);
            E.setPosition(Position.fromDB(db.getPositionID(position)));

            User U = new User();
            U.setPassword(Hasher.getHash(password));
            U.setUsername(name.toLowerCase(Locale.ROOT) + "." + surname.toLowerCase(Locale.ROOT)+"@mta.ro");
            U.setRole(Role.fromDB(db.getRoleID("Professor")));

            E.setUser(U);

            if (db.add(E))
                return true;
        }
        catch (SQLException e){}

        return false;

    }

    public boolean createStudent(String name, String surname, String password, String major, String study_group, int income) {
        try
        {
            Database db = Database.getInstance();
            Student S = new Student();
            S.setName(name);
            S.setSurname(surname);
            S.setIncome(income);
            S.setMajor(Major.fromDB(db.getMajorID(major)));
            S.setStudyGroup(StudyGroup.fromDB(db.getStudyGroupID(study_group)));

            User U = new User();
            U.setPassword(Hasher.getHash(password));
            U.setUsername(name.toLowerCase(Locale.ROOT) + "." + surname.toLowerCase(Locale.ROOT)+"@mta.ro");
            U.setRole(Role.fromDB(db.getRoleID("Student")));

            S.setUser(U);

            if (db.add(S))
                return true;
        }
        catch (SQLException e){}

        return false;
    }

    public boolean deleteStudent(String name, String surname) {
        try
        {
            Database db = Database.getInstance();
            Student S = Student.fromDB(db.getStudentID(name, surname));
            if (db.delete(S))
                return true;
        }
        catch (SQLException e){}

        return false;
    }

    public String viewProfessor(String name, String surname){
        try{
            Database db = Database.getInstance();
            Professor P = Professor.fromEmployee(Employee.fromDB(db.getEmployeeID(name, surname)));

            return P.toJson();
        }
        catch (SQLException e){}
        catch (JsonProcessingException e){}

        return null;

    }




}
