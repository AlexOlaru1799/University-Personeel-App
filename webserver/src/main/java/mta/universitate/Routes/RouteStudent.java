package mta.universitate.Routes;

import mta.universitate.Model.Database;
import mta.universitate.Model.Student;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

public class RouteStudent {
    Database db = Database.getInstance();

    /*
    @RequestMapping(value = "/student/my-profile", produces = "application/json")
    @ResponseBody
    public String profile(){

        try
        {
            // TODO
            // Parse cookie so I can return the Student object
            // Student S = CookieManager.parseCookie(cookie);
            // return S.toJSON();
            return "{'status' : 'SUCCESS'}";
        }
        catch (SQLException e){}

        return "{'status' : 'FAILED'}";
    }

     */

}
