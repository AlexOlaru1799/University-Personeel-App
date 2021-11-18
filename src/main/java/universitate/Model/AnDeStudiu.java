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
}
