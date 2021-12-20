package mta.universitate.Routes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import mta.universitate.Model.*;
import mta.universitate.Model.Professor;
import mta.universitate.Model.Secretary;
import mta.universitate.Utils.CookieManager;
import mta.universitate.Utils.ParamsParser;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@RestController
public class RouteProfessor {
    Database db = Database.getInstance();

    @PostMapping(value = "/professor/view-schedule", produces = "application/json")
    @ResponseBody
    public String viewSchedule(@CookieValue(value = "uid", defaultValue = "test") Cookie C,  @RequestBody String payload) {
        try {

            HashMap<String, Object> parameters = ParamsParser.parse(payload);
            Professor P = Professor.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));
            String initDate=parameters.get("date").toString();

            StringBuilder response=new StringBuilder();

            ArrayList<Schedule>modules=P.viewScheduleForProfessor(P.getName(),P.getSurname(),initDate);
            ArrayList<Schedule>modules2=new ArrayList<>();

            for (Schedule sched : modules)
                modules2.add(sched);

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return String.format("{\"status\" : \"SUCCESS\", \"result\" : %s }", ow.writeValueAsString(modules2) );
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return "{\"status\" : \"FAILED\"}";
    }

    @PostMapping(value = "/professor/averageGrade", produces = "application/json")
    @ResponseBody
    public String averageGrade(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestBody String payload) {
        try
        {
            HashMap<String, Object> parameters = ParamsParser.parse(payload);
            Professor P = Professor.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));

            String name=parameters.get("name").toString();
            ArrayList<Grade> grades=P.getGradesForSubject(name);

            int avg=0;
            for(int i=0;i<grades.size();i++)
            {
                avg += grades.get(i).getValue();
            }
            avg/=grades.size();

            return String.format("{\"status\" : \"SUCCESS\", \"result\" : %d }", avg);
        }
        catch (Exception exc){}

        return "{\"status\" : \"FAILED\"}";
    }

    @PostMapping(value = "/professor/failedOneSubject", produces = "application/json")
    @ResponseBody
    public String failedOneSubject(@CookieValue(value = "uid", defaultValue = "test") Cookie C) {
        try
        {
            Professor P = Professor.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));

            ArrayList<Student>students=P.failedOneSubject();

            ArrayList<Student>students2=new ArrayList<>();

            if(students.size()>0)
            {
                for (Student stud : students) {
                    students2.add(stud);
                }

                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                return String.format("{\"status\" : \"SUCCESS\", \"result\" : %s }", ow.writeValueAsString(students2) );
            }
            else
            {
                return "{\"status\" : \"NO STUDENTS\"}";
            }
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
        return "{\"status\" : \"FAILED\"}";
    }

    @PostMapping(value = "/professor/nrStudFailedExam", produces = "application/json")
    @ResponseBody
    public String nrStudFailedExam(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestBody String payload) {
        try
        {
            HashMap<String, Object> parameters = ParamsParser.parse(payload);

            Professor P = Professor.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));

            String name=parameters.get("name").toString();

            ArrayList<Grade>grades=P.getGradesForSubject(name);

            int nr=0;
            for(int i=0;i<grades.size();i++) {
                if (grades.get(i).getValue() < 5) {
                    nr++;
                }
            }

            return String.format("{\"status\" : \"SUCCESS\", \"result\" : %d }", nr);
        }
        catch (Exception exc){}

        return "{\"status\" : \"FAILED\"}";
    }

    @PostMapping(value = "/professor/add-grade", produces = "application/json")
    @ResponseBody
    public String addGrade(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestBody String payload)  {
        try
        {
            HashMap<String, Object> parameters = ParamsParser.parse(payload);
            Professor P = Professor.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));

            String name = parameters.get("name").toString();
            String surname = parameters.get("surname").toString();
            String courseName = parameters.get("course").toString();
            String data = parameters.get("date").toString();
            int grade = Integer.parseInt(parameters.get("grade").toString());

            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(data, format);

            if (P.giveGrade(name, surname, grade, courseName, date))
                return "{\"status\" : \"SUCCESS\"}";
        }
        catch (Exception exc){}

        return "{\"status\" : \"FAILED\"}";

    }

    @PostMapping(value = "/professor/gradesForStudent", produces = "application/json")
    @ResponseBody
    public String viewGradesForStudent(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestBody String payload) {
        try
        {
            HashMap<String, Object> parameters = ParamsParser.parse(payload);
            Professor P = Professor.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));

            String name=parameters.get("name").toString();
            String surname=parameters.get("surname").toString();

            ArrayList<Grade> grades=P.getGradesForStudent(name,surname);
            ArrayList<Grade> grades2=new ArrayList<>();

            if(grades.size()>0)
            {
                for (Grade grade : grades) {
                    grades2.add(grade);
                }

                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                return String.format("{\"status\" : \"SUCCESS\", \"result\" : %s }", ow.writeValueAsString(grades2));
            }
            else
            {
                return "{\"status\" : \"NO GRADES\"}";
            }
        }
        catch (Exception exc){
            exc.printStackTrace();
        }
        return "{\"status\" : \"FAILED\"}";
    }
}