package mta.universitate;
import mta.universitate.Model.Database;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
public class initial {

    @RequestMapping("/")
    public String getMess() throws SQLException {
        Database db1;
        // refers to the only object of Database
        db1= Database.getInstance();
        ResultSet result=db1.executeQuery("select * from sali_de_clasa");
        return result.getString(1);
    }
}


