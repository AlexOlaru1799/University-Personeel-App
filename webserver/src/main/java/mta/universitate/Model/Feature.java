package mta.universitate.Model;

public class Feature {
    private int id;
    private String description;

    public static Feature fromDB(int id)
    {
        Feature F = new Feature();
        F.id = id;

        return Database.getInstance().get(F);
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
