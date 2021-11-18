package universitate;

public class Profesor extends Angajat{

    public Profesor()
    {
        super();
    }
    public Profesor(String _nume,String _prenume,
                   String _dataNasterii)
    {
        super(_nume,_prenume,_dataNasterii);
    }

    public void acordaNota(Student S,int nota)
    {
        System.out.print("Profesor adauga nota" +
                nota + "studentului " +S.getNume());
    }

}
