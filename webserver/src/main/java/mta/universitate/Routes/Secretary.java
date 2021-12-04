package mta.universitate.Routes;

import mta.universitate.Model.Database;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

@RestController
public class Secretary {
    @GetMapping("/materiiSpecializare")
    public String viewSubjects(/*String specialization*/) throws SQLException {

        String specialization="Calculatoare";

        Database db = Database.getInstance();
        ResultSet result = db.getSubjectsbySpecialization(specialization);

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

    @GetMapping("/addMaterie")
    public String addSubject(/*int credits, int IDTeacher, String name, String specialization*/) throws SQLException {
        int credits=4;
        int IDTeacher=22;
        String name="Microprocesoare";
        String specialization="Calculatoare";

        Database db = Database.getInstance();
        ResultSet result = db.addSubject(credits, IDTeacher, name,specialization);

        StringBuilder stringBuilder=new StringBuilder();

        if(result!=null)
        {
            stringBuilder.append("Eroare!");
        }
        else
        {
            stringBuilder.append("S-a adaugat materia!");
        }

        return stringBuilder.toString();
    }

}
