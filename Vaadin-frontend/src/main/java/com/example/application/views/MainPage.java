package com.example.application.views;


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
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

@PageTitle("Main Page")
@Route(value = "/main")
public class MainPage extends VerticalLayout implements HasUrlParameter<String> {

    String username;
//    public MainPage(String username,String password)throws SQLException {
//
//        add(new Text(username));
//        add(new Text(password));
//
//    }

    public void setParameter(BeforeEvent event,
                             String parameter) {

        this.username = parameter;
        add(new Text(this.username));
    }



}

