package com.example.application.views.Routes;


import com.example.application.views.BackEnd.Database;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

@RestController
public class Students {
    @RequestMapping("/students")
    public String getStudents() throws SQLException {
        Database db1;
        db1 = Database.getInstance();

        ResultSet result = db1.executeQuery(
                "SELECT [dbo].[studenti].Nume,[dbo].[studenti].Prenume,[dbo].[specializari].Denumire,[dbo].[grupe_studiu].denumire_grupa " +
                        "FROM [dbo].[studenti] " +
                        "INNER JOIN [dbo].[specializari] " +
                        "ON [dbo].[studenti].FK_Specializare = [dbo].[specializari].ID_Specializare " +
                        "INNER JOIN [dbo].[grupe_studiu] " +
                        "ON [dbo].[studenti].FK_Grupa = [dbo].[grupe_studiu].ID_Grupa " +
                        "ORDER BY [dbo].[grupe_studiu].denumire_grupa ASC");


        if(result == null)
        {
            return "Error :(";
        }

        ResultSetMetaData metadata = result.getMetaData();
        int columnCount = metadata.getColumnCount();

        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("<style>table, th, td {border: 1px solid black;border-collapse: collapse; text-align:center} table.center{margin-left: auto;margin-right: auto;} table{font-family: arial, sans-serif;width: 70%;}</style>");
        stringBuilder.append("<table class=\"center\">" +
                "<tr>" +
                "<th>Nume Student</th>" +
                "<th>Prenume Student</th>" +
                "<th>Specializare</th>" +
                "<th>Grupa Student</th>" +
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

    //-------------------------------------------------------

    @GetMapping("/StudentPersonalInfo")
    public String viewPersonalInfo() throws SQLException {
        String nume="Gigel";
        String prenume="Cornel";

        Database db = Database.getInstance();
        ResultSet result = db.getStudentInfobyName(nume,prenume);

        if(result == null)
        {
            return "Error!!";
        }

        ResultSetMetaData metadata = result.getMetaData();
        int columnCount = metadata.getColumnCount();

        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("<style>table, th, td {border: 1px solid black;border-collapse: collapse; text-align:center} table.center{margin-left: auto;margin-right: auto;} table{font-family: arial, sans-serif;width: 70%;}</style>");
        stringBuilder.append("<table class=\"center\">" +
                "<tr>" +
                "<th>Student</th>" +
                "<th>ID Student</th>" +
                "<th>An de studiu</th>" +
                "<th>Solda</th>" +
                "<th>Grupa</th>" +
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



    //------------------------------------------------

    @GetMapping("/StudentGrades")
    public String viewStudentGrades(/*String nume, String prenume*/) throws SQLException {
        String nume="Miroseala";
        String prenume="Stefania";

        Database db = Database.getInstance();
        ResultSet result = db.getStudentGradesbyName(nume,prenume);

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
                "Notele studentului "+ nume + " " + prenume +" sunt: </tr>" +
                "<tr>" +
                "<th>Nota</th>" +
                "<th>Data</th>" +
                "<th>Materie</th>" +
                "<th>Profesor</th>" +
                "<th>Grupa</th>" +
                "<th>An de studiu</th>" +
                "<th>Specializare</th>" +
                "<th>Facultate</th>"+
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

    //-----------------------------------------------

    @GetMapping("/StudentClasses")
    public String viewClasses() throws SQLException {
        String nume="Gigel";
        String prenume="Cornel";

        Database db = Database.getInstance();
        ResultSet result = db.getStudentClasses(nume,prenume);

        if(result == null)
        {
            return "Error!!";
        }

        ResultSetMetaData metadata = result.getMetaData();
        int columnCount = metadata.getColumnCount();

        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("<style>table, th, td {border: 1px solid black;border-collapse: collapse; text-align:center} table.center{margin-left: auto;margin-right: auto;} table{font-family: arial, sans-serif;width: 70%;}</style>");
        stringBuilder.append("<table class=\"center\">" +
                "<tr>" +
                "Materiile studentului "+ nume + " " + prenume +" sunt: </tr>" +
                "<th>Nume Materie</th>" +
                "<th>Numar Credite</th>" +
                "<th>An de studiu</th>" +
                "<th>Denumire grupa</th>" +
                "<th>Specializare</th>" +
                "<th>Facultate</th>" +
                "<th>Profesor</th>"+
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


    //---------------------------

    @GetMapping("/StudentSchedule")
    public String viewSchedule() throws SQLException {
        String nume="Gigel";
        String prenume="Cornel";

        Database db = Database.getInstance();
        ResultSet result = db.getStudentSchedule(nume,prenume);

        if(result == null)
        {
            return "Error!!";
        }

        ResultSetMetaData metadata = result.getMetaData();
        int columnCount = metadata.getColumnCount();

        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("<style>table, th, td {border: 1px solid black;border-collapse: collapse; text-align:center} table.center{margin-left: auto;margin-right: auto;} table{font-family: arial, sans-serif;width: 70%;}</style>");
        stringBuilder.append("<table class=\"center\">" +
                "<tr>" +
                "Orarul studentului "+ nume + " " + prenume +" este: </tr>" +
                "<th>Ora</th>" +
                "<th>Nume Grupa</th>" +
                "<th>Nume Materie</th>" +
                "<th>Denumire Sala</th>" +
                "<th>An de studiu</th>" +
                "<th>Specializare</th>" +
                "<th>Facultate</th>" +
                "<th>Profesor</th>"+
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

    //----------------------------
    @GetMapping("/InsertReport")
    public String uploadStudentReport(/*int FKTipRaport, String data, int FK_User, int FK_Supervizor*/) throws SQLException {
        int FKTipRaport=1;
        int FK_User=1007;
        String data="2021-11-10 08:00";
        int FK_Supervizor=23;

        Database db = Database.getInstance();
        ResultSet result = db.uploadReport(FKTipRaport, data, FK_User, FK_Supervizor);

        StringBuilder stringBuilder=new StringBuilder();

        if(result!=null)
        {
            stringBuilder.append("Eroare :(");
        }
        else
        {
            stringBuilder.append("S-a adaugat Raportul!");
        }

        return stringBuilder.toString();
    }

}
