package mta.universitate.Routes;

import mta.universitate.Model.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class StudentsStatistics {
    Database db = Database.getInstance();

    @RequestMapping("/myTest")
    public String test() throws SQLException {
//        Student student=new Student();
//        student.setId(5);
//        student=db.get(student);
//
//
//        Grade grade=new Grade();
//        grade.setId(3000);
//        grade=db.get(grade);
//
//        Course course=new Course();
//        course.setId(50);
//        course=db.get(course);
//
//        Feature feature=new Feature();
//        feature.setId(23);
//        feature=db.get(feature);
//
//        Request request=new Request();
//        request.setId(2001);
//        request=db.get(request);
//
//        Document document=new Document();
//        document.setId(1);
//        document=db.get(document);
//
//        RequestType requestType=new RequestType();
//        requestType.setId(2);
//        requestType=db.get(requestType);
//
//        Module module=new Module();
//        module.setId(3);
//        module=db.get(module);
//
        Schedule schedule=new Schedule();
        schedule.setId(2015);
        schedule=db.get(schedule);




        return String.format("<h1>%s<h1>", schedule.getModule().getProfessor().getSalary());
    }

}
