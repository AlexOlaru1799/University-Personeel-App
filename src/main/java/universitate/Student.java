package universitate;

public class Student extends Persoana{
    private int numarMatricol;

    //To do adaugare grupa aici
    //private Grupa grupa;

    public Student(String _nume,String _prenume,
                   String _dataNasterii)
    {
        super(_nume,_prenume,_dataNasterii);
    }

    @Override
    public void adaugaPersoana(Persoana P) {
        System.out.print("adaugaPersoana din Student");
    }

    @Override
    public void adaugaDocument(){
        System.out.print("adaugaDocument din Student");
    }

    public void solicitaRaport()
    {
        System.out.print("solicitaRaport din Student");
    }

}
