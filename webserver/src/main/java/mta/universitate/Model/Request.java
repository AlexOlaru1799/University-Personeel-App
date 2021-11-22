package mta.universitate.Model;

abstract class Request implements iRequest{
    private int ID;
    private RequestType type;

    public int getID() { return ID;}
    public abstract void solve();
}
