package mta.universitate.Routes;

import mta.universitate.Model.*;
import mta.universitate.Utils.CookieManager;
import mta.universitate.Utils.Hasher;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

@RestController
public class RouteLogin {
    Database db = Database.getInstance();

    @RequestMapping(value = "/login", produces = "application/json")
    @ResponseBody
    public String login(@RequestParam String username, @RequestParam String password, HttpServletResponse response)  {
        try
        {
            User U = db.get(User.fromDB(db.getUserID(username)));
            if (U.getPassword().contentEquals(Hasher.getHash(password)))
            {
                Cookie cookie = CookieManager.getInstance().generateCookie(U);
                cookie.setPath("/");
                response.addCookie(cookie);
                return "{'status' : 'SUCCESS'}";
            }
        }
        catch (SQLException exc){}

        return "{'status' : 'FAILED'}";

    }


    @RequestMapping(value = "/logout", produces = "application/json")
    @ResponseBody
    public String logout(@CookieValue(value="uid", defaultValue = "hahaha") Cookie cookie){
        if (CookieManager.getInstance().invalidateCookie(cookie))
            return "{'status' : 'SUCCESS'}";
        return "{'status' : 'FAILED'}";
    }

}


