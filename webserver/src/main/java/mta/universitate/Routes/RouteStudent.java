package mta.universitate.Routes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import mta.universitate.Model.*;
import mta.universitate.Model.Professor;
import mta.universitate.Model.Secretary;
import mta.universitate.Utils.CookieManager;
import mta.universitate.Utils.ParamsParser;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@RestController
public class RouteStudent {
    Database db = Database.getInstance();

 /*   @RequestMapping(value = "/student/my-profile", produces = "application/json")
    @ResponseBody
    public String viewProfile(@CookieValue(value = "uid", defaultValue = "test") Cookie C,  @RequestBody String payload){

        try
        {
            HashMap<String, Object> parameters = ParamsParser.parse(payload);
            Student S = Student.fromDB(CookieManager.getInstance().validateCookie(C).getId());
            String initDate=parameters.get("date").toString();

            StringBuilder response=new StringBuilder();

            ArrayList<Schedule>modules=S.get;
            ArrayList<Schedule>modules2=new ArrayList<>();

            for (Schedule sched : modules)
                modules2.add(sched);

            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            return String.format("{\"status\" : \"SUCCESS\", \"result\" : %s }", ow.writeValueAsString(modules2) );
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return "{'status' : 'FAILED'}";
    }
    */
 @RequestMapping(value = "/student/studentSchedule", produces = "application/json")
 @ResponseBody
 public String viewScheduleStudent(@CookieValue(value = "uid", defaultValue = "test") Cookie C,  @RequestBody String payload){

     try
     {
         HashMap<String, Object> parameters = ParamsParser.parse(payload);
         Student S = Student.fromUser(CookieManager.getInstance().validateCookie(C));
         String initDate=parameters.get("date").toString();

         StringBuilder response=new StringBuilder();

         ArrayList<Schedule>modules=S.viewScheduleForStudent(S.getId(),initDate);
         ArrayList<Schedule>modules2=new ArrayList<>();

         for (Schedule sched : modules)
             modules2.add(sched);

         ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
         return String.format("{\"status\" : \"SUCCESS\", \"result\" : %s }", ow.writeValueAsString(modules2) );
     } catch (Exception exc) {
         exc.printStackTrace();
     }

     return "{\"status\" : \"FAILED\"}";
 }





}
