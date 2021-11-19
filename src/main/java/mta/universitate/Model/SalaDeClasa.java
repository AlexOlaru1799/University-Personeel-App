package mta.universitate.Model;

enum TipSala{
    Amfiteatru,Laborator
}

public class SalaDeClasa {
    String denumire;
    int capacitate;
    Dotare dotari[ ];
    TipSala tipSala;

    public SalaDeClasa(String _denumire,int _capacitate,TipSala _tip)
    {
        this.denumire = _denumire;
        this.capacitate = _capacitate;
        this.tipSala = _tip;
    }

    public void afiseazaDenumire()
    {
        System.out.println(this.denumire);
    }

}
