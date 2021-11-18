package universitate;


public abstract class Persoana implements IPersoana {
    private String nume;
    private String prenume;
    private String dataNasterii;
    private int indemnizatie;

    public Persoana()
    {

    }
    public Persoana(String _nume,String _prenume,
                    String _dataNasterii)
    {
        nume=_nume;
        prenume=_prenume;
        dataNasterii=_dataNasterii;
    }

    public String getNume()
    {
        return nume;
    }

    public String getPrenume()
    {
        return prenume;
    }

    public String getDataNasterii()
    {
        return dataNasterii;
    }
}
