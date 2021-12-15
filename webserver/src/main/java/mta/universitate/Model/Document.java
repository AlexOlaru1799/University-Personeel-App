package mta.universitate.Model;
import mta.universitate.Utils.JsonParser;

public class Document extends JsonParser {
    private Integer id;
    private String title;
    private String content;
    private User user;

    public static Document fromDB(int id){
        Document D = new Document();
        D.id = id;

        return Database.getInstance().get(D);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
