package universitate;

public class Administrator extends Angajat{

    public Administrator()
    {
        super();
    }
    public Administrator(String _nume,String _prenume,
                   String _dataNasterii)
    {
        super(_nume,_prenume,_dataNasterii);
    }

    public void afiseazaProfesori()
    {
        System.out.print("Afisare profesori");
    }

    public void afiseazaMaterii()
    {
        System.out.print("Afisare materii");
    }

    public void stergePersoana(Persoana P)
    {
        System.out.print("Sterg persoana " + P.getNume());
    }

    public void afiseazaStudent(Student S)
    {
        System.out.print("Afisez student " + S.getNume());
    }
}
