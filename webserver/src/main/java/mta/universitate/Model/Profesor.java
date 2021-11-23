package mta.universitate.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

@RestController
public class Profesor extends Angajat{

    public Profesor()
    {
        super();
    }
    public Profesor(String _nume,String _prenume,
                   String _dataNasterii)
    {
        super(_nume,_prenume,_dataNasterii);
    }

    public void acordaNota(Student S,int nota)
    {
        System.out.print("Profesor adauga nota" +
                nota + "studentului " +S.getNume());
    }

    @RequestMapping("/orar")
    public String getMySchedule() throws SQLException
    {
        Database db1;
        db1 = Database.getInstance();
        ResultSet result = db1.executeQuery(
                "SELECT [dbo].[orar].[Ora], [dbo].[grupe_studiu].[ID_Grupa], [dbo].[materii].[ID_Materie], [dbo].[angajati].[Nume],[dbo].[angajati].[Prenume]" +
                        "FROM [dbo].[angajati]" +
                        "INNER JOIN [dbo].[functii]" +
                        "ON [dbo].[functii].[ID_Functie]=[dbo].[angajati].[ID_Angajat]" +
                        "INNER JOIN [dbo].[ore]" +
                        "ON [dbo].[ore].[FK_Titular]=[dbo].[angajati].[ID_Angajat]" +
                        "INNER JOIN [dbo].[orar]" +
                        "ON [dbo].[orar].[FK_Ore]=[dbo].[ore].[ID_Ora]" +
                        "INNER JOIN [dbo].[grupe_studiu]" +
                        "ON [dbo].[grupe_studiu].[ID_Grupa]=[dbo].[orar].[FK_Grupa]" +
                        "INNER JOIN [dbo].[materii]" +
                        "ON [dbo].[materii].[FK_Profesor]=[dbo].[angajati].[ID_Angajat]" +
                        "WHERE [dbo].[functii].[Denumire]='Profesor'" +
                        "ORDER BY [dbo].[angajati].[Nume] ASC");


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
                "<th>ID Grupa</th>" +
                "<th>ID Materie</th>" +
                "<th>Nume profesor</th>" +
                "<th>Prenume profesor</th>" +
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


}
