package com.example.application.views.Professor;

import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.JsonObject;
import com.microsoft.windowsazure.core.utils.DateFactory;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@PageTitle("Professor Schedule")
@Route(value = "professorShedule", layout = ProfessorLayout.class)

public class ProfessorSchedule extends VerticalLayout {


    private TextField professorName;
    private Button showSchedule;

    public ProfessorSchedule() {
        professorName = new TextField("Professor Name");
        showSchedule = new Button("Show Schedule");

        setPadding(true);
        setSpacing(true);

        add(new H2("Show professor schedule. What days you want?"));

        DatePicker initialDate = new DatePicker("Start date");
        add(initialDate);

        DatePicker finalDate = new DatePicker("Final date");
        add(finalDate);

        TimePicker initialTimeModule = new TimePicker();
        initialTimeModule.setLabel("Start hour module: ");
        add(initialTimeModule);

        TimePicker finalTimeModule = new TimePicker();
        finalTimeModule.setLabel("Final hour module: ");
        add(finalTimeModule);


        VerticalLayout layout = createLayout("Professor Schedule");
        layout.setPadding(true);
        layout.add(professorName, initialDate, finalDate, initialTimeModule, finalTimeModule, showSchedule);

        setSpacing(true);
        setPadding(true);

        H5 subject = new H5("");
        H5 date = new H5("");
        H5 hour = new H5("");

        showSchedule.addClickListener(e -> {

            String professor = professorName.getValue();
            DatePicker date1 = initialDate;
            DatePicker date2 = finalDate;
            TimePicker time1 = initialTimeModule;
            TimePicker time2 = finalTimeModule;


            String initialDateFromUser = initialDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String finalDateFromUser = finalDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String initialHourFromUser = initialTimeModule.getValue().format(DateTimeFormatter.ofPattern("hh:mm:ss"));
            String finalHourFromUser = finalTimeModule.getValue().format(DateTimeFormatter.ofPattern("hh:mm:ss"));

            StringBuilder sendInitialDateTime = new StringBuilder().append(initialDateFromUser).append(" ").append(initialHourFromUser);
            StringBuilder sendFinalDateTime = new StringBuilder().append(finalDateFromUser).append(" ").append(finalHourFromUser);


            System.out.println(initialDateFromUser.toString());
            System.out.println(finalDateFromUser.toString());
            System.out.println(initialHourFromUser.toString());
            System.out.println(finalHourFromUser.toString());

            System.out.println(sendInitialDateTime.toString());
            System.out.println(sendFinalDateTime.toString());



            //Database DB = Database.getInstance();

            //ResultSet res2 = DB.getStudentInfo(ID);

            //ResultSet res3 = DB.getStudentGrades(ID);

        });


        layout = createLayout("Professor Schedule: ");
        layout.setPadding(true);
        layout.setHeight("300px");
        layout.add(subject, date, hour);
        layout.getStyle().set("overflow", "scroll");// enable scrolling when content doesn't fit
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


