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
            return "trist:(";
        //return result.getString(1);
        StringBuilder stringBuilder=new StringBuilder();

        ResultSetMetaData metadata = result.getMetaData();
        int columnCount = metadata.getColumnCount();
        while (result.next()) {
            for (int i = 1; i <= columnCount; i++) {
                stringBuilder.append(result.getString(i)).append("\t");
            }
        }

        return stringBuilder.toString();
    }

    public void afiseazaDenumire()
    {
        System.out.println(this.denumire);
    }

}
