package com.example.application.views.Routes;


import com.example.application.views.BackEnd.Database;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Objects;

@RestController
public class DeleteUser {

    @DeleteMapping("/deletestudent/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HttpStatus deleteStudent(String id) throws SQLException {
        Database db1;
        db1 = Database.getInstance();
        String res = db1.deleteStudent(id);

        if (!Objects.equals(res, "OK")) {
            return HttpStatus.NOT_FOUND;
        }


        return HttpStatus.OK;
    }

    @DeleteMapping("/deleteprofessor/{id}")
    @ResponseStatus(HttpStatus.OK)
    public HttpStatus deleteProfessor(String id)
    {


        return HttpStatus.OK;
    }
}
