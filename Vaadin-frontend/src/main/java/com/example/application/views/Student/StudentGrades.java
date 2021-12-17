package com.example.application.views.Student;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Student Grades")
@Route(value = "studentGrades", layout = StudentLayout.class)


public class StudentGrades extends VerticalLayout {
    private TextField studentName;
    private TextField studentFirstname;
    private Button showGrades;
    public StudentGrades(){
        studentName = new TextField("Student Name");
        studentFirstname = new TextField("Student First name");
        showGrades = new Button("Show ");

        VerticalLayout layout = createLayout("Enter student's name and first name: ");
        layout.setPadding(true);
        layout.add(studentName, studentFirstname,showGrades);

        layout = createLayout("Grades : ");
        layout.setPadding(true);
        layout.setHeight("500px");
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

