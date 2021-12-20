
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
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.HashMap;

@PageTitle("Add Subject")
@Route(value = "createDocument", layout = MainLayout.class)
public class CreateDocument extends VerticalLayout {

    public CreateDocument() {
        VerticalLayout mainLayout = createLayout("Enter document details : ");



        TextField title = new TextField("Document title");
        title.setWidth("500px");

        TextArea content = new TextArea("Document content");
        content.setWidth("500px");
        content.setHeight("300px");



        Button addSubject = new Button("Push Document");
        addSubject.setWidth("500px");

        mainLayout.add(title,content,addSubject);
        add(mainLayout);


        addSubject.addClickListener(e -> {
            String titleS = title.getValue();
            String contentS = content.getValue();


            ApiRequest req = new ApiRequest("http://localhost:8080/create-document");

            req.addCookie(OwnCookieManager.getInstance().getCookie());

            req.addParameter("course_name",titleS);
            req.addParameter("professor_surname",contentS);


            // Send the request and get the response
            HashMap<String, Object> response = req.send();

            if(response.get("status").equals("SUCCESS")) {

                Notification.show("Document has been added!");
            }
            else{
                Notification.show("Failed :(");
            }


            title.clear();
            content.clear();

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
