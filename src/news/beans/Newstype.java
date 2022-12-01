package news.beans;

/**
 * Created on 2022-10-27 23:02
 *
 * @author Xia Jiayi
 */
public class Newstype implements java.io.Serializable {
    private Integer id;
    private String newstype;

    public Newstype() {
    }

    public Newstype(Integer id, String newstype) {
        this.id = id;
        this.newstype = newstype;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNewstype() {
        return newstype;
    }

    public void setNewstype(String newstype) {
        this.newstype = newstype;
    }
}
