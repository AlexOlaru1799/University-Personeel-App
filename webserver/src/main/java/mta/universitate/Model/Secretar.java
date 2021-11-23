package mta.universitate.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Clasa Secretar
 *
 * Clasa utilizata pentru a implementa utilitatile secretarului
 *
 */
@RestController
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

    @RequestMapping("/studenti")
    public String afiseazaStudenti()
    {

        return "<table border >" +
                "  <tr>" +
                "    <th>Grupa</th>" +
                "    <th>Nume</th>" +
                "    <th>Prenume</th>" +
                "  </tr>" +
                "  <tr>" +
                "    <td>C114C</td>" +
                "    <td>Marian</td>" +
                "    <td>Razvan</td>" +
                "  </tr>" +
                "  <tr>" +
                "    <td>C114C</td>" +
                "    <td>Chiforiuc</td>" +
                "    <td>Gabriela</td>" +
                "  </tr>" +
                "</table>";
    }


    public void afiseazaStatistici(Student S)
    {
        System.out.print("Afisare statistici student "+
                S.getNume());
    }


    public void afiseazaStatistici(GrupaDeStudiu G)
    {
        System.out.print("Afisare gurpa de studiu");
    }

    //TO DO statistici pentru an de studiu
    public void afiseazaStatistici(AnDeStudiu A)
    {
        System.out.print("An "+
                A.getAn());
    }
}
