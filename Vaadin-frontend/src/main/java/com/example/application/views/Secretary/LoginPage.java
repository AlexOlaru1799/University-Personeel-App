package com.example.application.views.Secretary;     // To do modify for the current project



import com.example.application.views.BackEnd.Database;
import com.example.application.views.Utils.ApiRequest;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Random;

import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;

@PageTitle("list")
@Route(value = "")
public class LoginPage extends VerticalLayout {

    public LoginPage()throws SQLException {
        setSpacing(false);

        Random rand = new Random();
        int upperLimit = 500;
        int int_random = rand.nextInt(upperLimit);

        // Here we will store the cookie
        CookieManager cookieManager = new CookieManager();

        // Create request and set the endpoint
        ApiRequest req = new ApiRequest("http://localhost:8080/login");

        String photo = "https://picsum.photos/seed/" + int_random + "/400";

        Image image = new Image(photo, "Image not loaded");
        add(image);

        add(new H2("Welcome!🤗"));
        add(new Paragraph("This page is dedicated to university students and staff."));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");


        LoginOverlay component = new LoginOverlay();
        H1 title = new H1();
        title.getStyle().set("color", "var(--lumo-base-color)");
        Icon icon = VaadinIcon.ACADEMY_CAP.create();
        icon.setSize("40px");
        icon.getStyle().set("top", "-4px");
        title.add(icon);
        Text titleText = new Text(" UniApp");

        component.setDescription("App for the students and staff of the university");

        title.add(titleText);
        component.setTitle(title);



        component.addLoginListener(e -> {

                String username = e.getUsername();
                String password = e.getPassword();


                req.addParameter("username", username);
                req.addParameter("password", password);


            // Send the request and get the response
            HashMap<String, Object> response = req.send();

            // Get the cookie and store it in the CookieManager
            cookieManager.getCookieStore().add(null, req.getCookie());

            System.out.println(response.get("status"));




//                    if(!res2.next())
//                    {
//                        System.out.println("DB Query Error\n\n");
//                    }
//                    else
//                    {
//                        System.out.println(res2.getString(1));
//                    }
//
//                    if(Objects.equals(res2.getString(1), "300"))
//                    {
//                        String location = "viewStudent";
//                        component.getUI().ifPresent(ui ->ui.navigate(location));
//                    }
//                    else    //TO DO: ADD HERE FOR THE OTHER TYPE OF USERS
//                    {
//                        String location = "main/"+username;
//                        component.getUI().ifPresent(ui ->ui.navigate(location));
//                    }





        });
        Button open = new Button("Open login overlay",
                e -> component.setOpened(true));

        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setAdditionalInformation("To close the login form submit non-empty username and password");
        component.setI18n(i18n);

        add(component, open);



    }






}