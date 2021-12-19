package com.example.application.views.Admin;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;

@PageTitle("Reset Account Password")
@Route(value = "resetAccountPassword", layout = AdminLayout.class)
public class ResetAccountPassword extends VerticalLayout{

    private TextField username;
    private PasswordField newPassword;
    private Button resPassword;

    public ResetAccountPassword() {

        username = new TextField("Username");
        newPassword = new PasswordField("Type new password");
        resPassword = new Button("Reset Password");

        setPadding(true);
        setSpacing(true);

        add(new H2("Reset the password by pressing the button"));

        VerticalLayout layout = createLayout("Reset Password");
        layout.setPadding(true);
        layout.add(username, newPassword , resPassword);


        resPassword.addClickListener(e -> {

            String user = username.getValue();
            String pass = newPassword.getValue();

            //Database DB = Database.getInstance();

            //ResultSet res2 = DB.getStudentInfo(ID);

            //ResultSet res3 = DB.getStudentGrades(ID);

            if(user != "" && pass!= "")
            {
                Notification.show("Username:" + user + " credentials has been updated");

            }
            else
            {
                Notification.show("You need to complete all the fields!");
            }
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