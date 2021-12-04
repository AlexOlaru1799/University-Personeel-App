package com.example.application.views.Routes;

import com.example.application.views.BackEnd.Database;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

@RestController
public class ProfessorProfile {


    @GetMapping("/professor/{id}")
    public String getProfessorProfile(@PathVariable String id) throws SQLException {
        Database db1;
        db1 = Database.getInstance();
        ResultSet result = db1.getProfessorInfo(id);

        if(result == null)
        {
            return "Error :(";
        }

        ResultSetMetaData metadata = result.getMetaData();
        int columnCount = metadata.getColumnCount();
        StringBuilder stringBuilder=new StringBuilder();


        while (result.next()) {

            for (int i = 1; i <= columnCount; i++) {
                stringBuilder.append(result.getString(i)).append(" ");
            }
        }
        stringBuilder.append("\n");

        result = db1.getProfessorClasses(id);

        if(result == null)
        {
            return "Error :(";
        }

        metadata = result.getMetaData();
        columnCount = metadata.getColumnCount();

        stringBuilder.append("<style>table, th, td {border: 1px solid black;border-collapse: collapse; text-align:center} table.center{margin-left: auto;margin-right: auto;} table{font-family: arial, sans-serif;width: 70%;}</style>");
        stringBuilder.append("<table class=\"center\">" +
                "<tr>" +
                "<th>Nume Materie</th>" +
                "<th>Tip ora</th>" +
                "<th>Ora</th>" +
                "<th>Grupa</th>" +
                "<th>Sala de clasa</th>" +
                "</tr>");

        while (result.next()) {
            stringBuilder.append("<tr>");
            for (int i = 2; i <= columnCount; i++) {
                stringBuilder.append("<td>").append(result.getString(i)).append("</td>");
            }
            stringBuilder.append("</tr>");
        }
        stringBuilder.append("</table>");

        return stringBuilder.toString();
    }
}
