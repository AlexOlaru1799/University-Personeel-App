package com.example.application.views.Admin;

import com.example.application.views.Utils.ApiRequest;
import com.example.application.views.Utils.OwnCookieManager;
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
import java.util.ArrayList;
import java.util.HashMap;

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

        // Create request and set the endpoint
        ApiRequest getGroups = new ApiRequest("http://localhost:8080/admin/get-study-groups");
        getGroups.addCookie(OwnCookieManager.getInstance().getCookie());

        HashMap<String, Object> groups = getGroups.send();

        ArrayList<Object> objResponse = new ArrayList<Object>(groups.values());

        ArrayList<Object> objGroups = (ArrayList<Object>)objResponse.get(0);

        ArrayList<String> groupsName = new ArrayList<String>(objGroups.size());

        for (int i = 0; i < objGroups.size(); i++) {
            String[] set = null;
            String c = objGroups.get(i).toString();
            set = c.split("=");
            set = set[1].split(",");
            groupsName.add(set[0]);
        }


        Select<String> group = new Select<String>();
        group.setLabel("Select Study Group");
        group.setItems(groupsName);
        group.setValue(groupsName.get(0));

        // Create request and set the endpoint
        ApiRequest getCourse = new ApiRequest("http://localhost:8080/admin/get-courses");
        getCourse.addCookie(OwnCookieManager.getInstance().getCookie());

        HashMap<String, Object> courses = getCourse.send();

        ArrayList<Object> objResp = new ArrayList<Object>(courses.values());

        ArrayList<Object> objClasses = (ArrayList<Object>)objResp.get(0);

        ArrayList<String> coursesName = new ArrayList<String>(objClasses.size());


        for (int i = 0; i < objClasses.size(); i++) {
            String[] set = null;
            String c = objClasses.get(i).toString();
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

        add(new H2("Update Schedule by pressing the button"));

        VerticalLayout layout = createLayout("Update Schedule");
        layout.setPadding(true);
        layout.add(subject, group , datePicker, timePicker, updateSchedule);


        updateSchedule.addClickListener(e -> {

            String _subject = subject.getValue();
            String _group = group.getValue();
            LocalDate _date = datePicker.getValue();
            LocalTime _time = timePicker.getValue();

            System.out.println(_subject + "  " + _group + "  " + _date.toString() + "  " + _time.toString());

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
