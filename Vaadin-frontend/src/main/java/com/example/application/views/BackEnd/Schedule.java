package com.example.application.views.BackEnd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Schedule {
    private StudyGroup group;
    Map<StudyGroup,Details>groupSchedule=new HashMap<>();

    public Schedule() {
    }

    public Schedule(StudyGroup group,Details details) {
        this.group=group;
        groupSchedule.put(this.group,details);
    }

    public StudyGroup getGroup(){
        return this.group;
    }

    public Map getGroupSchedule(){
        return groupSchedule;
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
}