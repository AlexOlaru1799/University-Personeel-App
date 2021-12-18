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

    @RequestMapping(value = "/secretary/create-student", produces = "application/json")
    @ResponseBody
    public String createStudent(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestParam String name, @RequestParam String surname, @RequestParam String password, @RequestParam String major, @RequestParam String study_group, @RequestParam int income) {

        try
        {
            Secretary S = Secretary.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));
            if (S.createStudent(name, surname, password, major, study_group, income))
                return "{'status' : 'SUCCESS'}";
        }
        catch (Exception exc){}

        return "{'status' : 'FAILED'}";
    }


    @RequestMapping(value = "/secretary/delete-student", produces = "application/json")
    @ResponseBody
    public String deleteStudent(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestParam String name, @RequestParam String surname) {
        try
        {
            Secretary S = Secretary.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));
            if (S.deleteStudent(name, surname))
                return "{'status' : 'SUCCESS'}";
        }
        catch (Exception exc){}

        return "{'status' : 'FAILED'}";
    }


    @RequestMapping(value = "/secretary/view-professor", produces = "application/json")
    @ResponseBody
    public String viewProfessor(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestParam String name, @RequestParam String surname) {
        try
        {
            Secretary S = Secretary.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));
            return S.viewProfessor(name, surname);
        }
        catch (Exception exc){
            exc.printStackTrace();
        }

        return "{'status' : 'FAILED'}";
    }

    @RequestMapping(value = "/secretary/view-student", produces = "application/json")
    @ResponseBody
    public String viewStudent(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestParam String name, @RequestParam String surname) {
        try
        {
            Secretary S = Secretary.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));
            return S.viewStudent(name, surname);
        }
        catch (Exception exc){
            exc.printStackTrace();
        }

        return "{'status' : 'FAILED'}";
    }

    @RequestMapping(value = "/secretary/view-classroom", produces = "application/json")
    @ResponseBody
    public String viewClassroom(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestParam String name)
    {
        try
        {
            Secretary S = Secretary.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));
            return S.viewClassroom(name);
        }
        catch (Exception exc){
            exc.printStackTrace();
        }

        return "{'status' : 'FAILED'}";
    }

    // TODO
    @RequestMapping(value = "/secretary/view-courses", produces = "application/json")
    @ResponseBody
    public String viewCourses(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestParam String major)
    {
        return "";
    }
}
