package com.example.application.views.Secretary;



import com.example.application.views.BackEnd.*;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@PageTitle("Create Professor Account")
@Route(value = "createTeacherAccount", layout = MainLayout.class)
public class CreateTeacherAccount extends VerticalLayout {

    public CreateTeacherAccount() {

        VerticalLayout mainLayout = createLayout("Insert the necessary info");

        TextField name = new TextField("Professor name");
        name.setWidth("500px");
        TextField surname = new TextField("Professor surname");
        surname.setWidth("500px");
        IntegerField salary = new IntegerField("Professor salary");
        salary.setWidth("500px");

        Button addStudent = new Button("Add Professor");
        addStudent.setWidth("500px");

        mainLayout.add(name,surname,salary,addStudent);
        add(mainLayout);


        addStudent.addClickListener(e -> {
            String nameS = name.getValue();
            String surnameS = surname.getValue();
            int salaryS = salary.getValue();

            Professor professor = new Professor(nameS,surnameS);
            professor.setSalary(salaryS);





            Database DB = Database.getInstance();

            try {
                DB.createTeacher(professor);
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }


            name.clear();
            surname.clear();
            salary.clear();

            Notification.show("Professor was added to the database!");

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
