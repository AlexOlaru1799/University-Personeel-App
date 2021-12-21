package com.example.application.views.Admin;

import com.example.application.views.Utils.ApiRequest;
import com.example.application.views.Utils.OwnCookieManager;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.HashMap;

@PageTitle("Add Student")
@Route(value = "addStudent", layout = AdminLayout.class)
public class AddStudent extends VerticalLayout{

    private TextField studentName;
    private TextField studentSurname;
    private PasswordField password;
    private IntegerField income;
    private Button addStudent;

    public AddStudent() {

        studentName = new TextField("Student Name");
        studentSurname = new TextField("Student Surname");
        password = new PasswordField("Student Password");
        income = new IntegerField("Student Income");
        addStudent = new Button("Add Student");

        Select<String> majorSelect = new Select<String>();
        majorSelect.setLabel("Student Major");
        majorSelect.setItems("Calculatoare", "Cyber", "ESCA", "BAT", "AEV");
        majorSelect.setValue("BAT");

        Select<String> studyGroup = new Select<String>();
        studyGroup.setLabel("Student Study Group");
        studyGroup.setItems("C111C", "C114C", "E123E", "C154Com", "A171B", "C113Cy");
        studyGroup.setValue("C114C");

        setPadding(true);
        setSpacing(true);

        add(new H2("Add Student by pressing the button"));

        VerticalLayout layout = createLayout("Insert Student data here");
        layout.setPadding(true);
        layout.add(studentName, studentSurname, password, majorSelect, studyGroup, income, addStudent);


        addStudent.addClickListener(e -> {

            // Create request and set the endpoint
            ApiRequest req = new ApiRequest("http://localhost:8080/admin/create-student");

            String name = studentName.getValue();
            String surname = studentSurname.getValue();
            String passwd = password.getValue();
            String maj = majorSelect.getValue();
            String _group = studyGroup.getValue();
            Integer inc = income.getValue();


            if(name != "" && surname!= "" && passwd != "" && inc != null && maj !="" && _group!="")
            {
                req.addParameter("name", name);
                req.addParameter("surname", surname);
                req.addParameter("password", passwd);
                req.addParameter("major", maj);
                req.addParameter("study_group", _group);
                req.addParameter("income", String.valueOf(inc));

                req.addCookie(OwnCookieManager.getInstance().getCookie());

                // Send the request and get the response
                HashMap<String, Object> response = req.send();

                if(response.get("status").equals("SUCCESS")) {
                    Notification.show("Student with name:" + name + " " + surname + " " + passwd + " " + maj +
                            " " + _group + " " + inc + " has been added");
                }
                else {
                    Notification.show("Failed :(");
                }
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
