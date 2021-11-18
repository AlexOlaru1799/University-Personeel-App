package universitate.Model;

import java.util.ArrayList;

public class AnDeStudiu {

    int An;
    ArrayList<Specializare> specializari = new ArrayList<>();

    public int getAn()
    {
        return this.An;
    }

    public ArrayList<Specializare> getSpecializari() {
        return this.specializari;
    }

    public String AfisareSpecializari(){
        for(int i=0;i<this.specializari.size();i++)
        {
            return ("Specializarea: " + this.getSpecializari().get(i));
        }
    }
}
