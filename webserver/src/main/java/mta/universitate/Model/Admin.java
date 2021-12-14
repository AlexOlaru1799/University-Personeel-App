package mta.universitate.Model;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import mta.universitate.Utils.Hasher;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.Locale;

public class Admin extends Employee {

    public Admin(Employee E)
    {
        super(E);
    }

    public static Admin fromDB(int id)
    {
        Employee E = Employee.fromDB(id);

        return fromEmployee(E);
    }

    public static Admin fromEmployee(Employee E){
        if (E.getUser().getRole().getDescription().contentEquals("Administrator"))
            return new Admin(E);
        else
            return null;
    }

    public void getProfessors() {
        System.out.print("Show professors");
    }

    public void getCourses() {
        System.out.print("Show courses");
    }

    public void deletePerson() {
        System.out.print("Delete persoan ");
    }

    public void getStudent(Student S) {
        System.out.print("Showing student " + S.getName());
    }

    public void getStudentsByMajor(Major M){

    }

    public boolean deleteEmployee(String name, String surname) {
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

    public boolean createEmployee(String name, String surname, String password, String position, String role, int salary) {
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
            U.setRole(Role.fromDB(db.getRoleID(role)));

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

    public boolean resetUserPassword(String username, String newPassword) {

        try
        {
            Database db = Database.getInstance();
            User U = db.get(User.fromDB(db.getUserID(username)));
            U.setPassword(newPassword);
            if (Database.getInstance().update(U))
                return true;
        }
        catch (SQLException e){}

        return false;
    }

    public boolean resetUsername(String username, String newUsername) {

        try
        {
            Database db = Database.getInstance();
            User U = db.get(User.fromDB(db.getUserID(username)));
            U.setUsername(newUsername);
            if (Database.getInstance().update(U))
                return true;
        }
        catch (SQLException e)
        {
            return false;
        }

        return false;
    }
}
