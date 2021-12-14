package mta.universitate.Model;

import mta.universitate.Utils.JsonParser;

public class Faculty extends JsonParser {
    private int id;
    private String name;

    public static Faculty fromDB(int id)
    {
        Faculty F = new Faculty();
        F.id = id;

        return Database.getInstance().get(F);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
