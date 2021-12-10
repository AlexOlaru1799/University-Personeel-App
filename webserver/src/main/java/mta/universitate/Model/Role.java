package mta.universitate.Model;


public class Role{
    int id;
    String type;

    public Role()
    {}
    public Role(String type){
        this.type = type;
    }

    static public Role fromDB(int id)
    {
        Role R = new Role();
        R.id = id;

        return Database.getInstance().get(R);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}


