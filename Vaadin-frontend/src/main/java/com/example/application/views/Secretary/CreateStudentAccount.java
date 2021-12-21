package com.example.application.views.Secretary;



import com.example.application.views.Utils.ApiRequest;
import com.example.application.views.Utils.OwnCookieManager;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;

@PageTitle("Create Student Account")
@Route(value = "createStudentAccount", layout = MainLayout.class)
public class CreateStudentAccount extends VerticalLayout {

    public CreateStudentAccount() {

        VerticalLayout mainLayout = createLayout("Insert the necessary info");

        TextField name = new TextField("Student name");
        name.setWidth("500px");
        TextField surname = new TextField("Student surname");
        surname.setWidth("500px");
        PasswordField password = new PasswordField("Student password");
        password.setWidth("500px");
        Select<String> major = new Select<String>();
        major.setLabel("Student Major");
        major.setItems("Calculatoare", "Cyber", "ESCA", "BAT","AEV");
        major.setValue("Calculatoare");
        major.setWidth("500px");

        TextField study_group = new TextField("Student Study Group");
        study_group.setWidth("500px");

        IntegerField solda = new IntegerField("Income");
        solda.setWidth("500px");

        Button addStudent = new Button("Add Student");
        addStudent.setWidth("500px");

        mainLayout.add(name,surname,password,major,study_group,solda,addStudent);
        add(mainLayout);


        addStudent.addClickListener(e -> {
            String nameS = name.getValue();
            String surnameS = surname.getValue();
            String passS = password.getValue();
            String study_groupS = study_group.getValue();
            String majorS = major.getValue();
            int soldaS=solda.getValue();



            ApiRequest req = new ApiRequest("http://localhost:8080/secretary/create-student");

            req.addCookie(OwnCookieManager.getInstance().getCookie());

            req.addParameter("name",nameS);
            req.addParameter("surname",surnameS);
            req.addParameter("password",passS);
            req.addParameter("major",majorS);
            req.addParameter("study_group",study_groupS);
            req.addParameter("income",String.valueOf(soldaS));




            // Send the request and get the response
            HashMap<String, Object> response = req.send();

            if(response.get("status").equals("SUCCESS")) {

                Notification.show("Student with name: " + nameS + " " + surnameS + " has been added");
            }
            else{
                Notification.show("Failed :(");
            }


            name.clear();
            surname.clear();
            password.clear();
            study_group.clear();
            major.clear();
            solda.clear();

            //Notification.show("Student was added to the database!");

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
