package mta.universitate.Model;

import mta.universitate.Utils.JsonParser;

public class RequestType extends JsonParser {
    private Integer id;
    private String description;

    static public RequestType fromDB(int id)
    {
        RequestType RT = new RequestType();
        RT.id = id;

        return Database.getInstance().get(RT);
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
}
