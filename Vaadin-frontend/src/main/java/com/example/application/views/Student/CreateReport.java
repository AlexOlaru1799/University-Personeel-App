package com.example.application.views.Student;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Create Report")
@Route(value = "createReport", layout = StudentLayout.class)


public class CreateReport extends VerticalLayout {
    private TextField studentName;
    private TextField studentFirstname;
    private TextField reportType;
    private Button generateReport;
    public CreateReport(){
        studentName = new TextField("Student Name");
        studentFirstname = new TextField("Student First name");
        reportType=new TextField("Report Type");
        generateReport = new Button("Generate Report ");

        VerticalLayout layout = createLayout("Please complete the request: ");
        layout.setPadding(true);
        layout.add(studentName, studentFirstname,reportType,generateReport);

        layout = createLayout("Report: ");
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

