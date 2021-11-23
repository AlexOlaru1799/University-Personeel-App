package mta.universitate.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;


/**
 * Clasa Secretar
 *
 * Clasa utilizata pentru a implementa utilitatile secretarului
 *
 */
@RestController
public class Secretar extends Angajat{

    public Secretar()
    {
        super();
    }
    public Secretar(String _nume,String _prenume,
                   String _dataNasterii)
    {
        super(_nume,_prenume,_dataNasterii);
    }

    @RequestMapping("/studenti")
    public String getStudenti() throws SQLException {
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
            return "Eroare :(";
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


    public void afiseazaStatistici(Student S)
    {
        System.out.print("Afisare statistici student "+
                S.getNume());
    }


    public void afiseazaStatistici(GrupaDeStudiu G)
    {
        System.out.print("Afisare gurpa de studiu");
    }

    //TO DO statistici pentru an de studiu
    public void afiseazaStatistici(AnDeStudiu A)
    {
        System.out.print("An "+
                A.getAn());
    }
}
