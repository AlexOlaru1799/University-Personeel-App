package mta.universitate.Routes;

import mta.universitate.Model.*;
import mta.universitate.Utils.CookieManager;
import mta.universitate.Utils.Hasher;
import mta.universitate.Utils.ParamsParser;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

import mta.universitate.Model.Admin.*;

import javax.servlet.http.Cookie;

@RestController
public class RouteAdmin {
    Database db = Database.getInstance();

    @PostMapping(value = "/admin/reset-password", produces = "application/json")
    @ResponseBody
    public String resetPassword(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestBody String payload){

        try
        {
            HashMap<String, Object> parameters = ParamsParser.parse(payload);
            Admin A = Admin.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));
            if (A.resetUserPassword(parameters.get("username").toString(), Hasher.getHash(parameters.get("new_pass").toString())))
                return "{\"status\" : \"SUCCESS\"}";
        }
        catch (Exception exc){}

        return "{\"status\" : \"FAILED\"}";
    }

    @PostMapping(value = "/admin/reset-username", produces = "application/json")
    @ResponseBody
    public String resetUsername(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestBody String payload){

        try
        {
            HashMap<String, Object> parameters = ParamsParser.parse(payload);
            Admin A = Admin.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));
            if (A.resetUsername(parameters.get("username").toString(), parameters.get("new_username").toString()))
                return "{\"status\" : \"SUCCESS\"}";
        }
        catch (Exception exc){}

        return "{\"status\" : \"FAILED\"}";
    }


    @PostMapping(value = "/admin/create-employee", produces = "application/json")
    @ResponseBody
    public String createEmployee(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestBody String payload)  {

        try
        {
            HashMap<String, Object> parameters = ParamsParser.parse(payload);
            Admin A = Admin.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));

            String name = parameters.get("name").toString();
            String surname = parameters.get("surname").toString();
            String password = parameters.get("password").toString();
            String position = parameters.get("position").toString();
            String role = parameters.get("role").toString();
            int salary = Integer.parseInt(parameters.get("salary").toString());

            if (A.createEmployee(name, surname, password, position, role, salary))
                return "{\"status\" : \"SUCCESS\"}";
        }
        catch (Exception exc){}

        return "{\"status\" : \"FAILED\"}";

    }


    @PostMapping(value = "/admin/delete-employee", produces = "application/json")
    @ResponseBody
    public String deleteEmployee(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestBody String payload) {

        try
        {
            HashMap<String, Object> parameters = ParamsParser.parse(payload);

            String name = parameters.get("name").toString();
            String surname = parameters.get("surname").toString();

            Admin A = Admin.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));
            if (A.deleteEmployee(name, surname))
                return "{\"status\" : \"SUCCESS\"}";
        }
        catch (Exception exc){}

        return "{\"status\" : \"FAILED\"}";
    }


    @PostMapping(value = "/admin/create-student", produces = "application/json")
    @ResponseBody
    public String createStudent(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestBody String payload) {

        try
        {
            HashMap<String, Object> parameters = ParamsParser.parse(payload);
            Admin A = Admin.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));

            String name = parameters.get("name").toString();
            String surname = parameters.get("surname").toString();
            String password = parameters.get("password").toString();
            String major = parameters.get("major").toString();
            String study_group = parameters.get("study_group").toString();
            int income = Integer.parseInt(parameters.get("income").toString());

            if (A.createStudent(name, surname, password, major, study_group, income))
                return "{\"status\" : \"SUCCESS\"}";
        }
        catch (Exception exc){}

        return "{\"status\" : \"FAILED\"}";
    }


    @PostMapping(value = "/admin/delete-student", produces = "application/json")
    @ResponseBody
    public String deleteStudent(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestBody String payload) {
        try
        {
            HashMap<String, Object> parameters = ParamsParser.parse(payload);

            String name = parameters.get("name").toString();
            String surname = parameters.get("surname").toString();

            Admin A = Admin.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));
            if (A.deleteStudent(name, surname))
                return "{\"status\" : \"SUCCESS\"}";
        }
        catch (Exception exc){}

        return "{\"status\" : \"FAILED\"}";
    }
}
