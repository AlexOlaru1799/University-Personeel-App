package com.example.application.views.Professor;

import com.example.application.views.Utils.ApiRequest;
import com.example.application.views.Utils.OwnCookieManager;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

@PageTitle("Professor Schedule")
@Route(value = "professorShedule", layout = ProfessorLayout.class)

public class ProfessorSchedule extends VerticalLayout {


    private Button showSchedule;

    public ProfessorSchedule() {
        showSchedule = new Button("Show Schedule");

        setPadding(true);
        setSpacing(true);

        add(new H2("Show professor schedule. What days you want?"));

        DatePicker initialDate = new DatePicker("Start date");
        add(initialDate);



        VerticalLayout layout = createLayout("Professor Schedule");
        layout.setPadding(true);
        layout.add(initialDate,showSchedule);

        setSpacing(true);
        setPadding(true);

        TextArea textArea = new TextArea();
        textArea.setLabel("Your scheduler: ");
        textArea.setWidth("500px");
        textArea.isReadOnly();
        textArea.setHeight("500px");
        layout.add(textArea);

        showSchedule.addClickListener(e -> {

            DatePicker data1 = initialDate;

            String initDate = initialDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            //String initDate = "2021-10-04";

            if(initDate != "")
            {
                // Create request and set the endpoint
                ApiRequest req = new ApiRequest("http://localhost:8080/professor/view-schedule");
                req.addCookie(OwnCookieManager.getInstance().getCookie());

                req.addParameter("date", initDate);

                HashMap<String, Object> response = req.send();

                ArrayList<Object> objResp = new ArrayList<Object>(response.values());

                ArrayList<Object> objClasses = (ArrayList<Object>)objResp.get(0);

                System.out.println(objClasses);

                ArrayList<String> name = new ArrayList<String>(objClasses.size());
                ArrayList<String> surname = new ArrayList<String>(objClasses.size());
                ArrayList<String> courseName = new ArrayList<String>(objClasses.size());
                ArrayList<String> studyGroup = new ArrayList<String>(objClasses.size());
                ArrayList<String> classroom = new ArrayList<String>(objClasses.size());

                for (int i = 0; i < objClasses.size(); i++) {
                    String[] set = null;
                    String c = objClasses.get(i).toString();
                    set = c.split("=");
                    String nume = set[17].split(",")[0];
                    String prenume = set[18].split("}},")[0];
                    String materie = set[14].split(",")[0];
                    String grupa = set[5].split(",")[0];
                    String clasa = set[9].split(",")[0];
                    set = set[1].split(",");
                    name.add(nume);
                    surname.add(prenume);
                    courseName.add(materie);
                    studyGroup.add(grupa);
                    classroom.add(clasa);

                    System.out.println(nume);
                    System.out.println(prenume);
                    System.out.println(materie);
                    System.out.println(grupa);
                    System.out.println(clasa);
                }

                String scheduleFinal = "";
                for(int i = 0;i < name.size(); i++)
                {
                    scheduleFinal += name.get(i);
                    scheduleFinal += "\t";
                    scheduleFinal += surname.get(i);
                    scheduleFinal += "\t";
                    scheduleFinal += studyGroup.get(i);
                    scheduleFinal += "\t";
                    scheduleFinal += courseName.get(i);
                    scheduleFinal += "\t";
                    scheduleFinal += classroom.get(i);
                    scheduleFinal += "\n";
                }

                String print = "You don't have courses today! :)";

                if(response.get("status").equals("SUCCESS"))
                {
                    if(objClasses.toString() == "[]" ){
                        textArea.setValue(print);
                    }
                    else if(response.get("result") != null ) {
                        textArea.setValue(scheduleFinal);
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


