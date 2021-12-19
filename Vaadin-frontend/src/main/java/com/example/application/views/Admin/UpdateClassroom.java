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

import java.util.Collection;
import java.util.HashMap;

@PageTitle("Update Classroom")
@Route(value = "updateClassroom", layout = AdminLayout.class)
public class UpdateClassroom extends VerticalLayout{

    private IntegerField capacity;
    private Button updateClassroom;

    public UpdateClassroom() {

        capacity = new IntegerField("Select Capacity");
        updateClassroom = new Button("Update Classroom");

        // Create request and set the endpoint
        ApiRequest getCl = new ApiRequest("http://localhost:8080/admin/get-classrooms");
        getCl.addCookie(OwnCookieManager.getInstance().getCookie());

        HashMap<String, Object> classes = getCl.send();

        //String ceva = classes.get("name").toString();

        System.out.println(classes);

        Select<String> classSelect = new Select<String>();
        classSelect.setLabel("Select Class");
        //sclassSelect.setItems((Collection<String>) classes);
        classSelect.setValue("Class");

        Select<String> type = new Select<String>();
        type.setLabel("Class Type");
        type.setItems("Lab", "Course");
        type.setValue("Lab");

        setPadding(true);
        setSpacing(true);

        add(new H2("Modify classroom by pressing the button"));

        VerticalLayout layout = createLayout("Update Classroom data here");
        layout.setPadding(true);
        layout.add(classSelect, type, capacity, updateClassroom);


        updateClassroom.addClickListener(e -> {

            String classroom = classSelect.getValue();
            String _type = type.getValue();
            Integer _capacity = capacity.getValue();

            ApiRequest req = new ApiRequest("http://localhost:8080/admin/update-classroom");


            if(classroom != "" && _type!= "" && _capacity != 0)
            {
                req.addParameter("name", classroom);
                req.addParameter("capacity", String.valueOf(_capacity));
                req.addParameter("kind", _type);

                req.addCookie(OwnCookieManager.getInstance().getCookie());
                // Send the request and get the response
                HashMap<String, Object> response = req.send();

                if(response.get("status").equals("SUCCESS"))
                {
                    Notification.show("Classroom has been updated");
                }
                else
                {
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
