package mta.universitate.Model;
import java.util.Vector;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

@RestController
public class ReportsManager {
    public Vector<Raport> rapoarte = new Vector<Raport>();

    public String populateRapoarte() throws SQLException
    {
        Raport rap1 = new Raport("Studentul Marius Popescu doreste permisie din data de 12.07.2022",RaportType.Permisie);
        Raport rap2 = new Raport("Studentul Mihai Ionescu solicita o adeverinta pentru medic",RaportType.Adeverinta);
        Raport rap3 = new Raport("Studentul Iulia Ionel doreste sa se transfere la grupa C112C",RaportType.SchimbareGrupa);


        rapoarte.add(rap1);
        rapoarte.add(rap2);
        rapoarte.add(rap3);



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




    @RequestMapping("/rapoarte")
    public String seePendingReports() throws SQLException
    {

        String temp = "<table border=\"2\" cellspacing=\"5\" cellpadding=\"4\" width=\"900\" align=\"center\">";

        if(this.rapoarte.size() == 0)
        {
            this.populateRapoarte();
        }

        for(int i = 0;i<this.rapoarte.size();i++)
        {
            if(i==0)
            {
                temp+="  <tr >";
                temp+="    <th>Tip Raport</th>";
                temp+="    <th>Descriere</th>";
                temp+="  </tr>" ;
                temp+="  <tr>";
                temp+="<td>"+ this.rapoarte.elementAt(i).getTipRaport()+"</td>";
                temp+="<td>"+ this.rapoarte.elementAt(i).getDescriere()+"</td>";
                temp+="  </tr>" ;
            }
            else if(i!=this.rapoarte.size()-1)
            {
                temp+="  <tr>";
                temp+="<td>"+ this.rapoarte.elementAt(i).getTipRaport()+"</td>";
                temp+="<td>"+ this.rapoarte.elementAt(i).getDescriere()+"</td>";
                temp+="  </tr>" ;
            }
            else
            {
                temp+="  <tr>";
                temp+="<td>"+ this.rapoarte.elementAt(i).getTipRaport()+"</td>";
                temp+="<td>"+ this.rapoarte.elementAt(i).getDescriere()+"</td>";
                temp+="  </tr>" ;
                temp+="</table>";
            }
        }
        //System.out.println(temp);

        String bd_result = this.populateRapoarte();
        return bd_result;

    }

    public static void main(String[] args)throws SQLException {

        ReportsManager rep = new ReportsManager();

        rep.populateRapoarte();

    }

}