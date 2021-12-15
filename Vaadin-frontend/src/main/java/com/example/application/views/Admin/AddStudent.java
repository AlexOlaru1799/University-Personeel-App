package com.example.application.views.Admin;

import com.example.application.views.Admin.AdminLayout;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Add Student")
@Route(value = "addStudent", layout = AdminLayout.class)
public class AddStudent extends VerticalLayout{

    private TextField studentName;
    private TextField studentSurname;
    private PasswordField password;
    private TextField major;
    private TextField studyGroup;
    private IntegerField income;
    private Button addStudent;

    public AddStudent() {

        studentName = new TextField("Student Name");
        studentSurname = new TextField("Student Surname");
        password = new PasswordField("Student Password");
        major = new TextField("Student Major");
        studyGroup = new TextField("Student Study Group");
        income = new IntegerField("Student Income");
        addStudent = new Button("Add Student");

        Select<String> majorSelect = new Select<String>();
        majorSelect.setLabel("Student Major");
        majorSelect.setItems("Nj", "Nj", "Nj", "Nj", "Nj");
        majorSelect.setValue("Nj");

        Select<String> group = new Select<String>();
        group.setLabel("Student Study Group");
        group.setItems("Nj", "Nj", "Nj", "Nj", "Nj");
        group.setValue("Nj");

        setPadding(true);
        setSpacing(true);

        add(new H2("Add Student by pressing the button"));

        VerticalLayout layout = createLayout("Insert Student data here");
        layout.setPadding(true);
        layout.add(studentName, studentSurname, password, major, studyGroup, income, addStudent);


        addStudent.addClickListener(e -> {

            String name = studentName.getValue();
            String surname = studentSurname.getValue();
            String passwd = password.getValue();
            String maj = major.getValue();
            String _group = studyGroup.getValue();
            Integer inc = income.getValue();

            //Database DB = Database.getInstance();

            //ResultSet res2 = DB.getStudentInfo(ID);

            //ResultSet res3 = DB.getStudentGrades(ID);

            if(name != "" && surname!= "" && passwd != "" && inc != null && maj !="" && _group!="")
            {
                Notification.show("Student with name:" + name + " " + surname + " " + passwd + " " + maj +
                        " " + _group + " " + inc +" has been added");
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
