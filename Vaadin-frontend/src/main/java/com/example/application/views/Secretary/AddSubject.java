
package com.example.application.views.Secretary;



import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Add Subject")
@Route(value = "addSubject", layout = MainLayout.class)
public class AddSubject extends VerticalLayout {

    public AddSubject() {
        VerticalLayout mainLayout = createLayout("Insert the necessary info");

        IntegerField credits = new IntegerField("Subject credits");
        credits.setWidth("500px");
        IntegerField idTeacher = new IntegerField("Teacher ID");
        idTeacher.setWidth("500px");
        TextField name = new TextField("Subject name");
        name.setWidth("500px");

        Select<String> specialization = new Select<String>();
        specialization.setLabel("Select Specialization");
        specialization.setItems("Calculatoare", "AVMO", "BAT", "Comunicatii", "ESEM");
        specialization.setValue("Calculatoare");


        Button addSubject = new Button("Add Subject");
        addSubject.setWidth("500px");

        mainLayout.add(name,credits,idTeacher,specialization,addSubject);
        add(mainLayout);


        addSubject.addClickListener(e -> {
            String nameS = name.getValue();
            String specializationS = specialization.getValue();
            Integer idT = idTeacher.getValue();
            Integer creditsS = credits.getValue();




            name.clear();
            //surname.clear();
            //salary.clear();



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
