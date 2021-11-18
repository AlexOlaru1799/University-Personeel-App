package universitate;

public class Secretar extends Angajat{

    public Secretar()
    {
        super();
    }
    public Secretar(String _nume,String _prenume,
                   String _dataNasterii)
    {
        super(_nume,_prenume,_dataNasterii);
    }

    public void afiseazaStatistici(Student S)
    {
        System.out.print("Afisare statistici student "+
                S.getNume());
    }



    //TO DO statistici pentru grupa
//    public void afiseazaStatistici(Grupa G)
//    {
//        System.out.print("Afisare statistici student "+
//                G.getNume());
//    }

    //TO DO statistici pentru an de studiu
//    public void afiseazaStatistici(AnDeStudiu A)
//    {
//        System.out.print("Afisare statistici student "+
//                G.getNume());
//    }
}
