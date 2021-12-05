package mta.universitate.Routes;

import mta.universitate.Model.Database;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
public class ArchiveStudents {

    private String getStudentFolderName(ResultSet res, StringBuilder id) throws SQLException {

        StringBuilder stringBuilder = new StringBuilder();
        id.append(res.getString("ID"));
        stringBuilder.append(res.getString("Nume").toLowerCase(Locale.ROOT))
                .append("_")
                .append((res.getString("Prenume")).toLowerCase(Locale.ROOT))
                .append("_")
                .append(res.getString("Grupa"));

        return stringBuilder.toString();
    }

    private String getStudentGrades(ResultSet res) throws SQLException {
        ResultSetMetaData metaData=res.getMetaData();
        int countColumns=metaData.getColumnCount();

        StringBuilder stringBuilder= new StringBuilder();

        while(res.next())
        {
            for (int i = 1; i <= countColumns ; i++) {
                stringBuilder.append(res.getString(i)).append(" ");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    @GetMapping(value = "/archive-students", produces = "application/json")
    @ResponseBody
    public String archiveStudents() throws SQLException, IOException {
        Database db1;
        db1 = Database.getInstance();

        ResultSet res = db1.get4thYearStudents();
        res.next();
        res.next();

        StringBuilder id = new StringBuilder();
        String folderName = getStudentFolderName(res, id);


        res = db1.getStudentGrades(id.toString());
        String grades = getStudentGrades(res);

        String zipTest = folderName + "/grades.txt";

        File f = new File(".\\test.zip");
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(f));
        ZipEntry e = new ZipEntry(zipTest);
        out.putNextEntry(e);

        byte[] data = grades.toString().getBytes();
        out.write(data, 0, data.length);
        out.closeEntry();
        out.close();

        return folderName;
        //return "{'status' : 'SUCCESS'}";
    }

}
