package mta.universitate.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;



public class Database {
    private static Database dbObject;
    private Connection con;
    private Database() {
        String ConnectionUrl="jdbc:sqlserver://pituserver.database.windows.net:1433;database=secretariatatm1;user=pituAdmin@pituserver;password=1q2w3e4rT;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        try {
            con = DriverManager.getConnection(ConnectionUrl);
        }
        catch (SQLException e) {
               System.out.println(e.getMessage());
        }
    }

    public static synchronized Database getInstance() {

        // create object if it's not already created
        if(dbObject == null) {
            dbObject = new Database();
        }

        // returns the singleton object
        return dbObject;
    }

    public ResultSet executeQuery(String query)
    {
        try {
            if(con!=null){
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
            }
            else
                System.out.println("Conexiunea nu s-a putut face");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection getConnection() {
        return con;
    }

    public boolean addEmployee(String name, String surname, String password, String position, int salary)
    {
        // Returns: TRUE on success, FALSE on fail

        // Get 'functii' and get the ID of given Position

        // Insert in 'utilizatori' name.lowercase().surname.lowercase()@mta.ro as Username, Password,

        ResultSet rs = this.executeQuery(String.format("INSERT INTO angajati(Nume, Prenume, , Position, Salariu) VALUES (%s, %s, %s, %s, %d)", name, surname, password, position, salary));
        return true;
    }

    public boolean addStudent(String name, String surname, String password, String birthDate, String position, int salary)
    {
        // Returns: TRUE on success, FALSE on fail

        ResultSet rs = this.executeQuery(String.format("INSERT INTO Employees(Name, Surname, BirthDate, Position, Salary) VALUES (%s, %s, %s, %s, %d)", name, surname, password, birthDate, position, salary));
        return true;
    }

    public boolean deleteUser(String name, String surname)
    {
        // Returns: TRUE on success, FALSE on fail
        // Search for User with Name = <name> and Surname = <surname> in Students and Employees and then deletes it
        ResultSet rs = this.executeQuery(String.format("DELETE Employees, Students FROM Employees INNER JOIN Students WHERE Name = %s AND Surname = %s", name, surname));
        return true;
    }

    public boolean resetUserPassword(String name, String surname, String newPassword){
        // Returns: TRUE on success, FALSE on fail

        // Check rows affected: 0 -> Failed - User does not exist, 1 -> Success
        // Search for User with Name = <name> and Surname = <surname> in Students and Employees and then update password
        ResultSet rs = this.executeQuery(String.format("UPDATE Users SET Password = %s WHERE Name = %s AND Surname = %s", newPassword, name, surname));

        return true;
    }
}
