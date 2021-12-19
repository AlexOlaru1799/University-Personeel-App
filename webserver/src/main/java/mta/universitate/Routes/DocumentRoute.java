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
            User U = CookieManager.getInstance().validateCookie(C);
            String title = parameters.get("title").toString();
            String content = parameters.get("content").toString();

            Document D = new Document();
            D.setUser(U);
            D.setTitle(title);
            D.setContent(content);

            Database.getInstance().add(D);
            //TODO: Daca ne ramane timp adaugam si un request odata cu acel raport

            return "{\"status\" : \"SUCCESS\"}";
        }
        catch (Exception exc){}

        return "{\"status\" : \"FAILED\"}";
    }
}
