package mta.universitate.Model;

public class Faculty {
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
