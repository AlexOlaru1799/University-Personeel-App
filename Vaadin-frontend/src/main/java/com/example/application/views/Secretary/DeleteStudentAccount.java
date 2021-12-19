
package com.example.application.views.Secretary;



        import com.example.application.views.Utils.ApiRequest;
        import com.example.application.views.Utils.OwnCookieManager;
        import com.vaadin.flow.component.Html;
        import com.vaadin.flow.component.button.Button;
        import com.vaadin.flow.component.html.H3;
        import com.vaadin.flow.component.notification.Notification;
        import com.vaadin.flow.component.orderedlayout.VerticalLayout;
        import com.vaadin.flow.component.textfield.TextField;
        import com.vaadin.flow.router.PageTitle;
        import com.vaadin.flow.router.Route;

        import java.util.HashMap;

@PageTitle("Delete Student Account")
@Route(value = "deleteStudentAccount", layout = MainLayout.class)
public class DeleteStudentAccount extends VerticalLayout {

    public DeleteStudentAccount() {
        VerticalLayout mainLayout = createLayout("Insert the necessary info");

        TextField name = new TextField("Student name");
        name.setWidth("500px");
        TextField surname = new TextField("Student surname");
        surname.setWidth("500px");


        Button addStudent = new Button("Delete Student");
        addStudent.setWidth("500px");

        mainLayout.add(name,surname,addStudent);
        add(mainLayout);


        addStudent.addClickListener(e -> {
            String nameS = name.getValue();
            String surnameS = surname.getValue();

            ApiRequest req = new ApiRequest("http://localhost:8080/secretary/delete-student");

            req.addParameter("name",nameS);
            req.addParameter("surname",surnameS);


            req.addCookie(OwnCookieManager.getInstance().getCookie());


            // Send the request and get the response
            HashMap<String, Object> response = req.send();

            if(response.get("status").equals("SUCCESS")) {

                Notification.show("Student with name: " + nameS + " " + surnameS + " has been deleted");
            }
            else{
                Notification.show("There is no student with this name!");
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
