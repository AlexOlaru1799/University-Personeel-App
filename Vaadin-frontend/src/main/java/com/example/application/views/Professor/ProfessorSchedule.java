package com.example.application.views.Professor;

import com.microsoft.windowsazure.core.utils.DateFactory;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.time.LocalDate;


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
        add(initialDate);


        VerticalLayout layout = createLayout("Professor Schedule");
        layout.setPadding(true);
        layout.add(professorName, initialDate, finalDate, showSchedule);


        showSchedule.addClickListener(e -> {

            String professor = professorName.getValue();
            DatePicker date1 = initialDate;
            DatePicker date2 = finalDate;

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


