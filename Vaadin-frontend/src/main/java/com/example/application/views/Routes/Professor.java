package com.example.application.views.Routes;


import com.example.application.views.BackEnd.Database;
import com.example.application.views.BackEnd.Schedule;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

@RestController
public class Professor
{

//    @GetMapping("/noteStudenti")
//    public String viewStudentsGrades() throws SQLException {
//        Database db = Database.getInstance();
//        ResultSet result = db.getStudentsGrades();
//
//        if(result == null)
//        {
//            return "Error!!";
//        }
//
//        ResultSetMetaData metadata = result.getMetaData();
//        int columnCount = metadata.getColumnCount();
//
//        StringBuilder stringBuilder=new StringBuilder();
//        stringBuilder.append("<style>table, th, td {border: 1px solid black;border-collapse: collapse; text-align:center} table.center{margin-left: auto;margin-right: auto;} table{font-family: arial, sans-serif;width: 70%;}</style>");
//        stringBuilder.append("<table class=\"center\">" +
//                "<tr>" +
//                "<th>Nota</th>" +
//                "<th>Data</th>" +
//                "<th>Materie</th>" +
//                "<th>Profesor</th>" +
//                "<th>Student</th>" +
//                "<th>ID Student</th>" +
//                "<th>Grupa</th>" +
//                "<th>An de studiu</th>" +
//                "<th>Specializare</th>" +
//                "<th>Facultate</th>" +
//                "</tr>");
//
//        while (result.next()) {
//            stringBuilder.append("<tr>");
//            for (int i = 1; i <= columnCount; i++) {
//                stringBuilder.append("<td>").append(result.getString(i)).append("</td>");
//            }
//            stringBuilder.append("</tr>");
//        }
//        stringBuilder.append("</table>");
//
//
//        return stringBuilder.toString();
//    }

    @GetMapping("/noteStudent")
    public String viewStudentGrades(/*String nume, String prenume*/) throws SQLException {
        String nume="Gigel";
        String prenume="Cornel";

        Database db = Database.getInstance();
        ResultSet result = db.getStudentGradess(nume,prenume);

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
                "<th>Nota</th>" +
                "<th>Data</th>" +
                "<th>Materie</th>" +
                "<th>Profesor</th>" +
                "<th>Student</th>" +
                "<th>ID Student</th>" +
                "<th>Grupa</th>" +
                "<th>An de studiu</th>" +
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

    @GetMapping("/addNotaStudent")
    public String addStudentGrade(/*int nota, int FKMaterie, String data, int FK_Student*/) throws SQLException {
        int nota=9;
        int FKMaterie=54;
        String data="2020-08-14";
        int FK_Student=3;

        Database db = Database.getInstance();
        ResultSet result = db.addStudentGrade(nota,FKMaterie,data,FK_Student);

        StringBuilder stringBuilder=new StringBuilder();

        if(result!=null)
        {
            stringBuilder.append("Eroare!");
        }
        else
        {
            stringBuilder.append("S-a adaugat nota!");
        }

        return stringBuilder.toString();
    }

    @GetMapping("/orarProfesor")
    public String viewTeacherSchedule(/*String surname, String name*/) throws SQLException {
        String surname="Arseni";
        String name="Stefan";

        Schedule schedule=new Schedule();
        ResultSet result = schedule.getTeacherSchedule(surname,name);

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
                "<th>Ora</th>" +
                "<th>Grupa</th>" +
                "<th>Materie</th>" +
                "<th>Sala</th>" +
                "<th>Tip sala</th>" +
                "<th>Profesor</th>" +
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

    @GetMapping("/orarGrupa")
    public String viewGroupSchedule(/*String groupName*/) throws SQLException {
        String groupName="C114C";

        Schedule schedule=new Schedule();
        ResultSet result = schedule.getGroupSchedule(groupName);

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
                "<th>Ora</th>" +
                "<th>Sala</th>" +
                "<th>Tip modul</th>" +
                "<th>Materie</th>" +
                "<th>Profesor</th>" +
                "<th>Grupa</th>" +
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

//    @GetMapping("/adevSalariat")
//    public String viewRequests() throws SQLException {
//        Database db = Database.getInstance();
//        ResultSet result = db.getRequests();
//
//        if(result == null)
//        {
//            return "Eroare :(";
//        }
//
//        ResultSetMetaData metadata = result.getMetaData();
//        int columnCount = metadata.getColumnCount();
//
//        StringBuilder stringBuilder=new StringBuilder();
//        stringBuilder.append("<style>table, th, td {border: 1px solid black;border-collapse: collapse; text-align:center} table.center{margin-left: auto;margin-right: auto;} table{font-family: arial, sans-serif;width: 70%;}</style>");
//        stringBuilder.append("<table class=\"center\">" +
//                "<tr>" +
//                "<th>ID Raport</th>" +
//                "<th>Data</th>" +
//                "<th>Tip Raport</th>" +
//                "<th>Username</th>" +
//                "<th>Supervizor</th>" +
//                "<th>Functie supervizor</th>" +
//                "</tr>");
//
//        while (result.next()) {
//            stringBuilder.append("<tr>");
//            for (int i = 1; i <= columnCount; i++) {
//                stringBuilder.append("<td>").append(result.getString(i)).append("</td>");
//            }
//            stringBuilder.append("</tr>");
//        }
//        stringBuilder.append("</table>");
//
//
//        return stringBuilder.toString();
//    }

    @GetMapping("/addRaportAdevSalariat")
    public String askForTeacherSalaryCertificate(/*int tipRaport, String data, int FK_User,int FK_Supervizor*/) throws SQLException {
        int tipRaport=4;
        String data="2021-11-28T10:00:00.000";
        int FK_User=1002;
        int FK_Supervizor=23;

        Database db = Database.getInstance();
        ResultSet result = db.askForTeacherSalaryCertificate(tipRaport,data,FK_User,FK_Supervizor);

        StringBuilder stringBuilder=new StringBuilder();

        if(result!=null)
        {
            stringBuilder.append("Eroare!");
        }
        else
        {
            stringBuilder.append("S-a adaugat raportul!");
        }

        return stringBuilder.toString();
    }
}
