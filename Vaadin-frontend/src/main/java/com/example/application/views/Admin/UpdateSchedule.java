package com.example.application.views.Admin;

import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.time.LocalTime;

@PageTitle("Update Schedule")
@Route(value = "updateSchedule", layout = AdminLayout.class)
public class UpdateSchedule extends VerticalLayout{

    private DatePicker datePicker;
    private TimePicker timePicker;
    private Button updateSchedule;

    public UpdateSchedule() {
        datePicker = new DatePicker("Select new Date");
        updateSchedule = new Button("Update Schedule");

        timePicker = new TimePicker();
        timePicker.setLabel("Select Hour");
        timePicker.setValue(LocalTime.of(8, 0));

        Select<String> group = new Select<String>();
        group.setLabel("Select Study Group");
        group.setItems("Nj", "Nj", "Nj", "Nj", "Nj");
        group.setValue("Nj");

        Select<String> subject = new Select<String>();
        subject.setLabel("Select Subject");
        subject.setItems("Nj", "Nj", "Nj", "Nj", "Nj");
        subject.setValue("Nj");

        setPadding(true);
        setSpacing(true);

        add(new H2("Update Schedule by pressing the button"));

        VerticalLayout layout = createLayout("Update Schedule");
        layout.setPadding(true);
        layout.add(subject, group , datePicker, timePicker, updateSchedule);


        updateSchedule.addClickListener(e -> {

            String _subject = subject.getValue();
            String _group = group.getValue();
            LocalDate _date = datePicker.getValue();
            LocalTime _time = timePicker.getValue();

            //Database DB = Database.getInstance();

            //ResultSet res2 = DB.getStudentInfo(ID);

            //ResultSet res3 = DB.getStudentGrades(ID);

            if(_date != null && _time!= null)
            {
                Notification.show("Updated:" + _subject + " " + _group + " " + _date + " " + _time + " in schedule");
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
