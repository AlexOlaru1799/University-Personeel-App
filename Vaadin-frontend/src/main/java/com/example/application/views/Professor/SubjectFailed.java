package com.example.application.views.Professor;

import com.example.application.views.Utils.ApiRequest;
import com.example.application.views.Utils.OwnCookieManager;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.HashMap;

@PageTitle("Subject Failed")
@Route(value = "subjectFailed", layout = ProfessorLayout.class)

public class SubjectFailed extends VerticalLayout{
    private Button showSubjects;

    public SubjectFailed() {

        showSubjects = new Button("Show Failed Subject");

        setPadding(true);
        setSpacing(true);

        add(new H2("Show students who failed at a single subject!"));

        VerticalLayout layout = createLayout("Subject failed");
        layout.setPadding(true);
        layout.add(showSubjects);

        VerticalLayout infoLayout = createLayout("Student who failed at one exam: ");
        TextArea textArea = new TextArea();
        textArea.setWidth("500px");
        textArea.isReadOnly();
        textArea.setHeight("500px");
        infoLayout.add(textArea);

        showSubjects.addClickListener(e -> {

            ApiRequest req = new ApiRequest("http://localhost:8080/professor/failedOneSubject");
            req.addCookie(OwnCookieManager.getInstance().getCookie());

            HashMap<String, Object> response = req.send();

            System.out.println(response);

            ArrayList<Object> objResp = new ArrayList<Object>(response.values());

            ArrayList<Object> objClasses = (ArrayList<Object>)objResp.get(0);

            System.out.println(objClasses);

            ArrayList<String> name = new ArrayList<String>(objClasses.size());
            ArrayList<String> surname = new ArrayList<String>(objClasses.size());
            ArrayList<String> courseName = new ArrayList<String>(objClasses.size());
            ArrayList<String> studyGroup = new ArrayList<String>(objClasses.size());

            for (int i = 0; i < objClasses.size(); i++) {
                String[] set = null;
                String c = objClasses.get(i).toString();
                set = c.split("=");
                String nume = set[9].split(",")[0];
                String prenume = set[10].split(",")[0];
                String materie = set[3].split(",")[0];
                String grupa = set[12].split(",")[0];
                set = set[1].split(",");
                name.add(nume);
                surname.add(prenume);
                courseName.add(materie);
                studyGroup.add(grupa);
            }

            String failedStudents = "";
            for(int i = 0;i < name.size(); i++)
            {
                failedStudents += name.get(i);
                failedStudents += "\t";
                failedStudents += surname.get(i);
                failedStudents += "\t";
                failedStudents += studyGroup.get(i);
                failedStudents += "\t";
                failedStudents += courseName.get(i);
                failedStudents += "\n";
            }

            if(response.get("status").equals("SUCCESS"))
            {
                if(response.get("result") != null ) {
                    textArea.setValue(failedStudents);
                }
                Notification.show("Print succes!");
            }
            else
            {
                Notification.show("Failed :(");
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
