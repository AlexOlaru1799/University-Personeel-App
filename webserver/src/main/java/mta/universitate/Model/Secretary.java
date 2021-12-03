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

public class Secretary extends Employee {

    public Secretary(String name, String surname, String password,
                     String birthDate, int salary)
    {
        super(name, surname, password, birthDate, Position.SECRETARY, salary);
    }



    public void showStatistics(Student S)
    {
        System.out.print("Showing stats for student "+
                S.getName());
    }


    public void showStatistics(StudyGroup G)
    {
        System.out.print("Showing stats for study group");
    }

    //TO DO statistici pentru an de studiu
    public void showStatistics(StudyYear A)
    {
        System.out.print("Year "+
                A.getYear());
    }
}
