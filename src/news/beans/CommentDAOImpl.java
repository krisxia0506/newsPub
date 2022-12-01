package news.beans;

import news.util.DBGet;
import news.util.DateTimeUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created on 2022-11-21 10:41
 *
 * @author Xia Jiayi
 */
public class CommentDAOImpl implements CommentDAO {
    public boolean insert(Comment comment) {
        boolean result = false;
        int n = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        comment.setCommenttime(DateTimeUtil.getNowStr());
        try {
            conn = DBGet.getConnection();
            String sql = "insert into comment(comment,commentauthor,commenttime,newsid) values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, comment.getComment());
            ps.setString(2, comment.getCommentauthor());
            ps.setString(3, comment.getCommenttime());
            ps.setInt(4, comment.getNewsid());
            n = ps.executeUpdate();
        } catch (SQLException e1) {
            System.out.println(e1 + "commentDAOImpl");
        } finally {
            DBGet.closeConnection(conn);
        }
        if (n > 0) result = true;
        return result;
    }

    public ArrayList<Comment> getByNewsId(int newsid) {
        Comment comment = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        ArrayList<Comment> commentList = new ArrayList<Comment>();
        try {
            conn = DBGet.getConnection();
            String sql = "select * from comment where newsid = ? order by commenttime desc";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, newsid);
            rs = ps.executeQuery();

            while (rs.next()) {
                comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setComment(rs.getString("comment"));
                comment.setCommentauthor(rs.getString("commentauthor"));
                comment.setCommenttime(rs.getString("commenttime"));
                comment.setNewsid(rs.getInt("newsid"));
                commentList.add(comment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBGet.closeConnection(conn);
        }
        return commentList;
    }

    public ArrayList<Comment> getByUsername(String username) {
        Comment comment = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        ArrayList<Comment> commentList = new ArrayList<Comment>();
        try {
            conn = DBGet.getConnection();
            String sql = "select * from comment where commentauthor = ? order by commenttime desc";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();

            while (rs.next()) {
                comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setComment(rs.getString("comment"));
                comment.setCommentauthor(rs.getString("commentauthor"));
                comment.setCommenttime(rs.getString("commenttime"));
                comment.setNewsid(rs.getInt("newsid"));
                commentList.add(comment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBGet.closeConnection(conn);
        }
        return commentList;
    }

    public ArrayList<Comment> getAll() {
        Comment comment = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        ArrayList<Comment> commentList = new ArrayList<Comment>();
        try {
            conn = DBGet.getConnection();
            String sql = "select * from comment order by commenttime desc";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setComment(rs.getString("comment"));
                comment.setCommentauthor(rs.getString("commentauthor"));
                comment.setCommenttime(rs.getString("commenttime"));
                comment.setNewsid(rs.getInt("newsid"));
                commentList.add(comment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBGet.closeConnection(conn);
        }
        return commentList;
    }

    public boolean deleteById(String id) {
        boolean result = false;
        int n = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "delete from comment where id= ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            n = ps.executeUpdate();
        } catch (SQLException e1) {
            System.out.println(e1 + "deleteById");
        } finally {
            DBGet.closeConnection(conn);
        }
        if (n > 0) result = true;
        return result;
    }

    public boolean deleteByNewsId(String id) {
        boolean result = false;
        int n = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "delete from comment where newsid= ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            n = ps.executeUpdate();
        } catch (SQLException e1) {
            System.out.println(e1 + "deleteByNewsId");
        } finally {
            DBGet.closeConnection(conn);
        }
        if (n > 0) result = true;
        return result;
    }
    public boolean deleteByUsername(String username) {
        boolean result = false;
        int n = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "delete from comment where commentauthor= ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            n = ps.executeUpdate();
        } catch (SQLException e1) {
            System.out.println(e1 + "deleteById");
        } finally {
            DBGet.closeConnection(conn);
        }
        if (n > 0) result = true;
        return result;
    }

    public ArrayList<Comment> getTop5() {
        Comment comment = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        ArrayList<Comment> commentList = new ArrayList<Comment>();
        try {
            conn = DBGet.getConnection();
            String sql = "select * from comment order by commenttime desc limit 5";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String commenttext = rs.getString("comment");
                //截取过长的评论
                if (commenttext.length() > 18) {
                    commenttext = commenttext.substring(0, 18) + "......";
                }
                comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setComment(commenttext);
                comment.setCommentauthor(rs.getString("commentauthor"));
                comment.setCommenttime(rs.getString("commenttime"));
                comment.setNewsid(rs.getInt("newsid"));
                commentList.add(comment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBGet.closeConnection(conn);
        }
        return commentList;

    }
}

