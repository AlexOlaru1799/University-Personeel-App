package mta.universitate.Routes;

import mta.universitate.Model.*;
import mta.universitate.Utils.CookieManager;
import mta.universitate.Utils.ParamsParser;
import org.springframework.web.bind.annotation.*;
import mta.universitate.Model.Secretary;
import javax.servlet.http.Cookie;
import java.util.HashMap;

@RestController
public class RouteSecretary {
    Database db = Database.getInstance();


    @PostMapping(value = "/secretary/create-professor", produces = "application/json")
    @ResponseBody
    public String createProfessor(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestBody String payload)  {

        try
        {
            HashMap<String, Object> parameters = ParamsParser.parse(payload);
            Secretary S = Secretary.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));

            String name=parameters.get("name").toString();
            String surname=parameters.get("surname").toString();
            String password=parameters.get("password").toString();
            String position=parameters.get("position").toString();
            int salary=Integer.parseInt(parameters.get("salary").toString());

            if (S.createProfessor(name, surname, password, position, salary))
                return "{\"status\" : \"SUCCESS\"}";
        }
        catch (Exception exc){}

        return "{\"status\" : \"FAILED\"}";
    }

    @PostMapping(value = "/secretary/delete-professor", produces = "application/json")
    @ResponseBody
    public String deleteProfessor(@CookieValue(value = "uid", defaultValue = "test") Cookie C,  @RequestBody String payload) {

        try
        {
            HashMap<String, Object> parameters = ParamsParser.parse(payload);
            Secretary S = Secretary.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));

            String name=parameters.get("name").toString();
            String surname=parameters.get("surname").toString();

            if (S.deleteProfessor(name, surname))
                return "{\"status\" : \"SUCCESS\"}";
        }
        catch (Exception exc){}

        return "{\"status\" : \"FAILED\"}";
    }

    @PostMapping(value = "/secretary/create-student", produces = "application/json")
    @ResponseBody
    public String createStudent(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestBody String payload) {

        try
        {
            HashMap<String, Object> parameters = ParamsParser.parse(payload);
            Secretary S = Secretary.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));

            String name=parameters.get("name").toString();
            String surname=parameters.get("surname").toString();
            String password=parameters.get("password").toString();
            String major=parameters.get("major").toString();
            String study_group=parameters.get("study_group").toString();
            int income = Integer.parseInt(parameters.get("income").toString());

            if (S.createStudent(name, surname, password, major, study_group, income))
                return "{\"status\" : \"SUCCESS\"}";
        }
        catch (Exception exc){}

        return "{\"status\" : \"FAILED\"}";
    }

    @PostMapping(value = "/secretary/delete-student", produces = "application/json")
    @ResponseBody
    public String deleteStudent(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestBody String payload) {
        try
        {
            HashMap<String, Object> parameters = ParamsParser.parse(payload);
            Secretary S = Secretary.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));

            String name=parameters.get("name").toString();
            String surname=parameters.get("surname").toString();

            if (S.deleteStudent(name, surname))
                return "{\"status\" : \"SUCCESS\"}";
        }
        catch (Exception exc){}

        return "{\"status\" : \"FAILED\"}";
    }

    @PostMapping(value = "/secretary/view-professor", produces = "application/json")
    @ResponseBody
    public String viewProfessor(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestBody String payload) {
        try
        {
            HashMap<String, Object> parameters = ParamsParser.parse(payload);
            Secretary S = Secretary.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));

            String name=parameters.get("name").toString();
            String surname=parameters.get("surname").toString();

            return String.format("{\"status\" : \"SUCCESS\", \"result\" : %s }", S.viewProfessor(name, surname));
        }
        catch (Exception exc){
            exc.printStackTrace();
        }

        return "{\"status\" : \"FAILED\"}";
    }

    @PostMapping(value = "/secretary/view-student", produces = "application/json")
    @ResponseBody
    public String viewStudent(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestBody String payload) {
        try
        {
            HashMap<String, Object> parameters = ParamsParser.parse(payload);
            Secretary S = Secretary.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));

            String name=parameters.get("name").toString();
            String surname=parameters.get("surname").toString();

            return String.format("{\"status\" : \"SUCCESS\", \"result\" : %s }", S.viewStudent(name, surname));
        }
        catch (Exception exc){
            exc.printStackTrace();
        }

        return "{\"status\" : \"FAILED\"}";
    }

    @PostMapping(value = "/secretary/view-classroom", produces = "application/json")
    @ResponseBody
    public String viewClassroom(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestBody String payload)
    {
        try
        {
            HashMap<String, Object> parameters = ParamsParser.parse(payload);
            Secretary S = Secretary.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));

            String name=parameters.get("name").toString();

            return String.format("{\"status\" : \"SUCCESS\", \"result\" : %s }", S.viewClassroom(name));
        }
        catch (Exception exc){
            exc.printStackTrace();
        }

        return "{\"status\" : \"FAILED\"}";
    }

    // TODO
    @RequestMapping(value = "/secretary/view-courses", produces = "application/json")
    @ResponseBody
    public String viewCourses(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestParam String major)
    {
        return "";
    }

    @RequestMapping(value = "/secretary/add-course", produces = "application/json")
    @ResponseBody
    public String addCourse(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestBody String payload)
    {
        try
        {
            HashMap<String, Object> parameters = ParamsParser.parse(payload);
            Secretary S = Secretary.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));

            String course_name = parameters.get("course_name").toString();
            String professor_name = parameters.get("professor_name").toString();
            String professor_surname = parameters.get("professor_surname").toString();
            Integer credits = Integer.parseInt(parameters.get("credits").toString());

            if (S.addCourse(course_name, credits, professor_name, professor_surname))
                return String.format("{\"status\" : \"SUCCESS\"}");
        }
        catch (Exception exc){
            exc.printStackTrace();
        }

        return "{\"status\" : \"FAILED\"}";
    }
}
