package news.beans;

/**
 * Created on 2022-10-27 10:26
 *
 * @author Xia Jiayi
 */
public class News implements java.io.Serializable {
    private Integer id;
    private String title;
    private String content;
    private String author;
    private String pubtime;
    private String keyword;
    private String note;
    private String newstype;
    private String acnumber;

    public News() {
    }

    public News(String title, String content, String author, String pubtime, String keyword, String note) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.pubtime = pubtime;
        this.keyword = keyword;
        this.note = note;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPubtime() {
        return pubtime;
    }

    public void setPubtime(String pubtime) {
        this.pubtime = pubtime;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNewstype() {
        return newstype;
    }

    public void setNewstype(String newstype) {
        this.newstype = newstype;
    }

    public String getAcnumber() {
        return acnumber;
    }

    public void setAcnumber(String acnumber) {
        this.acnumber = acnumber;
    }
}
