package com.example.application.views.Secretary;



import com.example.application.views.Utils.ApiRequest;
import com.example.application.views.Utils.OwnCookieManager;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.net.CookieManager;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;

@PageTitle("Create Professor Account")
@Route(value = "createTeacherAccount", layout = MainLayout.class)
public class CreateTeacherAccount extends VerticalLayout {

    public CreateTeacherAccount() {

        VerticalLayout mainLayout = createLayout("Insert the necessary info");



        // Create request and set the endpoint


        //CookieManager cookieManager = new CookieManager();






        TextField name = new TextField("Professor name");
        name.setWidth("500px");
        TextField surname = new TextField("Professor surname");
        surname.setWidth("500px");
        PasswordField password = new PasswordField("Professor password");
        surname.setWidth("500px");

        Select<String> role = new Select<String>();
        role.setLabel("Employee Role");
        role.setItems("Administrator", "Secretary", "Professor", "Mentor","Janitor");
        role.setValue("Professor");

        IntegerField salary = new IntegerField("Professor salary");
        salary.setWidth("500px");

        Button addStudent = new Button("Add Professor");
        addStudent.setWidth("500px");

        mainLayout.add(name,surname,password,role,salary,addStudent);
        add(mainLayout);


        addStudent.addClickListener(e -> {
            String nameS = name.getValue();
            String surnameS = surname.getValue();
            int salaryS = salary.getValue();
            String passS = password.getValue();
            String positionS = role.getValue();

            ApiRequest req = new ApiRequest("http://localhost:8080/secretary/create-professor");

            req.addParameter("name",nameS);
            req.addParameter("surname",surnameS);
            req.addParameter("position",positionS);
            req.addParameter("password",passS);
            req.addParameter("salary",String.valueOf(salaryS));

            req.addCookie(OwnCookieManager.getInstance().getCookie());


            // Send the request and get the response
            HashMap<String, Object> response = req.send();

            if(response.get("status").equals("SUCCESS")) {

                Notification.show("Employee with name: " + nameS + " " + surnameS + " has been added");
            }
            else{
                Notification.show("Failed :(");
            }


            name.clear();
            surname.clear();
            salary.clear();
            role.clear();
            password.clear();

            //Notification.show("Professor was added to the database!");

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
