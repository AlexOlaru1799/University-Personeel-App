package mta.universitate.Model;

public class Student extends Person {
    private String id;
    private StudyGroup studyGroup;
    private StudyYear studyYear;
    private int income;
    private int major;
    

    public Student(String name, String surname,
                   StudyGroup studyGroup, StudyYear studyYear, int income, int major)
    {
        super(name, surname);
        this.studyGroup = studyGroup;
        this.studyYear = studyYear;
        this.income = income;
        this.major = major;
    }

    @Override
    public void addDocument(){
        System.out.print("AddDocument");
    }

    public void fillReport()
    {
        System.out.print("fillReport");
    }

    public int getIncome() {
        return income;
    }

    public int getMajor() {
        return major;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public StudyYear getStudyYear() {
        return studyYear;
    }
}
