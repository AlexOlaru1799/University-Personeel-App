package mta.universitate.Routes;

import mta.universitate.Model.Database;
import mta.universitate.Model.Person;
import mta.universitate.Model.Student;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
public class Admin {
    Database db = Database.getInstance();

    @RequestMapping(value = "/admin/reset-password", produces = "application/json")
    @ResponseBody
    public String resetPassword(@RequestParam String name, @RequestParam String surname, @RequestParam String new_pass) throws SQLException {
        if (this.db.resetUserPassword(name, surname , new_pass))
            return "{'status' : 'SUCCESS'}";
        return "{'status' : 'FALSE'}";

        //return String.format("<h1>Name: %s, Surname: %s, New password: %s<h1>", name, surname, new_pass );
    }

    @RequestMapping(value = "/admin/add-employee", produces = "application/json")
    @ResponseBody
    public String addEmployee(@RequestParam String name, @RequestParam String surname, @RequestParam String password, @RequestParam String birthDate, @RequestParam String position, @RequestParam int salary) throws SQLException {
        if (this.db.addEmployee(name, surname, password, position, salary))
            return "{'status' : 'SUCCESS'}";
        return "{'status' : 'FALSE'}";
    }

    @RequestMapping(value = "/admin/delete-user", produces = "application/json")
    @ResponseBody
    public String deleteUser(@RequestParam String name, @RequestParam String surname) throws SQLException {
        if (this.db.deleteUser(name, surname))
            return "{'status' : 'SUCCESS'}";
        return "{'status' : 'FALSE'}";
    }

}
