package mta.universitate.Routes;

import mta.universitate.Model.*;
import mta.universitate.Utils.CookieManager;
import mta.universitate.Utils.Hasher;
import mta.universitate.Utils.ParamsParser;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import javax.servlet.http.Cookie;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

@RestController
public class DocumentRoute {

    @PostMapping(value = "/create-document", produces = "application/json")
    @ResponseBody
    public String createDocument(@CookieValue(value = "uid", defaultValue = "test") Cookie C, @RequestBody String payload)
    {
        try
        {
            //Adaugare Document
            HashMap<String, Object> parameters = ParamsParser.parse(payload);
            Secretary S = Secretary.fromEmployee(Employee.fromUser(CookieManager.getInstance().validateCookie(C)));




            String name = parameters.get("name").toString();
            String surname = parameters.get("surname").toString();
            String title = parameters.get("title").toString();
            String content = parameters.get("content").toString();

            Document D = new Document();

            D.setTitle(title);
            D.setContent(content);

            String username=name.toLowerCase(Locale.ROOT) +"."+surname.toLowerCase(Locale.ROOT)+"@mta.ro";
            int UserId = Database.getInstance().getUserID(username);
            User U = new User();
            U.setId(UserId);
            D.setUser(U);

            Database.getInstance().add(D);
            //TODO: Daca ne ramane timp adaugam si un request odata cu acel raport

            return "{\"status\" : \"SUCCESS\"}";
        }
        catch (Exception exc){}

        return "{\"status\" : \"FAILED\"}";
    }
}
