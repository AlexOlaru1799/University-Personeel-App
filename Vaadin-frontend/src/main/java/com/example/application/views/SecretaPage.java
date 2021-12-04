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
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.stream.Stream;

@PageTitle("Main Page")
@Route(value = "/secretar")
public class SecretaPage extends VerticalLayout implements HasUrlParameter<String> {


    private H1 viewTitle;

    public void setParameter(BeforeEvent event,
                             String parameter) {


        add(new Text("Hello secrater,"+parameter+"!"));

        Tabs tabs = new Tabs();

        Tab tib = new Tab(new RouterLink("Tib", Tab.class));
        Tab tab = new Tab(new RouterLink("Tab", Tab.class));
        Tab tub = new Tab(new RouterLink("Tub", Tab.class));

        tabs.add(tib, tab, tub);
        add(tabs);
    }
}