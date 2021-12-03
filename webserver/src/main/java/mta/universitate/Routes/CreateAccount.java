package mta.universitate.Routes;


import mta.universitate.Model.Database;
import mta.universitate.Model.Professor;
import mta.universitate.Model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Objects;

@RestController
public class CreateAccount {

    @PostMapping("/createstudent")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpStatus createStudent(@RequestBody Student S) throws SQLException, NoSuchAlgorithmException {
        Database db1;
        db1 = Database.getInstance();
        String res = db1.createStudent(S);
        if (!Objects.equals(res, "OK")) {
            return HttpStatus.NOT_FOUND;
        }
        return HttpStatus.OK;

    }

    @PostMapping("/createprofessor")
    @ResponseStatus(HttpStatus.CREATED)
    public HttpStatus createProfessor(@RequestBody Professor P) throws SQLException, NoSuchAlgorithmException {
        Database db1;
        db1 = Database.getInstance();
        String res = db1.createTeacher(P);
        if (!Objects.equals(res, "OK")) {
            return HttpStatus.NOT_FOUND;
        }
        return HttpStatus.OK;
    }

}
