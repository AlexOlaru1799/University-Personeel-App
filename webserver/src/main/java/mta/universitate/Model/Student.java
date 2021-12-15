package mta.universitate.Model;

import mta.universitate.Utils.JsonParser;

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
}
