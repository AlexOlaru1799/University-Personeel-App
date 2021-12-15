package com.example.application.views.Admin;

import com.example.application.views.Admin.AdminLayout;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Delete Student")
@Route(value = "deleteStudent", layout = AdminLayout.class)
public class DeleteStudent extends VerticalLayout{

    private TextField studentName;
    private TextField studentSurname;
    private Button deleteButton;

    public DeleteStudent() {

        studentName = new TextField("Student Name");
        studentSurname = new TextField("Student Surname");
        deleteButton = new Button("Delete Student");

        setPadding(true);
        setSpacing(true);

        add(new H2("Delete Student by pressing the button"));

        VerticalLayout layout = createLayout("Enter Student name here");
        layout.setPadding(true);
        layout.add(studentName, studentSurname, deleteButton);


        deleteButton.addClickListener(e -> {

            String name = studentSurname.getValue();
            String surname = studentSurname.getValue();

            //Database DB = Database.getInstance();

            //ResultSet res2 = DB.getStudentInfo(ID);

            //ResultSet res3 = DB.getStudentGrades(ID);

            if(name != "" && surname != "")
            {
                Notification.show("Student with name:" + name + surname + " has been deleted");
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
