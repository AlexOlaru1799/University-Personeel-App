package com.example.application.views.Professor;

import com.example.application.views.Utils.ApiRequest;
import com.example.application.views.Utils.OwnCookieManager;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Subject Failed")
@Route(value = "subjectFailed", layout = ProfessorLayout.class)

public class SubjectFailed extends VerticalLayout{
    private Button showSubjects;

    public SubjectFailed() {

        showSubjects = new Button("Show Failed Subject");

        setPadding(true);
        setSpacing(true);

        add(new H2("Show students who failed at a single subject!"));

        VerticalLayout layout = createLayout("Subject failed");
        layout.setPadding(true);
        layout.add(showSubjects);


        showSubjects.addClickListener(e -> {



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
