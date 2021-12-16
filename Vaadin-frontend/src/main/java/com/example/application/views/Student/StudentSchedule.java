package com.example.application.views.Student;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Student Schedule")
@Route(value = "studentScheduele", layout = StudentLayout.class)


public class StudentSchedule extends VerticalLayout {
    private TextField studentName;
    private Button showSchedule;

    public StudentSchedule() {
        studentName = new TextField("Student Name");
        showSchedule = new Button("Show Schedule");


        DatePicker initialDate = new DatePicker("Start date");
        add(initialDate);

        DatePicker finalDate = new DatePicker("Final date");
        add(finalDate);

        VerticalLayout layout = createLayout("Show Student Schedule");
        layout.setPadding(true);
        layout.add(studentName, initialDate, finalDate, showSchedule);
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

