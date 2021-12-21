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
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.HashMap;

@PageTitle("Subject Statitics")
@Route(value = "subjectStatitics", layout = ProfessorLayout.class)

public class SubjectStatistics extends VerticalLayout {

    private Button showSubjects;

    public SubjectStatistics() {

        showSubjects = new Button("Show Statistics");

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

        Select<String> subject = new Select<String>();
        subject.setLabel("Select Subject");
        subject.setItems(coursesName);
        subject.setValue(coursesName.get(0));


        setPadding(true);
        setSpacing(true);

        VerticalLayout layout = createLayout("Show number of students who failed on a subject");
        layout.setPadding(true);
        layout.add(subject, showSubjects);

        TextArea textArea = new TextArea();
        textArea.setLabel("Number of outstanding students:");
        textArea.setWidth("250px");
        textArea.isReadOnly();
        textArea.setHeight("65px");
        layout.add(textArea);

        showSubjects.addClickListener(e -> {

            String name = subject.getValue();

            if(name != "")
            {
                // Create request and set the endpoint
                ApiRequest req = new ApiRequest("http://localhost:8080/professor/nrStudFailedExam");
                req.addCookie(OwnCookieManager.getInstance().getCookie());

                req.addParameter("name", name);

                HashMap<String, Object> response = req.send();

                if(response.get("status").equals("SUCCESS"))
                {
                    if(response.get("result") != null ) {
                        textArea.setValue(response.get("result").toString());
                    }
                    Notification.show("Print succes!");
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
