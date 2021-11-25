package mta.universitate.Model;

import java.util.ArrayList;

public class StudyYear {

    int year;
    ArrayList<Major> majors = new ArrayList<>();

    public int getYear()
    {
        return this.year;
    }

    public ArrayList<Major> getMajors() {
        return this.majors;
    }
}
