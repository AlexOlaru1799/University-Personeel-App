package com.example.application.views.Admin;

import com.example.application.views.Admin.AdminLayout;
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

@PageTitle("Update Classroom")
@Route(value = "updateClassroom", layout = AdminLayout.class)
public class UpdateClassroom extends VerticalLayout{

    private IntegerField capacity;
    private Button updateClassroom;

    public UpdateClassroom() {

        capacity = new IntegerField("Select Capacity");
        updateClassroom = new Button("Update Classroom");

        Select<String> classSelect = new Select<String>();
        classSelect.setLabel("Select Class");
        classSelect.setItems("Class", "Class", "Class", "Class", "Class");
        classSelect.setValue("Class");

        Select<String> type = new Select<String>();
        type.setLabel("Student Study Group");
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

            //Database DB = Database.getInstance();

            //ResultSet res2 = DB.getStudentInfo(ID);

            //ResultSet res3 = DB.getStudentGrades(ID);

            if(classroom != "" && _type!= "" && _capacity != 0)
            {
                Notification.show("Student with name:" + classroom + " " + _type + " has been added");
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
