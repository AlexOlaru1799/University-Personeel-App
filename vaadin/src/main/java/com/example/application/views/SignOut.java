

package com.example.application.views;

        import com.example.application.views.MainLayout;
        import com.vaadin.flow.component.button.Button;
        import com.vaadin.flow.component.html.H1;
        import com.vaadin.flow.component.html.H2;
        import com.vaadin.flow.component.html.Image;
        import com.vaadin.flow.component.html.Paragraph;
        import com.vaadin.flow.component.notification.Notification;
        import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
        import com.vaadin.flow.component.orderedlayout.VerticalLayout;
        import com.vaadin.flow.component.textfield.TextField;
        import com.vaadin.flow.router.PageTitle;
        import com.vaadin.flow.router.Route;
        import com.vaadin.flow.router.RouteAlias;

@PageTitle("Sign Out")
@Route(value = "signOut", layout = MainLayout.class)

public class SignOut extends VerticalLayout {

    private H1 text;
    private Button leave;

    public SignOut() {
        text = new H1("Do you wish to leave?");
        leave = new Button("Leave");
        add(text,leave);
        leave.addClickListener(e -> {
            getUI().ifPresent(ui ->ui.navigate(""));
        });



    }

}
