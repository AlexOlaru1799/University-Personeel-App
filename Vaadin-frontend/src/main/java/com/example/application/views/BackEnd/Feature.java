package com.example.application.views.BackEnd;

public class Feature {
    String description;

    public Feature(String description)
    {
        this.description = description;
    }


    public void show()
    {
        System.out.println(this.description);
    }

}
