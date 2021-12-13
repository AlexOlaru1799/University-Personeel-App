package mta.universitate.Routes;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import mta.universitate.Model.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TestJson {

    @GetMapping("/test")
    public String test() throws IOException {
        Role role = new Role();
        role.setId(1);
        role.setDescription("descriere");


        return role.toJson();
    }
}
