package com.example.application.views.Professor;

import com.example.application.views.Utils.ApiRequest;
import com.example.application.views.Utils.OwnCookieManager;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;
import java.util.HashMap;

@PageTitle("View Student Grades ")
@Route(value = "viewStudentGrades", layout = ProfessorLayout.class)

public class GradesStudent extends VerticalLayout {

    private TextField studentName;
    private TextField studentSurname;
    private Button showStudentGrades;

    public GradesStudent()
    {
        studentName = new TextField("Student Name");
        studentSurname = new TextField("Student Surname");
        showStudentGrades = new Button("ShowStudentsGrades");

        VerticalLayout layout = createLayout("Grade Add");
        layout.setPadding(true);
        layout.add(studentName, studentSurname, showStudentGrades);

        setPadding(true);
        setSpacing(true);

        VerticalLayout infoLayout = createLayout("Student Grades: ");
        TextArea textArea = new TextArea();
        textArea.setWidth("500px");
        textArea.isReadOnly();
        textArea.setHeight("500px");
        infoLayout.add(textArea);

        showStudentGrades.addClickListener(e -> {

            String name = studentName.getValue();
            String surname = studentSurname.getValue();

            if(name != "")
            {
                // Create request and set the endpoint
                ApiRequest req = new ApiRequest("http://localhost:8080/professor/gradesForStudent");
                req.addCookie(OwnCookieManager.getInstance().getCookie());

                req.addParameter("name", name);
                req.addParameter("surname", surname);

                HashMap<String, Object> response = req.send();


                ArrayList<Object> objResp = new ArrayList<Object>(response.values());

                ArrayList<Object> objClasses = (ArrayList<Object>)objResp.get(0);

                System.out.println(objClasses);

                ArrayList<String> grades = new ArrayList<String>(objClasses.size());
                ArrayList<String> courseName = new ArrayList<String>(objClasses.size());

                for (int i = 0; i < objClasses.size(); i++) {
                    String[] set = null;
                    String c = objClasses.get(i).toString();
                    set = c.split("=");
                    String materie_name_cality = set[3].split(",")[0];
                    set = set[1].split(",");
                    grades.add(set[0]);
                    courseName.add(materie_name_cality);
                }

                Grid grid = new Grid();

                grid.addColumn("courseName");
                grid.addColumn("grades");

                String gradesMaterii = "";
                for(int i = 0;i < grades.size(); i++)
                {
                   gradesMaterii += courseName.get(i);
                   gradesMaterii += ": ";
                   gradesMaterii += grades.get(i);
                   gradesMaterii += "\n";

                }

                if(response.get("status").equals("SUCCESS"))
                {
                    if(response.get("result") != null ) {
                        textArea.setValue(gradesMaterii);
                    }
                    Notification.show("Outstanding succes!");
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
