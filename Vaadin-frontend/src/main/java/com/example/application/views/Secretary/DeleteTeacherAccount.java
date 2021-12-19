
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

@PageTitle("Delete Teacher Account")
@Route(value = "deleteTeacherAccount", layout = MainLayout.class)
public class DeleteTeacherAccount extends VerticalLayout {

    public DeleteTeacherAccount() {
        VerticalLayout mainLayout = createLayout("Insert the necessary info");

        TextField name = new TextField("Teacher name");
        name.setWidth("500px");
        TextField surname = new TextField("Teacher surname");
        surname.setWidth("500px");


        Button addStudent = new Button("Delete Teacher");
        addStudent.setWidth("500px");

        mainLayout.add(name,surname,addStudent);
        add(mainLayout);


        addStudent.addClickListener(e -> {
            String nameS = name.getValue();
            String surnameS = surname.getValue();
            ApiRequest req = new ApiRequest("http://localhost:8080/secretary/delete-professor");

            req.addParameter("name",nameS);
            req.addParameter("surname",surnameS);


            req.addCookie(OwnCookieManager.getInstance().getCookie());


            // Send the request and get the response
            HashMap<String, Object> response = req.send();

            if(response.get("status").equals("SUCCESS")) {

                Notification.show("Teacher with name: " + nameS + " " + surnameS + " has been deleted");
            }
            else{
                Notification.show("There is no Teacher with this name!");
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
