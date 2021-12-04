package mta.universitate.Routes;

import mta.universitate.Model.Database;
import mta.universitate.Model.Person;
import mta.universitate.Model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@RestController
public class Admin {
    Database db = Database.getInstance();

    @RequestMapping(value = "/admin/reset-password", produces = "application/json")
    @ResponseBody
    public String resetPassword(@RequestParam String username, @RequestParam String new_pass) throws SQLException {
        System.out.printf("Salut");
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
    public HttpStatus createStudent(@RequestParam String name, @RequestParam String surname, @RequestParam String password, @RequestParam String birthDate, @RequestParam String position, @RequestParam int salary) throws SQLException, NoSuchAlgorithmException {
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

}
