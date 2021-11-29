package mta.universitate.Routes;


import mta.universitate.Model.Professor;
import mta.universitate.Model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateAccount {

    @PostMapping("/createstudent")
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(@RequestBody Student S)
    {

    }

    @PostMapping("/createprofessor")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProfessor(@RequestBody Professor P)
    {

    }

}
