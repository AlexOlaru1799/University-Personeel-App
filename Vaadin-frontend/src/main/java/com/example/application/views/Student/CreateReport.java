package com.example.application.views.Student;

import com.example.application.views.Utils.ApiRequest;
import com.example.application.views.Utils.OwnCookieManager;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.HashMap;

@PageTitle("Create Report")
@Route(value = "createReport", layout = StudentLayout.class)


public class CreateReport extends VerticalLayout {
    private final TextField supervizorName;
    private final TextField supervizorSurname;
    private final TextField kind;
    private final Button generateReport;

    public CreateReport() {
        supervizorName = new TextField("Supervizor Name");
        supervizorSurname = new TextField("Supervizor Surname");
        kind = new TextField("Report Type");
        generateReport = new Button("Generate Report ");

        VerticalLayout layout = createLayout("Please complete the request: ");
        layout.setPadding(true);
        layout.add(supervizorName, supervizorSurname, kind, generateReport);
        TextArea textArea = new TextArea();
        textArea.isReadOnly();
        layout.add(textArea);


        generateReport.addClickListener(e -> {

            String name = supervizorName.getValue();
            String surname = supervizorSurname.getValue();
            String type = kind.getValue();


            if (name != "") {
                // Create request and set the endpoint
                ApiRequest req = new ApiRequest("http://localhost:8080/student/createRequest");
                req.addCookie(OwnCookieManager.getInstance().getCookie());

                req.addParameter("kind", type);
                req.addParameter("sName", name);
                req.addParameter("sSurname", surname);


                HashMap<String, Object> response = req.send();

                System.out.println(response);
                String print = "SUCCES";
                String print2 = "FAILED";


                if (response.get("status").equals("SUCCESS")) {

                    textArea.setValue(print);
                } else {
                    textArea.setValue(print2);
                }


            } else {
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

