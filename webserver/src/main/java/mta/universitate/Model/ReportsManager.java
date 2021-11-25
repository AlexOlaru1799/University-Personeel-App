package mta.universitate.Model;
import java.util.Vector;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ReportsManager {
    public Vector<Report> reports = new Vector<Report>();


    // TODO: Do not use any HTML code. That will go in the Reports Class in Routes Package
    /*
    public String populateReports() //throws SQLException
    {
        Report rap1 = new Report("Studentul Marius Popescu doreste permisie din data de 12.07.2022", ReportType.LEAVE);
        Report rap2 = new Report("Studentul Mihai Ionescu solicita o adeverinta pentru medic", ReportType.MEDICAL_CERTIFICATE);
        Report rap3 = new Report("Studentul Iulia Ionel doreste sa se transfere la grupa C112C", ReportType.STUDY_GROUP_CHANGE);

        Database DB = Database.getInstance();

        ResultSet result = DB.executeQuery("Select rapoarte.ID_Raport as \"ID Raport\", tip_rapoarte.Tip_Raport as \"Tip Raport\", studenti.nume FROM rapoarte " +
                " inner join tip_rapoarte ON ID_TipRaport = ID_Raport " +
                " inner join studenti on FK_Student = ID_Student");

        if(result.next()==false)
        {
            // System.out.println("DB Query Error\n\n");
        }

        StringBuilder stringBuilder=new StringBuilder();

        ResultSetMetaData metadata = result.getMetaData();
        int columnCount = metadata.getColumnCount();

        //StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("<style>table, th, td {border: 1px solid black;border-collapse: collapse; text-align:center} table.center{margin-left: auto;margin-right: auto;} table{font-family: arial, sans-serif;width: 70%;}</style>");
        stringBuilder.append("<table class=\"center\">" +
                "<tr>" +
                "<th>ID Raport</th>" +
                "<th>Tip Raport</th>" +
                "<th>Nume</th>" +
                "</tr>");

        while (result.next()) {
            stringBuilder.append("<tr>");
            for (int i = 1; i <= columnCount; i++) {
                stringBuilder.append("<td>").append(result.getString(i)).append("</td>");
            }
            stringBuilder.append("</tr>");
        }
        stringBuilder.append("</table>");


        //return stringBuilder.toString();

        //  while (result.next()) {
        //     for (int i = 1; i <= columnCount; i++) {
        //         stringBuilder.append(result.getString(i)).append("\t");
        //      }
        //   }

        //String[] split = stringBuilder.toString().split(" ");
        //out.println(stringBuilder.toString());
        //System.out.println(split[1]);
        return  stringBuilder.toString();

    }
     */
}