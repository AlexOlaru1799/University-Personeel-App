package mta.universitate.Model;

import mta.universitate.Utils.JsonParser;

public class Faculty extends JsonParser {
    private Integer id;
    private String name;

    public Faculty(){}
    public Faculty(int id, String name){
        this.id = id;
        this.name = name;
    }

    public static Faculty fromDB(int id)
    {
        Faculty F = new Faculty();
        F.id = id;

        return Database.getInstance().get(F);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
