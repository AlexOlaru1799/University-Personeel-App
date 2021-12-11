package mta.universitate.Model;

import java.util.ArrayList;

public class StudyGroup {
    private int id;
    private String name;
    private Professor mentor;
    private int study_year;

    public static StudyGroup fromDB(int id)
    {
        StudyGroup SG = new StudyGroup();
        SG.id = id;

        return Database.getInstance().get(SG);
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Professor getMentor() {
        return mentor;
    }

    public void setMentor(Professor mentor) {
        this.mentor = mentor;
    }

    public int getStudy_year() {
        return study_year;
    }

    public void setStudy_year(int study_year) {
        this.study_year = study_year;
    }
}