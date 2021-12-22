package com.example.application.views.Student;

import com.example.application.views.Utils.ApiRequest;
import com.example.application.views.Utils.OwnCookieManager;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.HashMap;

@PageTitle("Student Grades")
@Route(value = "studentGrades", layout = StudentLayout.class)


public class StudentGrades extends VerticalLayout {
    private TextField courseName;

    private Button showGrades;
    public StudentGrades(){
        courseName = new TextField("Course Name");
        showGrades = new Button("Show ");

        VerticalLayout layout = createLayout("Enter the course name: ");
        layout.setPadding(true);
        layout.add(courseName,showGrades);


        TextArea textArea = new TextArea();
        textArea.isReadOnly();
        layout.add(textArea);

        showGrades.addClickListener(e -> {

            String course = courseName.getValue();


            if(course != "")
            {
                // Create request and set the endpoint
                ApiRequest req = new ApiRequest("http://localhost:8080/student/gradesForStudent");
                req.addCookie(OwnCookieManager.getInstance().getCookie());

                req.addParameter("courseName", course);


                HashMap<String, Object> response = req.send();

                System.out.println(response);
                if(!response.get("status").equals("SUCCESS"))
                {
                    Notification.show("No grades yet!");
                }
                else {

                    ArrayList<Object> objResp = new ArrayList<Object>(response.values());

                    ArrayList<Object> objClasses = (ArrayList<Object>) objResp.get(0);

                    System.out.println(objClasses);


                    String nota = "";
                    for (int i = 0; i < objClasses.size(); i++) {
                        String[] set = null;
                        String c = objClasses.get(i).toString();
                        set = c.split("=");
                        nota += set[2].split(",")[0];
                        set = set[1].split(",");

                    }


                    if (response.get("status").equals("SUCCESS")) {

                        if (response.get("result") != null) {
                            textArea.setValue(nota);
                        }

                    }
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

