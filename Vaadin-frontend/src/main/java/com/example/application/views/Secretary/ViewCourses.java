
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
    private Select<String> facultySelect;
    private Select<String> majorSelect;
    private Select<String> studyGroupSelect;
    private Select<String> yearSelect;

    private Registration facultySelectListener;
    private Registration majorSelectListener;
    private Registration studyGroupSelectListener;
    private Registration yearSelectListener;


    public ViewCourses() {
        VerticalLayout layout = new VerticalLayout();
        layout.getStyle().set("background-color", "#e8ebef");

        grid = new Grid<Course>();
        grid.addColumn(Course::getCourse).setHeader("Name").setSortable(true);
        grid.addColumn(Course::getCredits).setHeader("Credits").setSortable(true);
        grid.addColumn(Course::getName).setHeader("Professor Name").setSortable(true);
        grid.addColumn(Course::getSurname).setHeader("Professor Surname").setSortable(true);


        ApiRequest req = new ApiRequest("http://localhost:8080/secretary/view-courses");
        req.addCookie(OwnCookieManager.getInstance().getCookie());
        HashMap<String, Object> response = req.send();
        ArrayList<Object> objects = new ArrayList<Object>(response.values());

        ArrayList<Object> studs_json = (ArrayList<Object>) objects.get(0);

        students = new ArrayList<Course>(studs_json.size());

        System.out.println("------------> " + studs_json.size());

        for (Object stud_json : studs_json) {
            Course S = new Course();
            try {
                JSONObject obj = new JSONObject(stud_json.toString());
                S.setCourse(obj.getString("name"));
                S.setCredits(Integer.parseInt(obj.get("credits").toString()));
                S.setName(obj.getJSONObject("professor").get("name").toString());
                S.setSurname(obj.getJSONObject("professor").get("surname").toString());
                students.add(S);
                System.out.println(S.getCourse());
                System.out.println(S.getCredits());
                System.out.println(S.getName());
                System.out.println(S.getSurname());
            } catch (Exception exc) {
                System.out.println("exc");
            }

        }


//        facultySelect = new Select<String>();
//        majorSelect = new Select<String>();
//        studyGroupSelect = new Select<String>();
//        yearSelect = new Select<String>();
//        facultySelect.setLabel("Faculty");
//        majorSelect.setLabel("Major");
//        studyGroupSelect.setLabel("Study Group");
//        yearSelect.setLabel("Year");

//        facultySelect.setItems(students.stream().map(student -> student.getFaculty()).distinct());
//        majorSelect.setItems(students.stream().map(student -> student.getMajor()).distinct());
//        studyGroupSelect.setItems(students.stream().map(student -> student.getStudyGroup()).distinct());
//        yearSelect.setItems(students.stream().map(student -> student.getStudyYear().toString()).distinct());
//
//
//        facultySelectListener =  facultySelect.addValueChangeListener(this::updateStudents);
//        majorSelectListener = majorSelect.addValueChangeListener(this::updateStudents);
//        studyGroupSelectListener = studyGroupSelect.addValueChangeListener(this::updateStudents);
//        yearSelectListener = yearSelect.addValueChangeListener(this::updateStudents);

//        HorizontalLayout filters = new HorizontalLayout();
//        filters.setJustifyContentMode(JustifyContentMode.CENTER);
//        filters.setWidth("100%");
//        filters.add(facultySelect, majorSelect, studyGroupSelect, yearSelect);

        grid.setItems(students);

        //add(filters);
        layout.add(grid);
        add(layout);
    }

//    private void updateStudents(AbstractField.ComponentValueChangeEvent<Select<String>, String> selectStringComponentValueChangeEvent) {
//        String faculty = facultySelect.getValue();
//        String major = majorSelect.getValue();
//        String studyGroup = studyGroupSelect.getValue();
//        String year = yearSelect.getValue();
//
//        ArrayList<Student> new_items = new ArrayList<Student>();
//
//        for (Student S : students)
//        {
//            if ( (faculty == null) || (S.getFaculty().contentEquals(faculty)))
//                if ( (major == null) || (S.getMajor().contentEquals(major)))
//                    if ( (studyGroup == null) || (S.getStudyGroup().contentEquals(studyGroup)))
//                        if ( (year == null) || (S.getStudyYear().toString().contentEquals(year)))
//                            new_items.add(S);
//        }
//
//        facultySelectListener.remove();
//        majorSelectListener.remove();
//        studyGroupSelectListener.remove();
//        yearSelectListener.remove();
//
//
//        String finalFaculty = faculty == null ? "" : faculty;
//        String finalMajor = major == null ? "" : major;
//        String finalStudyGroup = studyGroup == null ? "" : studyGroup;
//
//        majorSelect.setItems(students.stream().filter(stud ->
//                        stud.getFaculty().contentEquals(finalFaculty))
//                .map(student -> student.getMajor()).distinct());
//
//        studyGroupSelect.setItems(students.stream().filter(stud ->
//                        stud.getFaculty().contentEquals(finalFaculty) &&
//                                stud.getMajor().contentEquals(finalMajor))
//                .map(student -> student.getStudyGroup()).distinct());
//
//        yearSelect.setItems(students.stream().filter(stud ->
//                stud.getFaculty().contentEquals(finalFaculty) &&
//                        stud.getMajor().contentEquals(finalMajor) &&
//                        stud.getStudyGroup().contentEquals(finalStudyGroup)
//        ).map(student -> student.getStudyYear().toString()).distinct());
//
//        facultySelect.setValue(faculty);
//        majorSelect.setValue(major);
//        studyGroupSelect.setValue(studyGroup);
//        yearSelect.setValue(year);
//
//        facultySelectListener =  facultySelect.addValueChangeListener(this::updateStudents);
//        majorSelectListener = majorSelect.addValueChangeListener(this::updateStudents);
//        studyGroupSelectListener = studyGroupSelect.addValueChangeListener(this::updateStudents);
//        yearSelectListener = yearSelect.addValueChangeListener(this::updateStudents);
//
//        grid.setItems(new_items);
//    }
}
