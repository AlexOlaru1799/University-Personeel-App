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

public class Classroom {
    String identifier;
    int capacity;
    Feature features[ ];
    ClassroomType type;

    public void showIdentifier()
    {
        System.out.println(this.identifier);
    }

}
