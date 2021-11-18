package mta.universitate.Model;

public class Worker extends Thread{
    private int ID;
    private Queue<Request*> *requests;
    public Worker(int ID, Queue<Request*> *requests ){
        this.ID = ID;
        this.requests = requests;
    }
    public void run(){
        // TODO: While True -> solve requests -- be careful to use mutex and cond variable
    }
}
