package mta.universitate.Model;

import mta.universitate.Utils.JsonParser;

public class StudyGroup extends JsonParser {
    private Integer id;
    private String name;
    private Professor mentor;
    private Integer study_year;

    public static StudyGroup fromDB(int id)
    {
        StudyGroup SG = new StudyGroup();
        SG.id = id;

        return Database.getInstance().get(SG);
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

    public Professor getMentor() {
        return mentor;
    }

    public void setMentor(Professor mentor) {
        this.mentor = mentor;
    }

    public Integer getStudy_year() {
        return study_year;
    }

    public void setStudy_year(Integer study_year) {
        this.study_year = study_year;
    }
}