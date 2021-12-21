package mta.universitate.Model;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Professor extends Employee {

    public Professor(Employee E) {
        super(E);
    }


    public static Professor fromDB(int id)
    {
        Employee E = Employee.fromDB(id);

        return fromEmployee(E);
    }

    public static Professor fromEmployee(Employee E){
        if (E.getPosition().getDescription().contentEquals("Professor"))
            return new Professor(E);
        else
            return null;
    }

    public boolean giveGrade(String name, String surname, int grade, String course, LocalDate date, int id) throws SQLException {
        Database db = Database.getInstance();

        Grade G=new Grade();
        G.setValue(grade);
        G.setDate(date);

        Student S=new Student();
        S.setId(db.getStudentID(name,surname));

        Course C=new Course();
        C.setId(db.getCourseID(course));
        C=db.get(C);

        G.setStudent(S);
        G.setCourse(C);

        ArrayList<Grade> grades = db.getAllGrades();

        if(C.getProfessor().getId()==id) {
            int ok = 0;

            for(int i=0;i<grades.size();i++)
            {
                if (grades.get(i).getStudent().getName().equals(name))
                {
                    if (grades.get(i).getStudent().getSurname().equals(surname))
                    {
                        if (grades.get(i).getCourse().getName().equals(course))
                        {
                            int idd=grades.get(i).getId();
                            if (db.delete(grades.get(i)))
                            {
                                if (db.add(G))
                                {
                                    ok = 1;
                                    return true;
                                }
                            }
                        }
                    }
                }
            }

            if (ok == 0) {
                if (db.add(G))
                    return true;
            }
        }

        return false;
    }

    public ArrayList getGradesForStudent(String name, String surname)
    {
        Database db = Database.getInstance();
        Student S=new Student();
        S.setName(name);
        S.setSurname(surname);

        ArrayList<Grade> gradesfromDB = db.getAllGrades();

        ArrayList<Grade> gradesforStudent=new ArrayList<>();

        for(int i=0;i<gradesfromDB.size();i++)
        {
            if(gradesfromDB.get(i).getStudent().getName().equals(S.getName()) && gradesfromDB.get(i).getStudent().getSurname().equals(S.getSurname()))
            {
                gradesfromDB.get(i).setDate(null);
                gradesforStudent.add(gradesfromDB.get(i));
            }
        }
        return gradesforStudent;
    }
}