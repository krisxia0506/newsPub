package news.beans;

/**
 * Created on 2022-11-21 10:34
 *
 * @author Xia Jiayi
 */
public class Comment implements java.io.Serializable {
    private Integer id;
    private String comment;
    private String commentauthor;
    private String commenttime;
    private Integer newsid;
    private String note;

    private News news;

    public Comment() {
    }

    public Comment(String comment, String commentauthor, String commenttime, Integer newsid, String note) {
        this.comment = comment;
        this.commentauthor = commentauthor;
        this.commenttime = commenttime;
        this.newsid = newsid;
        this.note = note;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentauthor() {
        return commentauthor;
    }

    public void setCommentauthor(String commentauthor) {
        this.commentauthor = commentauthor;
    }

    public String getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(String commenttime) {
        this.commenttime = commenttime;
    }

    public Integer getNewsid() {
        return newsid;
    }

    public void setNewsid(Integer newsid) {
        this.newsid = newsid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
