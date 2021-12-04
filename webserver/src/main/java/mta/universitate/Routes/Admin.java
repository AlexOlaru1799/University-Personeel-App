package mta.universitate.Routes;

import mta.universitate.Model.Database;
import mta.universitate.Model.Person;
import mta.universitate.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Objects;

@RestController
public class Admin {
    Database db = Database.getInstance();

    @RequestMapping(value = "/admin/reset-password", produces = "application/json")
    @ResponseBody
    public String resetPassword(@RequestParam String username, @RequestParam String new_pass) throws SQLException {
        if (this.db.resetUserPassword(username, new_pass))
            return "{'status' : 'SUCCESS'}";
        return "{'status' : 'FALSE'}";
    }

    @RequestMapping(value = "/admin/create-employee", produces = "application/json")
    @ResponseBody
    public String addEmployee(@RequestParam String name, @RequestParam String surname, @RequestParam String password, @RequestParam String role, @RequestParam String position, @RequestParam int salary) throws SQLException, NoSuchAlgorithmException {
        if (this.db.createEmployee(name, surname, password, role, position, salary))
            return "{'status' : 'SUCCESS'}";
        return "{'status' : 'FAILED'}";

    }

    @RequestMapping(value = "/admin/delete-employee", produces = "application/json")
    @ResponseBody
    public String deleteEmployee(@RequestParam String name, @RequestParam String surname) throws SQLException {
        if (this.db.deleteEmployee(name, surname))
            return "{'status' : 'SUCCESS'}";
        return "{'status' : 'FAILED'}";
    }

    /*
    @RequestMapping(value = "/admin/create-student", produces = "application/json")
    @ResponseBody
    public HttpStatus createStudent(@RequestParam String name, @RequestParam String surname, @RequestParam String password, @RequestParam int salary) throws SQLException, NoSuchAlgorithmException {
        db = Database.getInstance();

        String res = db.createStudent(S);
        if (!Objects.equals(res, "OK")) {
            return HttpStatus.NOT_FOUND;
        }
        return HttpStatus.OK;

    }

    @RequestMapping(value = "/admin/delete-student", produces = "application/json")
    @ResponseBody
    public String deleteUser(@RequestParam String name, @RequestParam String surname) throws SQLException {
        if (this.db.deleteStudent(name, surname))
            return "{'status' : 'SUCCESS'}";
        return "{'status' : 'FALSE'}";
    }
    */


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

}
