package mta.universitate.Model;
import mta.universitate.Utils.JsonParser;

import java.sql.SQLException;
import java.util.Locale;

public class Employee extends JsonParser {
    private Integer id;
    private String name;
    private String surname;
    private Position position;
    private Integer salary;
    private User user;



    public static Employee fromDB(int id){
        Employee E = new Employee();
        E.id = id;

        return Database.getInstance().get(E);
    }

    public static Employee fromUser(User U)
    {
        String[] tokens = U.getUsername().split("@");
        tokens = tokens[0].split("\\.");

        String name = tokens[0].substring(0, 1).toUpperCase(Locale.ROOT) + tokens[0].substring(1);
        String surname = tokens[1].substring(0, 1).toUpperCase(Locale.ROOT) + tokens[1].substring(1);
        try
        {
            Employee E = Employee.fromDB(Database.getInstance().getEmployeeID(name, surname));
            return E;
        }
        catch (SQLException exc){}

        return null;
    }


    public Employee(){}
    public Employee(int id, String name, String surname, Position position, int salary, User user){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.position = position;
        this.salary = salary;
        this.user = user;
    }

    public Employee(Employee E){
        this.id = E.id;
        this.name = E.name;
        this.surname = E.surname;
        this.position = E.position;
        this.salary = E.salary;
        this.user = E.user;
    }

    public void addDocument(Document D) {
        System.out.print("addDocument");
    }

    public String viewStudent(String name, String surname) {
        try{
            Database db = Database.getInstance();
            Student S = Student.fromDB(db.getStudentID(name, surname));

            return S.toJson();
        }
        catch (Exception exc){}
        return null;
    }

    public String viewClassroom(String name){
        try{
            Database db = Database.getInstance();
            Classroom C = Classroom.fromDB(db.getClassroomID(name));

            return C.toJson();
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
        return null;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
