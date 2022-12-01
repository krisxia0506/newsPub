package news.beans;

import java.util.ArrayList;

/**
 * Created on 2022-11-21 10:37
 *
 * @author Xia Jiayi
 */
public interface CommentDAO {
    public boolean insert(Comment comment);

    public ArrayList<Comment> getByNewsId(int newsid);

    public ArrayList<Comment> getByUsername(String username);

    public ArrayList<Comment> getAll();

    public boolean deleteById(String id);
    public boolean deleteByNewsId(String id);

    public ArrayList<Comment> getTop5();
}
