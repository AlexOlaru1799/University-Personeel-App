package mta.universitate.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

enum TipSala{
    Amfiteatru,Laborator
}

@RestController
public class SalaDeClasa {
    String denumire;
    int capacitate;
    Dotare dotari[ ];
    TipSala tipSala;



//    public SalaDeClasa(String _denumire,int _capacitate,TipSala _tip)
//    {
//        this.denumire = _denumire;
//        this.capacitate = _capacitate;
//        this.tipSala = _tip;
//    }

    @RequestMapping("/sali")
    public String getSali()
    {
        return "<h1>Salile de clasa sunt aici!!!</h1>";
    }

    public void afiseazaDenumire()
    {
        System.out.println(this.denumire);
    }

}
