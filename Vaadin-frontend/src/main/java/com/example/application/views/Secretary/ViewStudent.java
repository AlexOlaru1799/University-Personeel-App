
package com.example.application.views.Secretary;



import com.example.application.views.Utils.ApiRequest;
import com.example.application.views.Utils.OwnCookieManager;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@PageTitle("View Student")
@Route(value = "viewStudent", layout = MainLayout.class)
public class ViewStudent extends VerticalLayout {

    public ViewStudent() {
        VerticalLayout mainLayout = createLayout("Insert the name/surname of the student");

        TextField name = new TextField("Student name");
        name.setWidth("500px");
        TextField surname = new TextField("Student surname");
        surname.setWidth("500px");


        Button addStudent = new Button("View Student Information");
        addStudent.setWidth("500px");

        mainLayout.add(name,surname,addStudent);
        add(mainLayout);

        VerticalLayout infoLayout = createLayout("Student Info : ");
        TextArea textArea = new TextArea();
        textArea.setWidth("500px");
        textArea.isReadOnly();
        textArea.setHeight("500px");
        infoLayout.add(textArea);

        H3 infoStudent = new H3("Student personal Information");
        H5 numeInfo = new H5("");
        H5 prenumeInfo = new H5("");

        infoLayout.add(infoStudent,numeInfo,prenumeInfo);

        add(infoLayout);

        addStudent.addClickListener(e -> {
            String nameS = name.getValue();
            String surnameS = surname.getValue();
            ApiRequest req = new ApiRequest("http://localhost:8080/secretary/view-student");

            req.addParameter("name",nameS);
            req.addParameter("surname",surnameS);


            req.addCookie(OwnCookieManager.getInstance().getCookie());


            // Send the request and get the response
            HashMap<String, Object> response = req.send();










            if(response.get("status").equals("SUCCESS")) {

                Notification.show("Student with name: " + nameS + " " + surnameS + " has this info");
                textArea.setValue(response.get("result").toString());



               // numeInfo.setText( listOfValues.get(0).toString());
            }
            else{
                Notification.show("There is no Student with this name!");
            }

            name.clear();
            surname.clear();




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
