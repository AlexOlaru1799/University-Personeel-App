package mta.universitate.Model;

import java.util.ArrayList;

public class StudyYear {

    int year;
    ArrayList<Major> majors = new ArrayList<>();

    public StudyYear(int year)
    {
        this.year=year;
    }

    public int getYear()
    {
        return this.year;
    }

    public ArrayList<Major> getMajors() {
        return this.majors;
    }
}
