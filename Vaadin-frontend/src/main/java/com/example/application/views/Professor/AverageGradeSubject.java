package com.example.application.views.Professor;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Professor Schedule")
@Route(value = "averageGroup", layout = ProfessorLayout.class)

public class AverageGradeSubject extends VerticalLayout {
    private TextField subjectName;
    private Button showSubjects;

    public AverageGradeSubject() {

        subjectName = new TextField("Subject Name");
        showSubjects = new Button("Show Subject Average");

        setPadding(true);
        setSpacing(true);

        add(new H2("Show subject statistics!"));

        VerticalLayout layout = createLayout("Subject Statistics");
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
