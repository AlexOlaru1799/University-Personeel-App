package com.example.application.views;     // To do modify for the current project



import com.example.application.views.BackEnd.Database;
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

@PageTitle("list")
@Route(value = "")
public class LoginPage extends VerticalLayout {

    public LoginPage()throws SQLException {
        setSpacing(false);

        Random rand = new Random();
        int upperLimit = 500;
        int int_random = rand.nextInt(upperLimit);



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
            try
            {
                String username = e.getUsername();
                String password = e.getPassword();



                Database DB = Database.getInstance();

                String hashedPass =DB.getHash(password);

                ResultSet res  = DB.executeQuery(" SELECT CASE WHEN EXISTS (" +
                        "    SELECT *" +
                        "    FROM [dbo].[utilizatori]" +
                        "    WHERE [Username] = '" +
                        username +
                        "' and [Password] = '" +
                        hashedPass +
                        "'" +
                        ")" +
                        "THEN CAST(1 AS BIT)" +
                        "ELSE CAST(0 AS BIT) END");

                if(!res.next())
                {
                    System.out.println("DB Query Error\n\n");
                }
                else
                {
                    System.out.println(res.getString(1));
                }

                if (Objects.equals(res.getString(1), "1")) {

                    ResultSet res2  = DB.executeQuery(" SELECT [FK_TipCont]" +
                            "  FROM [dbo].[utilizatori]" +
                            "  WHERE [dbo].[utilizatori].[Username] = '" +
                            username +
                            "'");

                    if(!res2.next())
                    {
                        System.out.println("DB Query Error\n\n");
                    }
                    else
                    {
                        System.out.println(res2.getString(1));
                    }

                    if(Objects.equals(res2.getString(1), "300"))
                    {
                        String location = "viewStudent";
                        component.getUI().ifPresent(ui ->ui.navigate(location));
                    }
                    else    //TO DO: ADD HERE FOR THE OTHER TYPE OF USERS
                    {
                        String location = "main/"+username;
                        component.getUI().ifPresent(ui ->ui.navigate(location));
                    }




                    component.close();
                } else {
                    component.setError(true);

                }
            }
            catch (SQLException | NoSuchAlgorithmException throwables) {
                throwables.printStackTrace();
            }
        });
        Button open = new Button("Open login overlay",
                e -> component.setOpened(true));

        LoginI18n i18n = LoginI18n.createDefault();
        i18n.setAdditionalInformation("To close the login form submit non-empty username and password");
        component.setI18n(i18n);

        add(component, open);



    }






}