
package com.example.application.views.Secretary;

import com.example.application.components.Course;
import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.shared.Registration;
import org.json.JSONObject;
import com.example.application.views.Utils.ApiRequest;
import com.example.application.views.Utils.OwnCookieManager;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import com.example.application.components.Course;

import java.util.ArrayList;
import java.util.HashMap;

@PageTitle("View Courses")
@Route(value = "viewCourses", layout = MainLayout.class)
public class ViewCourses extends VerticalLayout {
    private ArrayList<Course> students;
    private Grid<Course> grid;





    public ViewCourses() {
        VerticalLayout layout = new VerticalLayout();
        layout.getStyle().set("background-color", "#e8ebef");

        grid = new Grid<Course>();
        grid.addColumn(Course::getCourse).setHeader("Name").setSortable(true);
        grid.addColumn(Course::getCredits).setHeader("Credits").setSortable(true);
        grid.addColumn(Course::getName).setHeader("Professor Name").setSortable(true);
        grid.addColumn(Course::getSurname).setHeader("Professor Surname").setSortable(true);

        //grid.getStyle().set("color","blue");
        //grid.getHeaderRows()

        grid.setHeight("600px");


        ApiRequest req = new ApiRequest("http://localhost:8080/secretary/view-courses");
        req.addCookie(OwnCookieManager.getInstance().getCookie());
        HashMap<String, Object> response = req.send();
        ArrayList<Object> objects = new ArrayList<Object>(response.values());

        ArrayList<Object> studs_json = (ArrayList<Object>) objects.get(0);

        students = new ArrayList<Course>(studs_json.size());

       // System.out.println("------------> " + studs_json.size());

        for (Object stud_json : studs_json) {
            Course S = new Course();
            try {
                JSONObject obj = new JSONObject(stud_json.toString());
                if(obj.getString("name").contains("_"))
                {
                    String[] parts = obj.getString("name").split("_");
                    String part1 = parts[0];
                    String part2 = parts[1];
                    S.setCourse(part1 + " " + part2);
                }
                else
                {
                    S.setCourse(obj.getString("name"));
                }


                S.setCredits(Integer.parseInt(obj.get("credits").toString()));
                S.setName(obj.getJSONObject("professor").get("name").toString());
                S.setSurname(obj.getJSONObject("professor").get("surname").toString());
                students.add(S);
                //System.out.println(S.getCourse());
               // System.out.println(S.getCredits());
               // System.out.println(S.getName());
               // System.out.println(S.getSurname());
            } catch (Exception exc) {
                System.out.println("exc");
            }

        }


        grid.setItems(students);

        //add(filters);
        layout.add(grid);
        add(layout);
    }


}
