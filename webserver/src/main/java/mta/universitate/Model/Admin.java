package mta.universitate.Model;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.SQLException;

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
        if (E.getUser().getRole().getDescription().contentEquals("Admin"))
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

    public void deleteEmployee(Employee E) {
    }

    public void addEmployee(Employee E) {
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
        catch (SQLException e)
        {
            return false;
        }

        return false;
    }
}
