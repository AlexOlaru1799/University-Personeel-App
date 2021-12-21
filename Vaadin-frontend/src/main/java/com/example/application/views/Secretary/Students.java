
package com.example.application.views.Secretary;

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

import com.example.application.components.Student;

import java.util.ArrayList;
import java.util.HashMap;

@PageTitle("Students")
@Route(value = "Students", layout = MainLayout.class)
public class Students extends VerticalLayout {
    private ArrayList<Student> students;
    private Grid<Student> grid;
    private Select<String> facultySelect;
    private Select<String> majorSelect;
    private Select<String> studyGroupSelect;
    private Select<String> yearSelect;

    private Registration facultySelectListener;
    private Registration majorSelectListener;
    private Registration studyGroupSelectListener;
    private Registration yearSelectListener;


    public Students() {
        VerticalLayout layout = new VerticalLayout();
        layout.getStyle().set("background-color", "#e8ebef");

        grid = new Grid<Student>();
        grid.addColumn(Student::getName).setHeader("Name").setSortable(true);
        grid.addColumn(Student::getSurname).setHeader("Surname").setSortable(true);
        grid.addColumn(Student::getFaculty).setHeader("Faculty").setSortable(true);
        grid.addColumn(Student::getMajor).setHeader("Major").setSortable(true);
        grid.addColumn(Student::getStudyGroup).setHeader("Study Group").setSortable(true);
        grid.addColumn(Student::getStudyYear).setHeader("Year").setSortable(true);
        grid.addColumn(Student::getIncome).setHeader("Income").setSortable(true);

        ApiRequest req = new ApiRequest("http://localhost:8080/secretary/view-students");
        req.addCookie(OwnCookieManager.getInstance().getCookie());
        HashMap<String, Object> response = req.send();
        ArrayList<Object> objects = new ArrayList<Object>(response.values());

        ArrayList<Object> studs_json = (ArrayList<Object>) objects.get(0);

        students = new ArrayList<Student>(studs_json.size());

        for (Object stud_json : studs_json) {
            Student S = new Student();
            try {
                JSONObject obj = new JSONObject(stud_json.toString());
                S.setName(obj.get("name").toString());
                S.setSurname(obj.get("surname").toString());
                S.setIncome(Integer.parseInt(obj.get("income").toString()));
                S.setFaculty(obj.getJSONObject("major").getJSONObject("faculty").get("name").toString());
                S.setMajor(obj.getJSONObject("major").get("name").toString());
                S.setStudyGroup(obj.getJSONObject("studyGroup").get("name").toString());
                S.setStudyYear(Integer.parseInt(obj.getJSONObject("studyGroup").get("study_year").toString()));

                students.add(S);
            } catch (Exception exc) {}

        }


        facultySelect = new Select<String>();
        majorSelect = new Select<String>();
        studyGroupSelect = new Select<String>();
        yearSelect = new Select<String>();
        facultySelect.setLabel("Faculty");
        majorSelect.setLabel("Major");
        studyGroupSelect.setLabel("Study Group");
        yearSelect.setLabel("Year");

        facultySelect.setItems(students.stream().map(student -> student.getFaculty()).distinct());
        majorSelect.setItems(students.stream().map(student -> student.getMajor()).distinct());
        studyGroupSelect.setItems(students.stream().map(student -> student.getStudyGroup()).distinct());
        yearSelect.setItems(students.stream().map(student -> student.getStudyYear().toString()).distinct());


        facultySelectListener =  facultySelect.addValueChangeListener(this::updateStudents);
        majorSelectListener = majorSelect.addValueChangeListener(this::updateStudents);
        studyGroupSelectListener = studyGroupSelect.addValueChangeListener(this::updateStudents);
        yearSelectListener = yearSelect.addValueChangeListener(this::updateStudents);

        HorizontalLayout filters = new HorizontalLayout();
        filters.setJustifyContentMode(JustifyContentMode.CENTER);
        filters.setWidth("100%");
        filters.add(facultySelect, majorSelect, studyGroupSelect, yearSelect);

        grid.setItems(students);

        add(filters);
        layout.add(grid);
        add(layout);
    }

    private void updateStudents(AbstractField.ComponentValueChangeEvent<Select<String>, String> selectStringComponentValueChangeEvent) {
        String faculty = facultySelect.getValue();
        String major = majorSelect.getValue();
        String studyGroup = studyGroupSelect.getValue();
        String year = yearSelect.getValue();

        ArrayList<Student> new_items = new ArrayList<Student>();

        for (Student S : students)
        {
            if ( (faculty == null) || (S.getFaculty().contentEquals(faculty)))
                if ( (major == null) || (S.getMajor().contentEquals(major)))
                    if ( (studyGroup == null) || (S.getStudyGroup().contentEquals(studyGroup)))
                        if ( (year == null) || (S.getStudyYear().toString().contentEquals(year)))
                            new_items.add(S);
        }

        facultySelectListener.remove();
        majorSelectListener.remove();
        studyGroupSelectListener.remove();
        yearSelectListener.remove();


        String finalFaculty = faculty == null ? "" : faculty;
        String finalMajor = major == null ? "" : major;
        String finalStudyGroup = studyGroup == null ? "" : studyGroup;

        majorSelect.setItems(students.stream().filter(stud ->
                stud.getFaculty().contentEquals(finalFaculty))
                .map(student -> student.getMajor()).distinct());

        studyGroupSelect.setItems(students.stream().filter(stud ->
                stud.getFaculty().contentEquals(finalFaculty) &&
                        stud.getMajor().contentEquals(finalMajor))
                .map(student -> student.getStudyGroup()).distinct());

        yearSelect.setItems(students.stream().filter(stud ->
                stud.getFaculty().contentEquals(finalFaculty) &&
                        stud.getMajor().contentEquals(finalMajor) &&
                        stud.getStudyGroup().contentEquals(finalStudyGroup)
        ).map(student -> student.getStudyYear().toString()).distinct());

        facultySelect.setValue(faculty);
        majorSelect.setValue(major);
        studyGroupSelect.setValue(studyGroup);
        yearSelect.setValue(year);

        facultySelectListener =  facultySelect.addValueChangeListener(this::updateStudents);
        majorSelectListener = majorSelect.addValueChangeListener(this::updateStudents);
        studyGroupSelectListener = studyGroupSelect.addValueChangeListener(this::updateStudents);
        yearSelectListener = yearSelect.addValueChangeListener(this::updateStudents);

        grid.setItems(new_items);
    }
}
