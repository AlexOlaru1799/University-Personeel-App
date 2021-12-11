package mta.universitate.Routes;

import mta.universitate.Model.*;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Locale;

@RestController
public class Admin {
    Database db = Database.getInstance();

    // TODO
    @RequestMapping(value = "/admin/reset-password", produces = "application/json")
    @ResponseBody
    public String resetPassword(@RequestParam String username, @RequestParam String new_pass) throws SQLException {
        if (this.db.resetUserPassword(username, new_pass))
            return "{'status' : 'SUCCESS'}";
        return "{'status' : 'FALSE'}";
    }


    @RequestMapping(value = "/admin/create-employee", produces = "application/json")
    @ResponseBody
    public String addEmployee(@RequestParam String name, @RequestParam String surname, @RequestParam String password, @RequestParam String position, @RequestParam int salary) throws SQLException, NoSuchAlgorithmException {
         Employee E = new Employee();
        E.setName(name);
        E.setSurname(surname);
        E.setSalary(salary);
        E.setPosition(Position.fromDB(db.getPositionID(position)));

        User U = new User();
        U.setPassword(password);
        U.setUsername(name.toLowerCase(Locale.ROOT) + "." + surname.toLowerCase(Locale.ROOT)+"@mta.ro");
        U.setRole(Role.fromDB(db.getRoleID(position)));

        E.setUser(U);

        if (db.add(E))
        {
            return "{'status' : 'SUCCESS'}";
        }
        return "{'status' : 'FAILED'}";

    }


    @RequestMapping(value = "/admin/delete-employee", produces = "application/json")
    @ResponseBody
    public String deleteEmployee(@RequestParam String name, @RequestParam String surname) throws SQLException {

        if (this.db.delete(Employee.fromDB(db.getEmployeeID(name, surname))))
            return "{'status' : 'SUCCESS'}";
        return "{'status' : 'FAILED'}";
    }


    @RequestMapping(value = "/admin/create-student", produces = "application/json")
    @ResponseBody
    public String createStudent(@RequestParam String name, @RequestParam String surname, @RequestParam String password, @RequestParam String major, @RequestParam String study_group, @RequestParam int income) throws SQLException, NoSuchAlgorithmException {
        Student S = new Student();
        S.setName(name);
        S.setSurname(surname);
        S.setIncome(income);
        S.setMajor(Major.fromDB(db.getMajorID(major)));
        S.setStudyGroup(StudyGroup.fromDB(db.getStudyGroupID(study_group)));

        User U = new User();
        U.setPassword(password);
        U.setUsername(name.toLowerCase(Locale.ROOT) + "." + surname.toLowerCase(Locale.ROOT)+"@mta.ro");
        U.setRole(Role.fromDB(db.getRoleID("Student")));

        S.setUser(U);

        if (db.add(S))
        {
            return "{'status' : 'SUCCESS'}";
        }
        return "{'status' : 'FAILED'}";

    }


    @RequestMapping(value = "/admin/delete-student", produces = "application/json")
    @ResponseBody
    public String deleteStudent(@RequestParam String name, @RequestParam String surname) throws SQLException {
        if (this.db.delete(Student.fromDB(db.getStudentID(name, surname))))
            return "{'status' : 'SUCCESS'}";
        return "{'status' : 'FAILED'}";
    }


    /*
    @GetMapping("/materii")
    public String viewSubjects() throws SQLException {
        Database db = Database.getInstance();
        ResultSet result = db.getSubjects();

        if(result == null)
        {
            return "Eroare :(";
        }

        ResultSetMetaData metadata = result.getMetaData();
        int columnCount = metadata.getColumnCount();

        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("<style>table, th, td {border: 1px solid black;border-collapse: collapse; text-align:center} table.center{margin-left: auto;margin-right: auto;} table{font-family: arial, sans-serif;width: 70%;}</style>");
        stringBuilder.append("<table class=\"center\">" +
                "<tr>" +
                "<th>Materie</th>" +
                "<th>Numar credite</th>" +
                "<th>Profesor</th>" +
                "<th>Specializare</th>" +
                "<th>Facultate</th>" +
                "</tr>");

        while (result.next()) {
            stringBuilder.append("<tr>");
            for (int i = 1; i <= columnCount; i++) {
                stringBuilder.append("<td>").append(result.getString(i)).append("</td>");
            }
            stringBuilder.append("</tr>");
        }
        stringBuilder.append("</table>");


        return stringBuilder.toString();
    }

    */

}
