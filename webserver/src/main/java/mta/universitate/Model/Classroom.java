package mta.universitate.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import mta.universitate.Utils.JsonParser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;


public class Classroom extends JsonParser {
    int id;
    int capacity;
    private String name;
    private boolean kind;
    private List<Feature> features;


    public static Classroom fromDB(int id)
    {
        Classroom C = new Classroom();
        C.id = id;

        return Database.getInstance().get(C);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
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
