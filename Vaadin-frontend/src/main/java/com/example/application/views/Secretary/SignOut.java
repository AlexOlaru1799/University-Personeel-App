

package com.example.application.views.Secretary;

        import com.vaadin.flow.component.button.Button;
        import com.vaadin.flow.component.html.H1;
        import com.vaadin.flow.component.orderedlayout.VerticalLayout;
        import com.vaadin.flow.router.PageTitle;
        import com.vaadin.flow.router.Route;

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
