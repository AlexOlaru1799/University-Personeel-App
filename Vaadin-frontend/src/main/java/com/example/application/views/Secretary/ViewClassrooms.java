
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

@PageTitle("View Clasrooms")
@Route(value = "viewClassrooms", layout = MainLayout.class)
public class ViewClassrooms extends VerticalLayout {

    public ViewClassrooms() {
        VerticalLayout mainLayout = createLayout("See Classroom Details");

        TextField name = new TextField("Classroom name");
        name.setWidth("500px");



        H3 details = new H3("");
        details.getStyle().set("color", "blue");
        details.getStyle().set("fontWeight", "bold");
        H5 capacity = new H5("");
        H5 classroomName = new H5("");

        H3 features = new H3("");
        features.getStyle().set("color", "blue");
        features.getStyle().set("fontWeight", "bold");

        TextArea textArea = new TextArea("");
        textArea.setHeight("200px");
        textArea.setWidth("500px");
        textArea.setReadOnly(true);

        Button addStudent = new Button("See Classroom");
        addStudent.setWidth("500px");

        mainLayout.add(name,addStudent,details,capacity,classroomName,features);
        //add(mainLayout);








        addStudent.addClickListener(e -> {
            String nameS = name.getValue();


            ApiRequest req = new ApiRequest("http://localhost:8080/secretary/view-classroom");


            req.addCookie(OwnCookieManager.getInstance().getCookie());
            req.addParameter("name",nameS);
            HashMap<String, Object> response = req.send();
            ArrayList<Object> objects = new ArrayList<Object>(response.values());


            Object studs_json = objects.get(0);

            textArea.clear();


            try {
                JSONObject obj = new JSONObject(studs_json.toString());

                details.setText("Classroom general details : ");
                capacity.setText("#Capacity : " + obj.getString("capacity"));
                classroomName.setText("#Name : " + nameS);

                features.setText("Classroom features : ");

                //JSONObject obj2 = new JSONObject(obj.getJSONObject("features").toString());


                JSONArray arr = obj.getJSONArray("features");

                for (int i = 0; i < arr.length(); i++) {
                    //System.out.println(arr.getJSONObject(i).getString("description"));
                    textArea.setValue(textArea.getValue() + "\n- " + arr.getJSONObject(i).getString("description"));
                }

                mainLayout.add(textArea);
                add(mainLayout);

            } catch (JSONException ex) {
                ex.printStackTrace();
            }





            //content.setValue(response.get("result").toString());

            name.clear();


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
