package mta.universitate.Routes;

import mta.universitate.Model.Database;
import mta.universitate.Model.Document;
import mta.universitate.Model.Grade;
import mta.universitate.Model.Student;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
public class ArchiveStudents {


    private String getStudentFolderName(Student S)
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(S.getName().toLowerCase(Locale.ROOT))
                .append("_")
                .append(S.getSurname().toLowerCase(Locale.ROOT))
                .append("_")
                .append(S.getStudyGroup().getStudy_year().toString());

        return stringBuilder.toString();
    }

    private ArrayList<Student> get4thYearStudents()
    {
        ArrayList<Student> allStudents = Database.getInstance().getAllStudents();
        ArrayList<Student> to_return = new ArrayList<Student>();

        for (Student student : allStudents) {

            if (student.getStudyGroup().getStudy_year() == 4) {
                to_return.add(student);
            }
        }

        return to_return;
    }

    private String getStudentGrades(Student S, ArrayList<Grade> allGrades)
    {
        StringBuilder stringBuilder = new StringBuilder();

        for (Grade grade : allGrades) {
            if (Objects.equals(grade.getStudent().getName(), S.getName()) &&
                    Objects.equals(grade.getStudent().getStudyGroup().getStudy_year(),S.getStudyGroup().getStudy_year()) &&
                    Objects.equals(grade.getStudent().getSurname(),S.getSurname())) {

                stringBuilder.append(grade.getCourse().getName())
                        .append("\t\t").append(grade.getValue())
                        .append("\t\t").append(grade.getDate())
                        .append("\n");
            }
        }

        return stringBuilder.toString();
    }



    @PostMapping(value = "/archive-students", produces = "application/json")
    @ResponseBody
    public String archiveStudents() throws SQLException, IOException {
        ArrayList<Student> fourthYearStudents=get4thYearStudents();
        ArrayList<Grade> allGrades = Database.getInstance().getAllGrades();
        if(fourthYearStudents.size() == 0)
        {
            return "{\"status\" : \"FAILED\"}";
        }

        ArrayList<Document> documents = Database.getInstance().getAllDocuments();


        int year = Calendar.getInstance().get(Calendar.YEAR);
        String zipName=".\\promotia_" + year + ".zip";
        File f = new File(zipName);
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(f));

        for (Student fourthYearStudent : fourthYearStudents) {


            String folderName = getStudentFolderName(fourthYearStudent);
            String grades = getStudentGrades(fourthYearStudent, allGrades);
            String header = "Materie\t\tNota\t\tData\n\n";
            String content = header + grades;


            String zipEntry = folderName + "/grades.txt";
            ZipEntry e = new ZipEntry(zipEntry);
            out.putNextEntry(e);
            byte[] data = content.getBytes();
            out.write(data, 0, data.length);
            out.closeEntry();


            int documentCounter = 1;
            for (Document document : documents) {
                if (fourthYearStudent.getUser().getId() == document.getUser().getId()) {

                    String documentFileName = document.getTitle().toLowerCase(Locale.ROOT);
                    String zipEntryDocument = folderName + "/" + documentFileName + "_" + documentCounter +".txt";

                    ZipEntry d = new ZipEntry(zipEntryDocument);
                    out.putNextEntry(d);
                    byte[] dataDocument = document.getContent().getBytes();
                    out.write(dataDocument, 0, dataDocument.length);
                    out.closeEntry();

                    documentCounter++;
                }
            }

        }
        out.close();
        return "{\"status\" : \"SUCCESS\"}";
    }

}
