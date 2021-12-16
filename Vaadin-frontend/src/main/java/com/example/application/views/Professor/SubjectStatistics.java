package com.example.application.views.Professor;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Subject Statitics")
@Route(value = "subjectStatitics", layout = ProfessorLayout.class)

public class SubjectStatistics extends VerticalLayout {
    private TextField subjectName;
    private Button showSubjects;

    public SubjectStatistics() {

        subjectName = new TextField("Subject Name");
        showSubjects = new Button("Show Statistics");

        setPadding(true);
        setSpacing(true);

        add(new H2("Show students who failed some exams!"));

        VerticalLayout layout = createLayout("Subject Statitics");
        layout.setPadding(true);
        layout.add(subjectName, showSubjects);


        showSubjects.addClickListener(e -> {

            String subject = subjectName.getValue();

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
