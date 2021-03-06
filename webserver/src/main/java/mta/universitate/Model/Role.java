package mta.universitate.Model;


import mta.universitate.Utils.JsonParser;

public class Role extends JsonParser {
    Integer id;
    String description;

    public Role(){}
    public Role(int id, String description)
    {
        this.id = id;
        this.description = description;
    }

    static public Role fromDB(int id)
    {
        Role R = new Role();
        R.id = id;

        return Database.getInstance().get(R);
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "\"Role [id=%d, description='%s']".formatted(id, description);

    }
}


