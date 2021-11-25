package mta.universitate.Routes;

import mta.universitate.Model.ReportsManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class Reports {
    ReportsManager manager;

    public Reports()
    {
        this.manager= new ReportsManager();

        //manager.populateReports();
    }

    /*
    @RequestMapping("/reports")
    public String seePendingReports() throws SQLException
    {
        String temp = "<table border=\"2\" cellspacing=\"5\" cellpadding=\"4\" width=\"900\" align=\"center\">";

        if(this.rep.reports.size() == 0)
        {
            this.populateReports();
        }

        for(int i = 0; i<this.reports.size(); i++)
        {
            if(i==0)
            {
                temp+="  <tr >";
                temp+="    <th>Tip Raport</th>";
                temp+="    <th>Descriere</th>";
                temp+="  </tr>" ;
                temp+="  <tr>";
                temp+="<td>"+ this.reports.elementAt(i).getType()+"</td>";
                temp+="<td>"+ this.reports.elementAt(i).getContent()+"</td>";
                temp+="  </tr>" ;
            }
            else if(i!=this.reports.size()-1)
            {
                temp+="  <tr>";
                temp+="<td>"+ this.reports.elementAt(i).getType()+"</td>";
                temp+="<td>"+ this.reports.elementAt(i).getContent()+"</td>";
                temp+="  </tr>" ;
            }
            else
            {
                temp+="  <tr>";
                temp+="<td>"+ this.reports.elementAt(i).getType()+"</td>";
                temp+="<td>"+ this.reports.elementAt(i).getContent()+"</td>";
                temp+="  </tr>" ;
                temp+="</table>";
            }
        }
        //System.out.println(temp);

        String bd_result = this.populateReports();
        return bd_result;

    }

     */
}
