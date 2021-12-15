package mta.universitate.Model;

import mta.universitate.Utils.JsonParser;

import java.util.List;


public class Classroom extends JsonParser {
    Integer id;
    Integer capacity;
    private String name;
    private boolean kind;
    private List<Feature> features;


    public static Classroom fromDB(int id)
    {
        Classroom C = new Classroom();
        C.id = id;

        return Database.getInstance().get(C);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isKind() {
        return kind;
    }

    public void setKind(boolean kind) {
        this.kind = kind;
    }
}
