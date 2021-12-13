package mta.universitate.Routes;

import mta.universitate.Model.*;
import mta.universitate.Utils.Hasher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;


@RestController
public class RouteLogin {
    Database db = Database.getInstance();

    @RequestMapping(value = "/login", produces = "application/json")
    @ResponseBody
    public String login(@RequestParam String username, @RequestParam String password)  {
        try
        {
            User U = db.get(User.fromDB(db.getUserID(username)));
            if (U.getPassword().contentEquals(Hasher.getHash(password)))
            {
                //TODO : Generate cookie and return it. Also, save it locally for validation
                return "{'status' : 'SUCCESS'}";
            }
        }
        catch (SQLException exc){}

        return "{'status' : 'FAILED'}";

    }

}


