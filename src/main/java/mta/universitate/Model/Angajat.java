package mta.universitate.Model;

public class Angajat extends Persoana{
    private Functie functie;

    public Angajat()
    {
        super();
    }
    public Angajat(String _nume,String _prenume,
                   String _dataNasterii)
    {
        super(_nume,_prenume,_dataNasterii);
    }


    //TO DO Grupa
    //private Grupa grupa

    @Override
    public void adaugaPersoana(Persoana P) {
        System.out.print("adaugaPersoana");
    }

    @Override
    public void adaugaDocument() {
        System.out.print("adaugaDocument");
    }

    //TO DO --> parametru anDeStudiu
    public void afiseazaNote()
    {

    }

    //TO DO --> parametru Grupa
//    public void afiseazaNote(Grupa G)
//    {
//
//    }

    public void afiseazaNote(Student S)
    {

    }
}
