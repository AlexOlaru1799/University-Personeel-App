package mta.universitate.Routes;

import mta.universitate.Model.*;
import mta.universitate.Utils.CookieManager;
import mta.universitate.Utils.Hasher;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Locale;

import mta.universitate.Model.Admin.*;

import javax.servlet.http.Cookie;

@RestController
public class RouteAdmin {
    Database db = Database.getInstance();

    @RequestMapping(value = "/admin/reset-password", produces = "application/json")
    @ResponseBody
    public String resetPassword(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestParam String username, @RequestParam String new_pass){

        try
        {
            Admin A = Admin.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));
            if (A.resetUserPassword(username, Hasher.getHash(new_pass)))
                return "{'status' : 'SUCCESS'}";
        }
        catch (Exception exc){}

        return "{'status' : 'FAILED'}";
    }

    @RequestMapping(value = "/admin/reset-username", produces = "application/json")
    @ResponseBody
    public String resetUsername(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestParam String username, @RequestParam String new_username){

        try
        {
            Admin A = Admin.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));
            if (A.resetUsername(username, new_username))
                return "{'status' : 'SUCCESS'}";
        }
        catch (Exception exc){}

        return "{'status' : 'FAILED'}";
    }


    @RequestMapping(value = "/admin/create-employee", produces = "application/json")
    @ResponseBody
    public String createEmployee(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestParam String name, @RequestParam String surname, @RequestParam String password, @RequestParam String position, @RequestParam String role, @RequestParam int salary)  {

        try
        {
            Admin A = Admin.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));
            if (A.createEmployee(name, surname, password, position, role, salary))
                return "{'status' : 'SUCCESS'}";
        }
        catch (Exception exc){}

        return "{'status' : 'FAILED'}";

    }


    @RequestMapping(value = "/admin/delete-employee", produces = "application/json")
    @ResponseBody
    public String deleteEmployee(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestParam String name, @RequestParam String surname) {

        try
        {
            Admin A = Admin.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));
            if (A.deleteEmployee(name, surname))
                return "{'status' : 'SUCCESS'}";
        }
        catch (Exception exc){}

        return "{'status' : 'FAILED'}";
    }


    @RequestMapping(value = "/admin/create-student", produces = "application/json")
    @ResponseBody
    public String createStudent(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestParam String name, @RequestParam String surname, @RequestParam String password, @RequestParam String major, @RequestParam String study_group, @RequestParam int income) {

        try
        {
            Admin A = Admin.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));
            if (A.createStudent(name, surname, password, major, study_group, income))
                return "{'status' : 'SUCCESS'}";
        }
        catch (Exception exc){}

        return "{'status' : 'FAILED'}";
    }


    @RequestMapping(value = "/admin/delete-student", produces = "application/json")
    @ResponseBody
    public String deleteStudent(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestParam String name, @RequestParam String surname) {
        try
        {
            Admin A = Admin.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));
            if (A.deleteStudent(name, surname))
                return "{'status' : 'SUCCESS'}";
        }
        catch (Exception exc){}

        return "{'status' : 'FAILED'}";
    }




    /*
    @GetMapping("/materii")
    public String viewSubjects() throws SQLException {
        Database db = Database.getInstance();
        ResultSet result = db.getSubjects();

        if(result == null)
        {
            return "Eroare :(";
        }

        ResultSetMetaData metadata = result.getMetaData();
        int columnCount = metadata.getColumnCount();

        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("<style>table, th, td {border: 1px solid black;border-collapse: collapse; text-align:center} table.center{margin-left: auto;margin-right: auto;} table{font-family: arial, sans-serif;width: 70%;}</style>");
        stringBuilder.append("<table class=\"center\">" +
                "<tr>" +
                "<th>Materie</th>" +
                "<th>Numar credite</th>" +
                "<th>Profesor</th>" +
                "<th>Specializare</th>" +
                "<th>Facultate</th>" +
                "</tr>");

        while (result.next()) {
            stringBuilder.append("<tr>");
            for (int i = 1; i <= columnCount; i++) {
                stringBuilder.append("<td>").append(result.getString(i)).append("</td>");
            }
            stringBuilder.append("</tr>");
        }
        stringBuilder.append("</table>");


        return stringBuilder.toString();
    }

    */

}
