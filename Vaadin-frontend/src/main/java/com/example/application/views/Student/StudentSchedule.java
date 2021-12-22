package com.example.application.views.Student;

import com.example.application.views.Utils.ApiRequest;
import com.example.application.views.Utils.OwnCookieManager;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

@PageTitle("Student Schedule")
@Route(value = "studentSchedule", layout = StudentLayout.class)


public class StudentSchedule extends VerticalLayout {
    private final Button showSchedule;

    public StudentSchedule() {
        showSchedule = new Button("Show Schedule");

        DatePicker Date = new DatePicker("Select your date");
        add(Date);

        VerticalLayout layout = createLayout("Please select a day: ");
        layout.setPadding(true);
        layout.add(Date, showSchedule);

        layout = createLayout("Schedule : ");
        layout.setPadding(true);
        layout.setHeight("500px");
        layout.getStyle().set("overflow", "scroll");

        TextArea textArea = new TextArea();
        textArea.setWidth("500px");
        textArea.isReadOnly();
        textArea.setHeight("500px");
        layout.add(textArea);
        showSchedule.addClickListener(e -> {

            DatePicker data1 = Date;

            String initDate = Date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            if (initDate != "") {
                // Create request and set the endpoint
                ApiRequest req = new ApiRequest("http://localhost:8080/student/studentSchedule");
                req.addCookie(OwnCookieManager.getInstance().getCookie());

                req.addParameter("date", initDate);

                HashMap<String, Object> response = req.send();

                ArrayList<Object> objResp = new ArrayList<Object>(response.values());

                ArrayList<Object> objClasses = (ArrayList<Object>) objResp.get(0);

                System.out.println(objClasses);

                ArrayList<String> date = new ArrayList<String>(objClasses.size());
                ArrayList<String> courseName = new ArrayList<String>(objClasses.size());
                ArrayList<String> classroom = new ArrayList<String>(objClasses.size());

                for (int i = 0; i < objClasses.size(); i++) {
                    String[] set = null;
                    String c = objClasses.get(i).toString();
                    set = c.split("=");
                    String ora = set[3].split(",")[0];
                    String materie = set[14].split(",")[0];
                    String clasa = set[9].split(",")[0];
                    set = set[1].split(",");
                    date.add(ora);
                    courseName.add(materie);
                    classroom.add(clasa);

                    System.out.println(ora);
                    System.out.println(materie);
                    System.out.println(clasa);
                }

                String scheduleFinal = "";
                for (int i = 0; i < date.size(); i++) {
                    scheduleFinal += date.get(i);
                    scheduleFinal += "\t";
                    scheduleFinal += courseName.get(i);
                    scheduleFinal += "\t";
                    scheduleFinal += classroom.get(i);
                    scheduleFinal += "\n";
                }

                String print = "You don't have courses today! :)";

                if (response.get("status").equals("SUCCESS")) {
                    if (objClasses.toString() == "[]") {
                        textArea.setValue(print);
                    } else if (response.get("result") != null) {
                        textArea.setValue(scheduleFinal);
                    }
                    Notification.show("Print succes!");

                } else {
                    Notification.show("Failed :(");
                }
            } else {
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

