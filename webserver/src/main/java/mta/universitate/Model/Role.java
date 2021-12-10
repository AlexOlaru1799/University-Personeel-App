package mta.universitate.Model;


public class Role{
    int id;
    String description;

    public Role(){}
    public Role(String description)
    {
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}


