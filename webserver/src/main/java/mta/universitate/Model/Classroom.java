package mta.universitate.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

enum ClassroomType {
    AMPHITHEATER,
    LABORATORY
}

@RestController
public class Classroom {
    String identifier;
    int capacity;
    Feature features[ ];
    ClassroomType type;

    @RequestMapping("/sali")
    public String getEmptyClassrooms() throws SQLException {
        Database db1;
        db1 = Database.getInstance();
        ResultSet result = db1.executeQuery("SELECT Denumire\n" +
                "FROM sali_de_clasa\n" +
                "LEFT OUTER JOIN orar\n" +
                "ON sali_de_clasa.ID_Sala = orar.FK_Sala\n" +
                "WHERE orar.FK_Sala IS NULL");

        //System.out.printf(result);

        if(result.next()==false)
            return "Empty result";

        StringBuilder stringBuilder=new StringBuilder();

        ResultSetMetaData metadata = result.getMetaData();
        int columnCount = metadata.getColumnCount();
//
//        System.out.printf("%s ", result.getString(1));
//        result.next();
//        System.out.printf("%s ", result.getString(1));



        stringBuilder.append("<style>table, th, td {border: 1px solid black;border-collapse: collapse; text-align:center} table.center{margin-left: auto;margin-right: auto;} table{font-family: arial, sans-serif;width: 70%;}</style>");
        stringBuilder.append("<table class=\"center\"><tr><th>Sali libere</th></tr>");

       do {
            stringBuilder.append("<tr>");
            for (int i = 1; i <= columnCount; i++) {
                stringBuilder.append("<td>").append(result.getString(i)).append("</td>");
            }
            stringBuilder.append("</tr>");
           System.out.printf("%s ", result.getString(1));
        }while(result.next());
        stringBuilder.append("</table>");
        return stringBuilder.toString();
    }

    public void showIdentifier()
    {
        System.out.println(this.identifier);
    }

}
