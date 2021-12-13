package mta.universitate.Model;

import mta.universitate.Utils.JsonParser;

public class RequestType extends JsonParser {
    private int id;
    private String description;

    static public RequestType fromDB(int id)
    {
        RequestType RT = new RequestType();
        RT.id = id;

        return Database.getInstance().get(RT);
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
