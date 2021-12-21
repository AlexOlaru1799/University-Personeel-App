
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

        VerticalLayout infoLayout = createLayout("");


        H3 infoStudent = new H3("");
        infoStudent.getStyle().set("color", "blue");
        infoStudent.getStyle().set("fontWeight", "bold");
        H5 numeInfo = new H5("");
        H5 prenumeInfo = new H5("");
        H5 incomeInfo = new H5("");
        H5 studentUsername = new H5("");
        H5 studentPassword = new H5("");
        H3 studyGroup = new H3("");
        studyGroup.getStyle().set("color", "blue");
        studyGroup.getStyle().set("fontWeight", "bold");
        H5 nameGrupa = new H5("");
        H5 anGrupa = new H5("");
        H3 mentorInfo = new H3("");
        mentorInfo.getStyle().set("color", "blue");
        mentorInfo.getStyle().set("fontWeight", "bold");
        H5 mentorName = new H5("");
        H5 mentorSurname = new H5("");
        H5 mentorSalary = new H5("");
        H5 mentorPosition = new H5("");
        H5 mentorUsername = new H5("");
        H5 mentorPassword = new H5("");
        H3 facultyInfo = new H3("");
        facultyInfo.getStyle().set("color", "blue");
        facultyInfo.getStyle().set("fontWeight", "bold");
        H5 major = new H5("");
        H5 faculty = new H5("");

        infoLayout.add(infoStudent,numeInfo,prenumeInfo,studentUsername,studentPassword,incomeInfo,studyGroup,nameGrupa,anGrupa,mentorInfo,mentorName,mentorSurname,mentorSalary,mentorPosition,mentorUsername,mentorPassword);

        infoLayout.add(facultyInfo,major,faculty);

        add(infoLayout);

        addStudent.addClickListener(e -> {
            String nameS = name.getValue();
            String surnameS = surname.getValue();
            ApiRequest req = new ApiRequest("http://localhost:8080/secretary/view-student");

            //ApiRequest req = new ApiRequest("http://localhost:8080/secretary/view-students");
            req.addCookie(OwnCookieManager.getInstance().getCookie());
            req.addParameter("name",nameS);
            req.addParameter("surname",surnameS);
            HashMap<String, Object> response = req.send();
            ArrayList<Object> objects = new ArrayList<Object>(response.values());


            Object studs_json = objects.get(0);



            //req.addCookie(OwnCookieManager.getInstance().getCookie());


            // Send the request and get the response
            //HashMap<String, Object> response = req.send();










            if(response.get("status").equals("SUCCESS")) {

                Notification.show("Student with name: " + nameS + " " + surnameS + " has this info");

                try {

                    JSONObject obj = new JSONObject(studs_json.toString());

                    JSONObject studyGroupC = obj.getJSONObject("studyGroup");
                    JSONObject mentorS = studyGroupC.getJSONObject("mentor");
                    String positionS = mentorS.getJSONObject("position").getString("description");
                    String numeGrupa = studyGroupC.getString("name");
                    String mentor = studyGroupC.getJSONObject("mentor").getString("name");
                    String mentorSurnameS = studyGroupC.getJSONObject("mentor").getString("surname");
                    String mentorSalry = studyGroupC.getJSONObject("mentor").getString("salary");
                    //numeInfo.setText(response.get("result").toString());
                    //prenumeInfo.setText(mentor);

                    String mentorUsernameS = obj.getJSONObject("studyGroup").getJSONObject("mentor").getJSONObject("user").getString("username");
                    String mentorPasswordS = obj.getJSONObject("studyGroup").getJSONObject("mentor").getJSONObject("user").getString("password");
                    String anGrupaS = obj.getJSONObject("studyGroup").getString("study_year");
                    String incomeInfoS = obj.getString("income");
                    String majorS = obj.getJSONObject("major").getString("name");
                    String facultyS = obj.getJSONObject("major").getJSONObject("faculty").getString("name");
                    String studentUsernameS = obj.getJSONObject("user").getString("username");
                    String studentPasswordS = obj.getJSONObject("user").getString("password");

                    infoStudent.setText("Student personal Information");
                    numeInfo.setText("#Name: " + nameS);
                    prenumeInfo.setText("#Surname: " + surnameS);
                    studyGroup.setText("Study Group Information");
                    nameGrupa.setText("#Study Group: " + numeGrupa);
                    mentorInfo.setText("Mentor Information");
                    mentorName.setText("#Mentor Name: " + mentor);
                    mentorSurname.setText("#Mentor Surname: " + mentorSurnameS);
                    mentorSalary.setText("#Mentor Salary: " + mentorSalry);
                    mentorPosition.setText("#Mentor Position: " + positionS);
                    mentorUsername.setText("#Mentor Username: " + mentorUsernameS);
                    mentorPassword.setText("#Mentor Password: " + mentorPasswordS);
                    anGrupa.setText("#Study Group Year : " + anGrupaS);
                    incomeInfo.setText("#Monthly Income : " + incomeInfoS + " lei");
                    facultyInfo.setText("Major information: ");
                    major.setText("#Major name: " + majorS);
                    faculty.setText("#Faculty name: "+ facultyS);
                    studentUsername.setText("#Student Username: " + studentUsernameS);
                    studentPassword.setText("#Student Password: " + studentPasswordS);


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
