package com.example.application.views;

import com.example.application.views.BackEnd.Database;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.ObjectUtils;

import java.sql.ResultSet;
import java.sql.SQLException;

@PageTitle("Archive Student")
@Route(value = "archiveStudent", layout = AdminLayout.class)
public class ArchiveStudent extends VerticalLayout{

    private TextField studentName;
    private Button archiveButton;

    public ArchiveStudent() {

        studentName = new TextField("Student Name");
        archiveButton = new Button("Archive Student");

        setPadding(true);
        setSpacing(true);

        add(new H2("Archive Student by pressing the button"));

        VerticalLayout layout = createLayout("Enter student name here");
        layout.setPadding(true);
        layout.add(studentName, archiveButton);


        archiveButton.addClickListener(e -> {

            String nameSurname = studentName.getValue();

            //Database DB = Database.getInstance();

            //ResultSet res2 = DB.getStudentInfo(ID);

            //ResultSet res3 = DB.getStudentGrades(ID);

            if(nameSurname != "")
            {
                Notification.show("Student with name:" + nameSurname + " has an archive now");
            }
            else
            {
                Notification.show("You need to select a name!");
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
