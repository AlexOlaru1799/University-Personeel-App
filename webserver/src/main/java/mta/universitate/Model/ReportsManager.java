package mta.universitate.Model;
import java.util.Vector;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportsManager {
    public Vector<Raport> rapoarte = new Vector<Raport>();

    public void populateRapoarte()
    {
        Raport rap1 = new Raport("Studentul Marius Popescu doreste permisie din data de 12.07.2022",RaportType.Permisie);
        Raport rap2 = new Raport("Studentul Mihai Ionescu solicita o adeverinta pentru medic",RaportType.Adeverinta);
        Raport rap3 = new Raport("Studentul Iulia Ionel doreste sa se transfere la grupa C112C",RaportType.SchimbareGrupa);


        rapoarte.add(rap1);
        rapoarte.add(rap2);
        rapoarte.add(rap3);

    }


    @RequestMapping("/rapoarte")
    public String seePendingReports()
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
        return temp;

    }

}
