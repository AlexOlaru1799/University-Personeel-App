package com.example.application.views.Professor;

import com.example.application.views.Utils.ApiRequest;
import com.example.application.views.Utils.OwnCookieManager;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
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

        // Create request and set the endpoint
        ApiRequest getCourse = new ApiRequest("http://localhost:8080/admin/get-courses");
        getCourse.addCookie(OwnCookieManager.getInstance().getCookie());

        HashMap<String, Object> courses = getCourse.send();

        ArrayList<Object> objResponse = new ArrayList<Object>(courses.values());

        ArrayList<Object> objectClasses = (ArrayList<Object>)objResponse.get(0);

        ArrayList<String> coursesName = new ArrayList<String>(objectClasses.size());


        for (int i = 0; i < objectClasses.size(); i++) {
            String[] set = null;
            String c = objectClasses.get(i).toString();
            set = c.split("=");
            set = set[1].split(",");
            coursesName.add(set[0]);
        }

        Select<String> subject = new Select<String>();
        subject.setLabel("Select Subject");
        subject.setItems(coursesName);
        subject.setValue(coursesName.get(0));

        setPadding(true);
        setSpacing(true);

        VerticalLayout layout = createLayout("");
        layout.setPadding(true);
        layout.add(subject, showSubjects);

        VerticalLayout infoLayout = createLayout("Students who failed at this subject: ");
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
                String nume = set[10].split(",")[0];
                String prenume = set[11].split(",")[0];
                String materie = set[4].split(",")[0];
                String grupa = set[13].split(",")[0];
                name.add(nume);
                surname.add(prenume);
                courseName.add(materie);
                studyGroup.add(grupa);
            }

            String failedStudents = "";
            String correctCourse = "";
            String message = "At this course no one failed!";

            for(int i = 0; i < name.size(); i++)
            {
                if(subject.getValue().equals(courseName.get(i))) {
                    correctCourse = "";
                    failedStudents += name.get(i);
                    failedStudents += "\t";
                    failedStudents += surname.get(i);
                    failedStudents += "\t";
                    failedStudents += studyGroup.get(i);
                    failedStudents += "\n";
                    correctCourse += courseName.get(i);
                }
            }

            System.out.println(subject.getValue().toString());
            System.out.println(courseName);

            System.out.println(correctCourse);

            if(response.get("status").equals("SUCCESS"))
            {
                if(response.get("result") != null && subject.getValue().equals(correctCourse)) {
                    textArea.setValue(failedStudents);
                }
                else
                {
                    textArea.setValue(message);
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
