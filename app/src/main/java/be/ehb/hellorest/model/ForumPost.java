package be.ehb.hellorest.model;

/**
 * Created by Q on 14-3-2018.
 */

public class ForumPost {

    private int id, userId;
    private String title, body;

    public ForumPost(int id, int userId, String title, String body) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return title;
    }
}
