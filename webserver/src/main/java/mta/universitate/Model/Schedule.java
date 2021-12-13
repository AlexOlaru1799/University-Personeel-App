package mta.universitate.Model;

import mta.universitate.Utils.JsonParser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

public class Schedule extends JsonParser {
    private int id;
    private Date date;
    private StudyGroup study_group;
    private Classroom classroom;
    private Module module;

    static public Schedule fromDB(int id)
    {
        Schedule S = new Schedule();
        S.id = id;

        return Database.getInstance().get(S);
    }



    public ResultSet getTeacherSchedule(String surname, String name) throws SQLException {
        Database db = Database.getInstance();
        ResultSet result = db.getTeacherSchedule(surname, name);

        return result;
    }

    public ResultSet getGroupSchedule(String groupName) throws SQLException {
        Database db = Database.getInstance();
        ResultSet result = db.getGroupSchedule(groupName);

        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public StudyGroup getStudy_group() {
        return study_group;
    }

    public void setStudy_group(StudyGroup study_group) {
        this.study_group = study_group;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}