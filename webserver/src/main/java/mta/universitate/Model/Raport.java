package mta.universitate.Model;

enum RaportType {
    Adeverinta,Permisie,SchimbareGrupa
}


public class Raport {
    String descriere;
    RaportType tipRaport;

    public Raport(String _descriere,RaportType _tipRaport)
    {
        this.descriere = _descriere;
        this.tipRaport = _tipRaport;
    }

    public String getDescriere()
    {
        return this.descriere;
    }

    public String getTipRaport()
    {
        if(this.tipRaport == RaportType.Adeverinta)
        {
            return "Adeverinta";
        }
        else if(this.tipRaport == RaportType.SchimbareGrupa)
        {
            return  "Schimbare grupa";
        }
        else
        {
            return "Permisie";
        }
    }


    public void afiseazaRaport()
    {
        if(this.tipRaport == RaportType.Adeverinta)
        {
            System.out.println("Adeverinta : ");
        }
        else if(this.tipRaport == RaportType.Permisie)
        {
            System.out.println("Permisie : ");
        }
        else
        {
            System.out.println("Schimbare Grupa : ");
        }

        System.out.println("\n" +  this.descriere);

    }




}
