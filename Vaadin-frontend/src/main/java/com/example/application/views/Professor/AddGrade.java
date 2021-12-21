package com.example.application.views.Professor;

import com.example.application.views.Utils.ApiRequest;
import com.example.application.views.Utils.OwnCookieManager;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

@PageTitle("Add Grade")
@Route(value = "addGrade", layout = ProfessorLayout.class)

public class AddGrade extends VerticalLayout {
    private TextField studentName;
    private TextField studentSurname;
    private TextField courseName;
    private IntegerField gradeToAdd;
    private Button addGrade;

    public AddGrade()
    {
        studentName = new TextField("Student Name");
        studentSurname = new TextField("Student Surname");
        gradeToAdd = new IntegerField("Add Grade");
        addGrade = new Button("Add Grade");

        // Create request and set the endpoint
        ApiRequest getCourse = new ApiRequest("http://localhost:8080/admin/get-courses");
        getCourse.addCookie(OwnCookieManager.getInstance().getCookie());

        HashMap<String, Object> courses = getCourse.send();

        ArrayList<Object> objResp = new ArrayList<Object>(courses.values());

        ArrayList<Object> objClasses = (ArrayList<Object>)objResp.get(0);

        ArrayList<String> coursesName = new ArrayList<String>(objClasses.size());


        for (int i = 0; i < objClasses.size(); i++) {
            String[] set = null;
            String c = objClasses.get(i).toString();
            set = c.split("=");
            set = set[1].split(",");
            coursesName.add(set[0]);
        }

        Select<String> courseName = new Select<String>();
        courseName.setLabel("Select Subject");
        courseName.setItems(coursesName);
        courseName.setValue(coursesName.get(0));

        setPadding(true);
        setSpacing(true);

        add(new H2("Add grade for a student"));

        DatePicker gradeDate = new DatePicker("Grade Date");
        add(gradeDate);

        VerticalLayout layout = createLayout("Grade Add");
        layout.setPadding(true);
        layout.add(studentName, studentSurname, courseName, gradeToAdd, gradeDate, addGrade);

        addGrade.addClickListener(e -> {

            // Create request and set the endpoint

            ApiRequest req = new ApiRequest("http://localhost:8080/professor/add-grade");
            req.addCookie(OwnCookieManager.getInstance().getCookie());

            String name = studentName.getValue();
            String surname = studentSurname.getValue();
            String course = courseName.getValue();
            DatePicker dataGrade = gradeDate;
            Integer grade = gradeToAdd.getValue();

            String date = dataGrade.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            System.out.println(date.toString());
            System.out.println(name.toString());
            System.out.println(surname.toString());
            System.out.println(course.toString());
            System.out.println(grade.toString());

            if(name != "" && surname != "" && course != "" && date != "" && grade != null)
            {
                req.addParameter("name", name);
                req.addParameter("surname", surname);
                req.addParameter("grade", String.valueOf(grade));
                req.addParameter("course", course);
                req.addParameter("date", date);


                // Send the request and get the response
                HashMap<String, Object> response = req.send();

                System.out.println(response);

                if(response.get("status").equals("SUCCESS")) {
                    Notification.show("Grade: " + grade + "on date: " + date + "for student: " + name +
                            surname + "at course: " + course + " has been added");
                }
                else {
                    Notification.show("Failed :(");
                }
            }
            else
            {
                Notification.show("You need to complete all the fields!");
            }
        });
    }

    private VerticalLayout createLayout(String caption) {
        VerticalLayout hl = new VerticalLayout();
        hl.setWidth("600px");
        hl.getStyle().set("background-color", "#e8ebef");
        add(new H4(caption));
        add(hl);
        add(new Html("<span>&nbsp;</span>")); // spacer
        return hl;
    }
}
