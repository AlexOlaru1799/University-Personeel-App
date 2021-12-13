package mta.universitate.Model;


import com.fasterxml.jackson.core.JsonFactory;
import mta.universitate.Utils.JsonParser;

public class Role extends JsonParser {
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

    @Override
    public String toString() {
        return "\"Role [id=%d, description='%s']".formatted(id, description);

    }
}


