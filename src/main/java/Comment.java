import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Comment
{
    private int id;
    private String text;
    private Date date;
    private User user;
    private Post post;

    /**
     * Constructor for objects of class Comment
     */
    public Comment(int id, String text, Date date, User user , Post post)
    {
        this.id = id;
        this.text = text;
        this.date = date;
        this.user  = user;
        this.post = post;
    }
    public Comment(String text, Date date, User user , Post post)
    {
        this.id = -1;
        this.text = text;
        this.date = date;
        this.user  = user;
        this.post = post;
    }
    public Comment()
    {
        this(-1, "", new Date(new java.util.Date().getTime()), null, null);
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public Post getPost() {
        return post;
    }

    @Override
    public String toString(){
        return this.text + " - " + this.user.getName() + " - " + this.date;
    }
}
