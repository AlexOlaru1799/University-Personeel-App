
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@PageTitle("View Teacher")
@Route(value = "viewTeacher", layout = MainLayout.class)
public class ViewTeacher extends VerticalLayout {

    public ViewTeacher() {
        VerticalLayout mainLayout = createLayout("Insert the name/surname of the teacher");

        TextField name = new TextField("Teacher name");
        name.setWidth("500px");
        TextField surname = new TextField("Teacher surname");
        surname.setWidth("500px");


        Button addStudent = new Button("View Teacher Information");
        addStudent.setWidth("500px");

        mainLayout.add(name,surname,addStudent);
        add(mainLayout);

        VerticalLayout infoLayout = createLayout("");


        H3 infoStudent = new H3("");
        infoStudent.getStyle().set("color", "blue");
        infoStudent.getStyle().set("fontWeight", "bold");
        H5 numeInfo = new H5("");
        H5 prenumeInfo = new H5("");
        H5 incomeInfo = new H5("");
        H5 teacherUsername = new H5("");
        H5 teacherPosition = new H5("");
        H5 teacherRole = new H5("");


        infoLayout.add(infoStudent,numeInfo,prenumeInfo,incomeInfo,teacherPosition,teacherRole,teacherUsername);

        //,numeInfo,prenumeInfo,studentUsername,studentPassword,incomeInfo,studyGroup,nameGrupa,anGrupa,mentorInfo,mentorName,mentorSurname,mentorSalary,mentorPosition,mentorUsername,mentorPassword);

        //infoLayout.add(facultyInfo,major,faculty);

        add(infoLayout);

        addStudent.addClickListener(e -> {
            String nameS = name.getValue();
            String surnameS = surname.getValue();
            ApiRequest req = new ApiRequest("http://localhost:8080/secretary/view-professor");

            //ApiRequest req = new ApiRequest("http://localhost:8080/secretary/view-students");
            req.addCookie(OwnCookieManager.getInstance().getCookie());
            req.addParameter("name",nameS);
            req.addParameter("surname",surnameS);
            HashMap<String, Object> response = req.send();
            ArrayList<Object> objects = new ArrayList<Object>(response.values());


            Object studs_json = objects.get(0);









            if(response.get("status").equals("SUCCESS")) {

                Notification.show("Student with name: " + nameS + " " + surnameS + " has this info");

                try {

                    JSONObject obj = new JSONObject(studs_json.toString());


                    String incomeInfoS = obj.getString("salary");
                    String teacherPositionS = obj.getJSONObject("position").getString("description");
                    String teacherUsernameS = obj.getJSONObject("user").getString("username");
                    String teacherRoleS = obj.getJSONObject("user").getJSONObject("role").getString("description");


                    infoStudent.setText("Teacher Information : ");
                    numeInfo.setText("#Name: " + nameS);
                    prenumeInfo.setText("#Surname: " + surnameS);
                    teacherPosition.setText("#Position : " + teacherPositionS);
                    teacherRole.setText("#Role : " + teacherRoleS);
                    teacherUsername.setText("#Teacher Username: " + teacherUsernameS);

                    incomeInfo.setText("#Monthly Income : " + incomeInfoS + " lei");



                } catch (JSONException ex) {
                    ex.printStackTrace();
                }






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
