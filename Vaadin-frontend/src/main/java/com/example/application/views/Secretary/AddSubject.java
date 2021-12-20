
package com.example.application.views.Secretary;



import com.example.application.views.Utils.ApiRequest;
import com.example.application.views.Utils.OwnCookieManager;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.HashMap;

@PageTitle("Add Subject")
@Route(value = "addSubject", layout = MainLayout.class)
public class AddSubject extends VerticalLayout {

    public AddSubject() {
        VerticalLayout mainLayout = createLayout("Insert the necessary info");

        IntegerField credits = new IntegerField("Course credits");
        credits.setWidth("500px");

        TextField name = new TextField("Course name");
        name.setWidth("500px");
        TextField nameP = new TextField("Professor name");
        nameP.setWidth("500px");
        TextField surnameP = new TextField("Professor surname");
        surnameP.setWidth("500px");


        Button addSubject = new Button("Add Course");
        addSubject.setWidth("500px");

        mainLayout.add(name,nameP,surnameP,credits,addSubject);
        add(mainLayout);


        addSubject.addClickListener(e -> {
            String nameS = name.getValue();
            String nameSP = nameP.getValue();
            String surnameSP = surnameP.getValue();
            Integer creditsS = credits.getValue();

            ApiRequest req = new ApiRequest("http://localhost:8080/secretary/add-course");

            req.addCookie(OwnCookieManager.getInstance().getCookie());

            req.addParameter("course_name",nameS);
            req.addParameter("professor_surname",surnameSP);
            req.addParameter("professor_name",nameSP);
            req.addParameter("credits",creditsS.toString());





            // Send the request and get the response
            HashMap<String, Object> response = req.send();

            if(response.get("status").equals("SUCCESS")) {

                Notification.show("Course with name: " + nameS + " has been added");
            }
            else{
                Notification.show("Failed :(");
            }


            name.clear();
            surnameP.clear();
            nameP.clear();
            credits.clear();



        });
    }

    private VerticalLayout createLayout(String caption) {
        VerticalLayout hl = new VerticalLayout();
        hl.setWidth("600px");
        hl.getStyle().set("background-color", "#e8ebef");
        //add(new H1(caption));
        hl.add(new H3(caption));

        add(hl);
        add(new Html("<span>&nbsp;</span>")); // spacer
        return hl;
    }

}
