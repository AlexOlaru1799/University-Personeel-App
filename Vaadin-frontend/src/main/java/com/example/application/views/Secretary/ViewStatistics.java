
package com.example.application.views.Secretary;



import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("View Students Statistics")
@Route(value = "viewStatistics", layout = MainLayout.class)
public class ViewStatistics extends VerticalLayout {

    public ViewStatistics() {
        VerticalLayout mainLayout = createLayout("See Student Statistics");

        TextField name = new TextField("Student name");
        name.setWidth("500px");
        TextField surname = new TextField("Student surname");
        surname.setWidth("500px");


        Button addStudent = new Button("See Statistics");
        addStudent.setWidth("500px");

        mainLayout.add(name,surname,addStudent);
        add(mainLayout);

        VerticalLayout layout = createLayout("");
        layout.setPadding(true);
        layout.setHeight("500px");
        layout.getStyle().set("overflow", "scroll");// enable scrolling when content doesn't fit

        add(layout);



        VerticalLayout layout2 = createLayout("See Group Statistics");

        TextField groupID = new TextField("Group ID");
        name.setWidth("500px");

        Button seeGroupStatistics = new Button("See Statistics");
        addStudent.setWidth("500px");

        layout2.add(groupID,seeGroupStatistics);

        add(layout2);

        VerticalLayout layout3 = createLayout("");
        layout3.setPadding(true);
        layout3.setHeight("500px");
        layout3.getStyle().set("overflow", "scroll");// enable scrolling when content doesn't fit

        add(layout3);


        addStudent.addClickListener(e -> {
            String nameS = name.getValue();
            String surnameS = surname.getValue();

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
