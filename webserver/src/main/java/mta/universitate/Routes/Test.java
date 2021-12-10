package mta.universitate.Routes;

import mta.universitate.Model.Database;
import mta.universitate.Model.Person;
import mta.universitate.Model.User;
import mta.universitate.Model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@RestController
public class Test {
    Database db = Database.getInstance();

    @RequestMapping("/test")
    public String test() throws SQLException {
        User U = new User();
        U.setId(1005);
        U = db.get(U);


        return String.format("<h1>%s<h1>", U.getUsername() );
    }



}
