package mta.universitate.Routes;

import mta.universitate.Model.*;
import mta.universitate.Utils.CookieManager;
import mta.universitate.Utils.Hasher;
import mta.universitate.Utils.ParamsParser;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

@RestController
public class RouteLogin {
    Database db = Database.getInstance();

    @RequestMapping(value = "/login", produces = "application/json")
    @ResponseBody
    public String login(@RequestBody String payload, HttpServletResponse response)  {
        try
        {
            HashMap<String, Object> params = ParamsParser.parse(payload);

            User U = db.get(User.fromDB(db.getUserID(params.get("username").toString())));
            if (U.getPassword().contentEquals(Hasher.getHash(params.get("password").toString())))
            {
                Cookie cookie = CookieManager.getInstance().generateCookie(U);
                cookie.setPath("/");
                response.addCookie(cookie);
                return "{\"status\" : \"SUCCESS\"}";
            }
        }
        catch (SQLException exc){}

        return "{\"status\" : \"FAILED\"}";

    }


    @RequestMapping(value = "/logout", produces = "application/json")
    @ResponseBody
    public String logout(@CookieValue(value="uid", defaultValue = "hahaha") Cookie cookie){
        if (CookieManager.getInstance().invalidateCookie(cookie))
            return "{\"status\" : \"SUCCESS\"}";
        return "{\"status\" : \"FAILED\"}";
    }

}


