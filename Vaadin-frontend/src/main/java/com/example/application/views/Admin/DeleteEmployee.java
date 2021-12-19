package com.example.application.views.Admin;

import com.example.application.views.Admin.AdminLayout;
import com.example.application.views.Utils.ApiRequest;
import com.example.application.views.Utils.OwnCookieManager;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.net.CookieManager;
import java.util.HashMap;

@PageTitle("Delete Employee")
@Route(value = "deleteEmployee", layout = AdminLayout.class)
public class DeleteEmployee extends VerticalLayout{

    private TextField employeeName;
    private TextField employeeSurName;
    private Button deleteButton;

    public DeleteEmployee() {

        employeeName = new TextField("Employee Name");
        employeeSurName = new TextField("Employee Surname");
        deleteButton = new Button("Delete Employee");

        setPadding(true);
        setSpacing(true);

        add(new H2("Delete Employee by pressing the button"));

        VerticalLayout layout = createLayout("Enter employee name here");
        layout.setPadding(true);
        layout.add(employeeName, employeeSurName, deleteButton);


        deleteButton.addClickListener(e -> {

            String name = employeeName.getValue();
            String surname = employeeSurName.getValue();

            // Create request and set the endpoint
            ApiRequest req = new ApiRequest("http://localhost:8080/admin/delete-employee");


            if(name != "" && surname != "")
            {
                req.addParameter("name", name);
                req.addParameter("surname", surname);

                req.addCookie(OwnCookieManager.getInstance().getCookie());
                // Send the request and get the response
                HashMap<String, Object> response = req.send();

                if(response.get("status").equals("SUCCESS"))
                {
                    Notification.show("Employee with name:" + name + surname + " has been deleted");
                }
                else
                {
                    Notification.show("Failed :(");
                }
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
