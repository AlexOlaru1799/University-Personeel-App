
package com.example.application.views;
     // To do modify for the current project


import com.example.application.views.BackEnd.Database;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;;import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@PageTitle("View Teacher")
@Route(value = "viewTeacher", layout = MainLayout.class)
public class ViewTeacher extends VerticalLayout {

    private TextField name;
    private Button searchButton;

    public ViewTeacher() {
        name = new TextField("Teacher ID");
        searchButton = new Button("Search");

        setSpacing(true);
        setPadding(true);

        H5 nume = new H5("");
        H5 prenume = new H5("");
        H5 salariu = new H5("");
        H5 id = new H5("");

        H5 username = new H5("");



        add(new H3("Search the teacher using his ID"));

        VerticalLayout layout = createLayout("Enter the ID here");
        layout.setPadding(true);
        layout.add(name,searchButton);


        layout = createLayout("Teacher info : ");
        layout.setPadding(true);
        layout.setHeight("300px");
        layout.add(id,nume,prenume,salariu,username);
        layout.getStyle().set("overflow", "scroll");// enable scrolling when content doesn't fit


        layout = createLayout("Teacher classes : ");
        layout.setPadding(true);
        layout.setHeight("500px");
        layout.getStyle().set("overflow", "scroll");// enable scrolling when content doesn't fit

        List<H5> materii = new ArrayList<H5>(50);
        List<H5> tip = new ArrayList<H5>(50);
        List<H5> ora = new ArrayList<H5>(50);
        List<H5> grupa = new ArrayList<H5>(50);
        List<H5> sala = new ArrayList<H5>(50);

        H3 titluM = new H3("Materie");
        H3 titluT = new H3("Tip");
        H3 tittluO = new H3("Ora");
        H3 titluG = new H3("Grupa");
        H3 titluS = new H3("Sala");



        HorizontalLayout titles = new HorizontalLayout(titluM,titluT,tittluO,titluG,titluS);
        layout.add(titles);

        List<HorizontalLayout> laySS = new ArrayList<HorizontalLayout>(50);

        for(int i =0;i<50;i++)
        {

            materii.add(new H5(""));
            tip.add(new H5(""));
            ora.add(new H5(""));
            grupa.add(new H5(""));
            sala.add(new H5(""));

            laySS.add(new HorizontalLayout(materii.get(i),tip.get(i),ora.get(i),grupa.get(i),sala.get(i)));

            //laySS.add(materii.get(i),tip.get(i),ora.get(i),grupa.get(i),sala.get(i));

            layout.add(laySS.get(i));
        }




        var ref = new Object() {
            int counter = 0;
        };



        searchButton.addClickListener(e -> {

            nume.setText("");
            prenume.setText("");
            id.setText("");
            salariu.setText("");

            username.setText("");

            for(int i =0;i<50;i++) {

                materii.get(i).setText("");
                tip.get(i).setText("");
                ora.get(i).setText("");
                grupa.get(i).setText("");
                sala.get(i).setText("");

            }

            ref.counter=0;

            String ID = name.getValue();

            Database DB = Database.getInstance();

            ResultSet res = DB.getProfessorInfo(ID);
            ResultSet res2 = DB.getProfessorInfo(ID);

            ResultSet res3 = DB.getProfessorClasses(ID);



            try {
                if(!res2.next())
                {
                    Notification.show("Teacher with ID:" + ID + " does not exist in the database!");
                    nume.setText("Teacher with ID:" + ID + " does not exist in the database!");
                }
                else
                {
                    try {
                        while (res.next()) {


                            id.setText("ID : "+ res.getString("ID") );
                            nume.setText("Nume : "+res.getString("NUME") );
                            prenume.setText("Prenume : "+res.getString("PRENUME") );
                            salariu.setText("Salariu : "+res.getString("SALARIU") );
                            username.setText("Username : "+res.getString("USERNAME") );
                        }

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    try {

                        while (res3.next()) {


                            materii.get(ref.counter).setText(res3.getString("MATERIE"));
                            tip.get(ref.counter).setText(res3.getString("TIP"));
                            ora.get(ref.counter).setText(res3.getString("ORA"));
                            grupa.get(ref.counter).setText(res3.getString("GRUPA"));
                            sala.get(ref.counter).setText(res3.getString("DENUMIRE"));



                            ref.counter++;


                        }


                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }


        });



        //add(name, sayHello);




    }

    private VerticalLayout createLayout(String caption) {
        VerticalLayout hl = new VerticalLayout();
        hl.setWidth("600px");
        hl.getStyle().set("background-color", "#e8ebef");
        add(new H4(caption));
        add(hl);
        add(new Html("<span>&nbsp;</span>")); // spacer
        return hl;
    }

}
