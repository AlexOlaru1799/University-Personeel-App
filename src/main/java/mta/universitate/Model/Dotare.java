package mta.universitate.Model;

public class Dotare {
    String denumire;

    public Dotare(String _denumire)
    {
        this.denumire = _denumire;
    }


    public void afiseazaDenumire()
    {
        System.out.println(this.denumire);
    }

}
