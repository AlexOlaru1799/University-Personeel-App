package mta.universitate.Model;

import java.util.ArrayList;

public class Specializare {
    private ArrayList<GrupaDeStudiu> grupe = new ArrayList<>();
    private ArrayList<Materie> materii = new ArrayList<>();
    private String denumire;

    public ArrayList<GrupaDeStudiu> getGrupe() {
        return grupe;
    }

    public ArrayList<Materie> getMaterii() {
        return materii;
    }

    public String getDenumire() {
        return denumire;
    }
}
