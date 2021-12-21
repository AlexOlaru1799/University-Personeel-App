package mta.universitate.Model;

import mta.universitate.Utils.JsonParser;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;

public class Student extends JsonParser {
    private Integer id;
    private String name;
    private String surname;
    private StudyGroup studyGroup;
    private Integer income;
    private Major major;
    private User user;

    public Student(){}
    public Student(int id, String name, String surname, StudyGroup studyGroup, int income, Major major, User user)
    {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.studyGroup = studyGroup;
        this.income = income;
        this.major = major;
        this.user = user;
    }

    public static Student fromDB(int id){
        Student S = new Student();
        S.id = id;

        return Database.getInstance().get(S);
    }

    public static Student fromUser(User U)
    {
        String[] tokens = U.getUsername().split("@");
        tokens = tokens[0].split("\\.");

        String name = tokens[0].substring(0, 1).toUpperCase(Locale.ROOT) + tokens[0].substring(1);
        String surname = tokens[1].substring(0, 1).toUpperCase(Locale.ROOT) + tokens[1].substring(1);
        try
        {
            Student S = Student.fromDB(Database.getInstance().getStudentID(name, surname));
            return S;
        }
        catch (SQLException exc){}

        return null;
    }

    public void fillReport()
    {
        System.out.print("fillReport");
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

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public void setStudyGroup(StudyGroup studyGroup) {
        this.studyGroup = studyGroup;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Schedule> viewScheduleForStudent(int id, String initDate) throws Exception{
        Database db = Database.getInstance();

        ArrayList<Schedule> schedules = db.getGroupSchedule(this.getStudyGroup().getName());
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
}
