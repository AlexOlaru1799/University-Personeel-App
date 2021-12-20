package com.example.application.views.Professor;

import com.example.application.views.Utils.ApiRequest;
import com.example.application.views.Utils.OwnCookieManager;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.HashMap;

@PageTitle("Average Grade")
@Route(value = "averageGroup", layout = ProfessorLayout.class)

public class AverageGradeSubject extends VerticalLayout {
    private TextField subjectName;
    private Button showSubjects;

    public AverageGradeSubject() {

        subjectName = new TextField("Subject Name");
        showSubjects = new Button("Show Subject Average");


        setPadding(true);
        setSpacing(true);

        add(new H2("Show subject average!"));

        VerticalLayout layout = createLayout("Subject Statistics");
        layout.setPadding(true);
        layout.add(subjectName, showSubjects);


        showSubjects.addClickListener(e -> {

            String name = subjectName.getValue();

            if(name != "")
            {
                // Create request and set the endpoint
                ApiRequest req = new ApiRequest("http://localhost:8080/professor/averageGrade");
                req.addCookie(OwnCookieManager.getInstance().getCookie());

                req.addParameter("name", name);

                HashMap<String, Object> response = req.send();

                if(response.get("status").equals("SUCCESS"))
                {
                    if(response.get("result") != null ) {
                        System.out.println(response.get("result"));
                    }
                    Notification.show("Classroom has been updated");
                }
                else
                {
                    Notification.show("Failed :(");
                }
            }
            else
            {
                Notification.show("You need to complete all the fields!");
            }
            //Database DB = Database.getInstance();

            //ResultSet res2 = DB.getStudentInfo(ID);

            //ResultSet res3 = DB.getStudentGrades(ID);

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
