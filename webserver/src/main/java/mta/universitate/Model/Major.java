package mta.universitate.Model;
import mta.universitate.Utils.JsonParser;

public class Major extends JsonParser {
    private Integer id;
    private String name;
    private Faculty faculty;
    private Secretary secretary;

    public Major(){}
    public Major(int id, String name, Faculty faculty, Secretary secretary){
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.secretary = secretary;
    }

    public static Major fromDB(int id)
    {
        Major M = new Major();
        M.id = id;

        return Database.getInstance().get(M);
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

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Secretary getSecretary() {
        return secretary;
    }

    public void setSecretary(Secretary secretary) {
        this.secretary = secretary;
    }
}
