package mta.universitate.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

enum TipSala{
    Amfiteatru,Laborator
}

@RestController
public class SalaDeClasa {
    String denumire;
    int capacitate;
    Dotare dotari[ ];
    TipSala tipSala;



//    public SalaDeClasa(String _denumire,int _capacitate,TipSala _tip)
//    {
//        this.denumire = _denumire;
//        this.capacitate = _capacitate;
//        this.tipSala = _tip;
//    }

    @RequestMapping("/sali")
    public String getSali() throws SQLException {
        Database db1;
        db1 = Database.getInstance();
        ResultSet result = db1.executeQuery("select * from sali_de_clasa");

        if(result.next()==false)
            return "Empty result";

        StringBuilder stringBuilder=new StringBuilder();

        ResultSetMetaData metadata = result.getMetaData();
        int columnCount = metadata.getColumnCount();

        stringBuilder.append("<style>table, th, td {border: 1px solid black;border-collapse: collapse; text-align:center} table.center{margin-left: auto;margin-right: auto;} table{font-family: arial, sans-serif;width: 70%;}</style>");
        stringBuilder.append("<table class=\"center\"><tr><th>Id Sala</th><th>Denumire</th><th>Capacitate</th><th>Tipul Salii</th></tr>");

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

    public void afiseazaDenumire()
    {
        System.out.println(this.denumire);
    }

}
