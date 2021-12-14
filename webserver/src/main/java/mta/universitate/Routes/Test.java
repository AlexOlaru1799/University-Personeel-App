package mta.universitate.Routes;

import com.fasterxml.jackson.core.JsonProcessingException;
import mta.universitate.Model.*;
import mta.universitate.Model.Professor;
import mta.universitate.Utils.CookieManager;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class Test {
    Database db = Database.getInstance();

    @GetMapping("/test")
    public String test() throws SQLException {

        try
        {
            ArrayList<Student> students = db.getAllStudents();
            StringBuilder response = new StringBuilder();

            for (Student stud : students)
                response.append(stud.toJson());

            return response.toString();
        }
        catch (JsonProcessingException exc){}

        return null;


    }



}
