package mta.universitate.Routes;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import mta.universitate.Model.Database;
import mta.universitate.Model.Role;
import mta.universitate.Model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TestJson {

    @GetMapping("/test")
    public String test() throws IOException {
        Student S = Student.fromDB(2);


        return S.toJson();
    }
}
