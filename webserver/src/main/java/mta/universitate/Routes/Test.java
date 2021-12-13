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

    @RequestMapping("/test")
    public String test(@CookieValue(value="uid", defaultValue = "hahaha") Cookie cookie) throws SQLException {

        User U = CookieManager.getInstance().validateCookie(cookie);
        return String.format("<h1>My name is %s<h1>", U.getUsername());
    }



}
