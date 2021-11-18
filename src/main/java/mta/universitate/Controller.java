package mta.universitate;
import java.util.*;
import mta.universitate.Model.*;


public class Controller {
    private List<Worker> threadpool;
    private Queue<Request*> requests;
    public Controller(String config_file_path) {
        this.loadConfig(config_file_path);
        int ID;
        for(int i = 0; i < NB_WORKERS; i++)
            ID = 1; // TODO: get unique ID
            threadpool.add(new Worker(ID, &this.requests));
            threadpool.get(i).start();
    }

    private void loadConfig(String config_file_path){}
}
