
package com.example.application.views.Secretary;



import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("View Courses")
@Route(value = "viewCourses", layout = MainLayout.class)
public class ViewCourses extends VerticalLayout {

    public ViewCourses() {
        VerticalLayout mainLayout = createLayout("Insert the necessary info");

        TextField specialization = new TextField("Specialization");
        specialization.setWidth("500px");



        Button seeSpecializationCourses = new Button("See Courses");
        seeSpecializationCourses.setWidth("500px");

        mainLayout.add(specialization,seeSpecializationCourses);
        add(mainLayout);


        seeSpecializationCourses.addClickListener(e -> {
            String nameS = specialization.getValue();


//            Professor professor = new Professor(nameS,surnameS);
//            professor.setSalary(salaryS);
//
//
//
//
//
//            Database DB = Database.getInstance();
//
//            try {
//                DB.createTeacher(professor);
//            } catch (NoSuchAlgorithmException ex) {
//                ex.printStackTrace();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }
//
//
//            name.clear();
//            surname.clear();
//            salary.clear();

            Notification.show("Professor was added to the database!");

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
