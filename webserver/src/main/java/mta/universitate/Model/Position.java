package mta.universitate.Model;

public class Position {
    private int id;
    private String description;

    public static Position fromDB(int id){
        Position P = new Position();
        P.setId(id);

        return Database.getInstance().get(P);
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
