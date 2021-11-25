package mta.universitate.Routes;

import mta.universitate.Model.Database;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;


@RestController
public class Index {

    @RequestMapping("/")
    public String main() throws SQLException {

        return "<h1>Index page<h1>";
    }
}


