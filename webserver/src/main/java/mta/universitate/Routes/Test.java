package mta.universitate.Routes;

import mta.universitate.Model.Database;
import mta.universitate.Model.Employee;
import mta.universitate.Model.Professor;
import mta.universitate.Model.User;
import mta.universitate.Utils.CookieManager;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;

import java.sql.SQLException;

@RestController
public class Test {
    Database db = Database.getInstance();

    @GetMapping("/test")
    public String test() throws SQLException {
        User U = new User();
        U.setUsername("mirela.ghica@mta.ro");
        Employee E = Employee.fromUser(U);

        return String.format("<h1>My name is %s %s<h1>", E.getName(), E.getSurname());
    }



}
