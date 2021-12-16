package com.example.application.views.Professor;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Professor Grade")
@Route(value = "professorGrade", layout = ProfessorLayout.class)

public class GradesProfessorStudyGroup extends VerticalLayout {
    private TextField studyGroupName;
    private Button showGroupAverage;

    public GradesProfessorStudyGroup() {

        studyGroupName = new TextField("Group Name");
        showGroupAverage = new Button("Show Average");

        setPadding(true);
        setSpacing(true);

        add(new H2("Show group average!"));

        VerticalLayout layout = createLayout("Group Average");
        layout.setPadding(true);
        layout.add(studyGroupName, showGroupAverage);


        showGroupAverage.addClickListener(e -> {

            String group = studyGroupName.getValue();

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


