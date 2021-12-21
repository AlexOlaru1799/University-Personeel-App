package mta.universitate.Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import mta.universitate.Utils.JsonParser;

import java.sql.SQLException;
import java.util.ArrayList;
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

    public ArrayList<Schedule> viewScheduleForProfessor(String name, String surname, String initDate){
        Database db = Database.getInstance();

        ArrayList<Schedule> schedules = db.getAllSchedule();
        ArrayList<Schedule> schedulesforReturn=new ArrayList<Schedule>();

        for(int i=0;i<schedules.size();i++)
        {
            String dateDB = schedules.get(i).getDate().toString();

            if(dateDB.equals(initDate)) {
                if (schedules.get(i).getModule().getProfessor().getName().equals(name) && schedules.get(i).getModule().getProfessor().getSurname().equals(surname)) {
                    schedulesforReturn.add(schedules.get(i));
                }
            }
        }
        return schedulesforReturn;
    }

    public ArrayList getGradesForSubject(String name) {
        Database db = Database.getInstance();
        Course C = new Course();
        C.setName(name);

        ArrayList<Grade> gradesfromDB = db.getAllGrades();

        ArrayList<Grade> gradesforSubject=new ArrayList<>();

        for(int i=0;i<gradesfromDB.size();i++)
        {
            if(gradesfromDB.get(i).getCourse().getName().equals(C.getName()))
            {
                gradesforSubject.add(gradesfromDB.get(i));
            }
        }
        return gradesforSubject;
    }

    public ArrayList<Grade> failedOneSubject() {
        Database db = Database.getInstance();

        ArrayList<Student> students=db.getAllStudents();
        ArrayList<Grade> grades = db.getAllGrades();
        ArrayList<Grade> grades2 = new ArrayList<>();

        int nr=0;

        for (int i = 0; i < grades.size(); i++)
        {
            int nrFailedSubj=0;

            for (int k = 0; k < students.size(); k++)
            {
                if (students.get(k).getName().equals(grades.get(i).getStudent().getName()) && students.get(k).getSurname().equals(grades.get(i).getStudent().getSurname())  )
                {
                    if(grades.get(i).getValue()<5)
                    {
                        nrFailedSubj++;
                    }
                }
            }
            if(nrFailedSubj==1)
            {
                nr++;
                grades.get(i).setDate(null);
                grades2.add(grades.get(i));

            }
        }
        return grades2;
    }

    public String viewCourses()
    {
        Database db = Database.getInstance();
        ArrayList<Course> courses = db.getAllCourses();

        try{
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return  ow.writeValueAsString(courses);
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
