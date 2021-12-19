package com.example.application.views.Admin;

import com.example.application.views.Admin.AdminLayout;
import com.example.application.views.Utils.ApiRequest;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.HashMap;

@PageTitle("Archive Student")
@Route(value = "archiveStudent", layout = AdminLayout.class)
public class ArchiveStudent extends VerticalLayout{

    private Button archiveButton;

    public ArchiveStudent() {

        archiveButton = new Button("Archive Students");

        setPadding(true);
        setSpacing(true);

        add(new H2("Archive Students by pressing the button"));

        VerticalLayout layout = createLayout("Archive Students Section");
        layout.setPadding(true);
        layout.add(archiveButton);


        archiveButton.addClickListener(e -> {
            // Create request and set the endpoint
            ApiRequest req = new ApiRequest("http://localhost:8080/archive-students");

            // Send the request and get the response
            HashMap<String, Object> response = req.send();

            Notification.show("Be patient, this may take some time...");

            if(response.get("status").equals("SUCCESS"))
            {
                Notification.show("Archive was created successfully :)");
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
