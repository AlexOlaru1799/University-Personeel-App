package com.example.application.views.Secretary;


import com.example.application.views.BackEnd.Database;
import com.example.application.views.BackEnd.Student;
import com.example.application.views.BackEnd.StudyGroup;
import com.example.application.views.BackEnd.StudyYear;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@PageTitle("Create Student Account")
@Route(value = "createStudentAccount", layout = MainLayout.class)
public class CreateStudentAccount extends VerticalLayout {

    public CreateStudentAccount() {

        VerticalLayout mainLayout = createLayout("Insert the necessary info");

        TextField name = new TextField("Student name");
        name.setWidth("500px");
        TextField surname = new TextField("Student surname");
        surname.setWidth("500px");
        IntegerField idGrupa = new IntegerField("Group ID");
        idGrupa.setWidth("500px");
        IntegerField year = new IntegerField("Study Year");
        year.setWidth("500px");
        IntegerField solda = new IntegerField("Solda");
        solda.setWidth("500px");
        IntegerField spec = new IntegerField("Specialization ID");
        spec.setWidth("500px");
        Button addStudent = new Button("Add Student");
        addStudent.setWidth("500px");

        mainLayout.add(name,surname,idGrupa,year,solda,spec,addStudent);
        add(mainLayout);


        addStudent.addClickListener(e -> {
            String nameS = name.getValue();
            String surnameS = surname.getValue();
            int idS = idGrupa.getValue();
            int yearS=year.getValue();
            int soldaS=solda.getValue();
            int specS = spec.getValue();


            StudyYear studyYear = new StudyYear(yearS);
            StudyGroup studyGroup = new StudyGroup(idS);

            Student student = new Student(nameS,surnameS,studyGroup,studyYear,soldaS,specS);

            Database DB = Database.getInstance();

            try {
                DB.createStudent(student);
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }


            name.clear();
            surname.clear();
            idGrupa.clear();
            year.clear();
            spec.clear();
            solda.clear();

            Notification.show("Student was added to the database!");

        });
    }

    private VerticalLayout createLayout(String caption) {
        VerticalLayout hl = new VerticalLayout();
        hl.setWidth("600px");
        hl.getStyle().set("background-color", "#e8ebef");
        //add(new H1(caption));
        hl.add(new H3(caption));

        add(hl);
        add(new Html("<span>&nbsp;</span>")); // spacer
        return hl;
    }

}
