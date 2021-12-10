package mta.universitate.Routes;

import mta.universitate.Model.Database;
import mta.universitate.Model.Employee;
import mta.universitate.Model.Professor;
import mta.universitate.Model.User;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
public class Test {
    Database db = Database.getInstance();

    @RequestMapping("/test")
    public String test() throws SQLException {
        Employee E = new Employee();
        E.setId(21);
        E = db.get(E);

        Professor prof = Professor.fromEmployee(E);




        return String.format("<h1>%s<h1>", prof.getName() );
    }



}
