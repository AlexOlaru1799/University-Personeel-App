




package com.example.application.views.Secretary;



        import com.vaadin.flow.component.Html;
        import com.vaadin.flow.component.Text;
        import com.vaadin.flow.component.button.Button;
        import com.vaadin.flow.component.button.ButtonVariant;
        import com.vaadin.flow.component.html.H2;
        import com.vaadin.flow.component.html.H3;
        import com.vaadin.flow.component.html.Image;
        import com.vaadin.flow.component.html.Paragraph;
        import com.vaadin.flow.component.orderedlayout.VerticalLayout;
        import com.vaadin.flow.router.PageTitle;
        import com.vaadin.flow.router.Route;

@PageTitle("Create Teacher Salary Certificate")
@Route(value = "createTeacherSalaryCertificate", layout = MainLayout.class)
public class CreateTeacherSalryCertificate extends VerticalLayout {

    public CreateTeacherSalryCertificate() {
        setSpacing(false);

        Image img = new Image("images/empty-plant.png", "placeholder plant");
        img.setWidth("200px");
        add(img);

        add(new H2("This place intentionally left empty"));
        add(new Paragraph("It’s a place where you can grow your own UI 🤗"));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");

    }

    private VerticalLayout createLayout(String caption) {
        VerticalLayout hl = new VerticalLayout();
        hl.setWidth("600px");
        hl.getStyle().set("background-color", "#dddddd");
        add(new Text(caption));
        add(hl);
        add(new Html("<span>&nbsp;</span>")); // spacer
        return hl;
    }

}
