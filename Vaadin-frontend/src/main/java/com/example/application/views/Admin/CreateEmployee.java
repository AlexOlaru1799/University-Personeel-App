package com.example.application.views.Admin;

import com.example.application.views.Admin.AdminLayout;
import com.example.application.views.Utils.ApiRequest;
import com.example.application.views.Utils.OwnCookieManager;
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

import java.util.HashMap;

@PageTitle("Add Employee")
@Route(value = "addEmployee", layout = AdminLayout.class)
public class CreateEmployee extends VerticalLayout{

    private TextField employeeName;
    private TextField employeeSurname;
    private PasswordField password;
    private IntegerField salary;
    private Button addEmployee;

    public CreateEmployee() {

        employeeName = new TextField("Employee Name");
        employeeSurname = new TextField("Employee Surname");
        password = new PasswordField("Employee Password");
        salary = new IntegerField("Employee Salary");
        addEmployee = new Button("Add Employee");

        Select<String> position = new Select<String>();
        position.setLabel("Employee Position");
        position.setItems("Administrator", "Secretary", "Mentor", "Professor", "Janitor");
        position.setValue("Administrator");

        Select<String> role = new Select<String>();
        role.setLabel("Employee Role");
        role.setItems("Administrator", "Secretary", "Professor", "Student");
        role.setValue("Professor");

        setPadding(true);
        setSpacing(true);

        add(new H2("Add Employee by pressing the button"));

        VerticalLayout layout = createLayout("Insert employee data here");
        layout.setPadding(true);
        layout.add(employeeName, employeeSurname, password, position, role, salary,addEmployee);


        addEmployee.addClickListener(e -> {

            String name = employeeName.getValue();
            String surname = employeeSurname.getValue();
            String passwd = password.getValue();
            String pos = position.getValue();
            String role1 = role.getValue();
            Integer sal = salary.getValue();

            // Create request and set the endpoint
            ApiRequest req = new ApiRequest("http://localhost:8080/admin/create-employee");


            if(name != "" && surname!= "" && passwd != "" && sal != null)
            {
                req.addParameter("name", name);
                req.addParameter("surname", surname);
                req.addParameter("password", passwd);
                req.addParameter("position", pos);
                req.addParameter("role", role1);
                req.addParameter("salary", String.valueOf(sal));

                req.addCookie(OwnCookieManager.getInstance().getCookie());


                // Send the request and get the response
                HashMap<String, Object> response = req.send();

                if(response.get("status").equals("SUCCESS")) {

                    Notification.show("Employee with name:" + name + " " + surname + " has been added");
                }
                else{
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
