package mta.universitate.Routes;

import mta.universitate.Model.*;
import mta.universitate.Model.Professor;
import mta.universitate.Model.Secretary;
import mta.universitate.Utils.CookieManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

@RestController
public class RouteProfessor {
    Database db = Database.getInstance();

    @RequestMapping(value = "/professor/view-schedule", produces = "application/json")
    @ResponseBody
    public String viewSchedule(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestParam String initDate) {
        try {
            Professor P = Professor.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));
            return P.viewScheduleForProfessor(P.getName(), P.getSurname(), initDate);
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return "{'status' : 'FAILED'}";
    }

    @RequestMapping(value = "/professor/averageGrade", produces = "application/json")
    @ResponseBody
    public Integer averageGrade(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestParam String name) {
        try
        {
            Professor P = Professor.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));
            ArrayList<Grade> grades=P.getGradesForSubject(name);

            int avg=0;
            for(int i=0;i<grades.size();i++)
            {
                avg += grades.get(i).getValue();
            }
            avg/=grades.size();

            return avg;
        }
        catch (Exception exc){}

        return -1;
    }

    @RequestMapping(value = "/professor/failedOneSubject", produces = "application/json")
    @ResponseBody
    public String failedOneSubject(@CookieValue(value = "uid", defaultValue = "test") Cookie C) {
        try
        {
            Professor P = Professor.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));

            ArrayList<Student>students=P.failedOneSubject();

            if(students.size()>0)
            {
                StringBuilder response = new StringBuilder();

                for (Student stud : students)
                    response.append(stud.toJson());

                return response.toString();
            }
            else
            {
                StringBuilder response = new StringBuilder();

                String error="No students!";

                response.append(error);

                return response.toString();
            }
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
        return "{'status' : 'FAILED'}";
    }

    @RequestMapping(value = "/professor/nrStudFailedExam", produces = "application/json")
    @ResponseBody
    public Integer nrStudFailedExam(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestParam String name) {
        try
        {
            Professor P = Professor.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));
            ArrayList<Grade>grades=P.getGradesForSubject(name);

            int nr=0;
            for(int i=0;i<grades.size();i++) {
                if (grades.get(i).getValue() <5) {
                    nr++;
                }
            }

            return nr;
        }
        catch (Exception exc){}

        return -1;
    }
}