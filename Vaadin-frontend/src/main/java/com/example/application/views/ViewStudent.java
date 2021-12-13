package com.example.application.views;

import com.example.application.views.BackEnd.Database;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


import java.sql.ResultSet;
import java.sql.SQLException;

@PageTitle("View Student")
@Route(value = "viewStudent", layout = MainLayout.class)

public class ViewStudent extends VerticalLayout {

    private TextField name;
    private Button searchButton;


    public ViewStudent() {
        name = new TextField("Student ID");
        searchButton = new Button("Search");

        setSpacing(true);
        setPadding(true);

        H5 nume = new H5("");
        H5 prenume = new H5("");
        H5 grupa = new H5("");
        H5 denumire = new H5("");
        H5 an = new H5("");
        H5 username = new H5("");


        add(new H3("Search the student using his ID"));

        VerticalLayout layout = createLayout("Enter the ID here");
        layout.setPadding(true);
        layout.add(name,searchButton);


        layout = createLayout("Student info : ");
        layout.setPadding(true);
        layout.setHeight("300px");
        layout.add(nume,prenume,grupa,denumire,an,username);
        layout.getStyle().set("overflow", "scroll");// enable scrolling when content doesn't fit

        layout = createLayout("Student grades : ");
        layout.setPadding(true);
        layout.setHeight("500px");
        layout.getStyle().set("overflow", "scroll");// enable scrolling when content doesn't fit

        H3 temp = new H3("");

        HorizontalLayout layH = new HorizontalLayout();

        H3 materie = new H3("Materie");
        H3 nota = new H3("   Nota");
        H3 data = new H3("   Data");

        layH.add(materie,nota,data);

        HorizontalLayout layH2 = new HorizontalLayout();

        H5 materie2 = new H5("");
        H5 nota2 = new H5("");
        H5 data2 = new H5("");

        layH2.add(materie2,nota2,data2);

        HorizontalLayout layH3 = new HorizontalLayout();

        H5 materie3 = new H5("");
        H5 nota3 = new H5("");
        H5 data3 = new H5("");

        layH3.add(materie3,nota3,data3);

        HorizontalLayout layH4 = new HorizontalLayout();

        H5 materie4 = new H5("");
        H5 nota4 = new H5("");
        H5 data4 = new H5("");

        layH4.add(materie4,nota4,data4);

        HorizontalLayout layH5 = new HorizontalLayout();

        H5 materie5 = new H5("");
        H5 nota5 = new H5("");
        H5 data5 = new H5("");

        layH5.add(materie5,nota5,data5);




        layout.add(layH);
        layout.add(layH2);
        layout.add(layH3);
        layout.add(layH4);
        layout.add(layH5);












        searchButton.addClickListener(e -> {

            nume.setText("");
            prenume.setText("");
            denumire.setText("");
            grupa.setText("");
            an.setText("");
            username.setText("");
            materie2.setText("");
            materie3.setText("");
            materie4.setText("");
            materie5.setText("");
            nota2.setText("");
            nota3.setText("");
            nota4.setText("");
            nota5.setText("");
            data2.setText("");
            data3.setText("");
            data4.setText("");
            data5.setText("");



            String ID = name.getValue();

            Database DB = Database.getInstance();

            ResultSet res = DB.getStudentInfo(ID);
            ResultSet res2 = DB.getStudentInfo(ID);

            ResultSet res3 = DB.getStudentGrades(ID);

            try {
                if(!res2.next())
                {
                    Notification.show("Student with ID:" + ID + " does not exist in the database!");
                    nume.setText("Student with ID:" + ID + " does not exist in the database!");
                }
                else
                {
                    try {
                        while (res.next()) {


                            nume.setText("Nume : "+ res.getString("NUME") );
                            prenume.setText("Prenume : "+res.getString("PRENUME") );
                            denumire.setText("Denumire : "+res.getString("DENUMIRE") );
                            grupa.setText("Grupa : "+res.getString("GRUPA") );
                            an.setText("An : "+res.getString("AN") );
                            username.setText("Username : "+res.getString("USERNAME") );
                        }

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    try {
                        int counter = 0;
                        while (res3.next()) {

                            //temp.setText(res3.getString("MATERIE"));
                            if(counter == 0)
                            {
                                materie2.setText(res3.getString("MATERIE"));
                                nota2.setText(res3.getString("VALOARE"));
                                data2.setText(res3.getString("DATA"));


                            }
                            else if(counter == 1)
                            {
                                materie3.setText(res3.getString("MATERIE"));
                                nota3.setText(res3.getString("VALOARE"));
                                data3.setText(res3.getString("DATA"));
                            }
                            else if(counter == 2)
                            {
                                materie4.setText(res3.getString("MATERIE"));
                                nota4.setText(res3.getString("VALOARE"));
                                data4.setText(res3.getString("DATA"));
                            }
                            else if(counter == 3)
                            {
                                materie5.setText(res3.getString("MATERIE"));
                                nota5.setText(res3.getString("VALOARE"));
                                data5.setText(res3.getString("DATA"));
                            }

                            counter++;


                        }


                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }


        });

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
