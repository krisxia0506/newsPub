package news.beans;

import news.util.DBGet;
import news.util.DateTimeUtil;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created on 2022-10-27 10:30
 *
 * @author Xia Jiayi
 */
public class NewsDAO {

    /**
     * 查看所有新闻
     *
     * @return ArrayList<News>
     */
    public ArrayList<News> getAllNews() {
        News news = null;
        ArrayList<News> newsList = new ArrayList<News>();
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = DBGet.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "select * from news";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                news = new News();
                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setContent(rs.getString("content"));
                news.setAuthor(rs.getString("author"));
                news.setPubtime(rs.getString("pubtime"));
                news.setKeyword(rs.getString("keyword"));
                news.setAcnumber(rs.getString("acnumber"));
                news.setNewstype(rs.getString("newstype"));
                newsList.add(news);
            }
        } catch (SQLException e1) {
            System.out.println("getAllNews" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return newsList;
    }

    /**
     * 插入一条新闻
     *
     * @param news 新闻对象
     * @return boolean
     */
    public boolean insertNews(News news) {

        boolean result = false;
        int n = 0;
        news.setPubtime(DateTimeUtil.getNowStr());
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "insert into news(title,content,author,pubtime,keyword,newstype) value(?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, news.getTitle());
            ps.setString(2, news.getContent());
            ps.setString(3, news.getAuthor());
            ps.setString(4, news.getPubtime());
            ps.setString(5, news.getKeyword());
            ps.setString(6, news.getNewstype());
            n = ps.executeUpdate();
        } catch (SQLException e1) {
            System.out.println("insert" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        if (n > 0) {
            result = true;
        }
        return result;
    }

    /**
     * 根据ID查询指定新闻
     *
     * @param id 新闻id
     * @return News
     */
    public News getById(String id) {
        News news = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "select * from news where id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                news = new News();
                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setContent(rs.getString("content"));
                news.setAuthor(rs.getString("author"));
                news.setPubtime(rs.getString("pubtime"));
                news.setKeyword(rs.getString("keyword"));
                news.setAcnumber(rs.getString("acnumber"));
                news.setNewstype(rs.getString("newstype"));
            }
        } catch (SQLException e1) {
            System.out.println("getById" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return news;
    }

    /**
     * 增加访问量
     *
     * @param id 新闻id
     */
    public void increaseAc(String id) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "update news set acnumber = acnumber +1 where id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e1) {
            System.out.println("increaseAc" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
    }

    /**
     * 相关新闻
     *
     * @param id 新闻id
     * @return 相关新闻列表
     */
    public ArrayList<News> getRelate(String id) {
        News news = null;
        ArrayList<News> newsList = new ArrayList<News>();
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = DBGet.getConnection();
            String sql = "SELECT * from news where newstype=(select newstype from news where id=?) and id != ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                news = new News();
                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                newsList.add(news);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBGet.closeConnection(conn);
        }
        return newsList;
    }

    /**
     * 修改新闻
     *
     * @param news 新闻对象
     * @return 是否成功
     */
    public boolean modifyNews(News news) {
        boolean result = false;
        int n = 0;
        Connection conn = null;
        String sql = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            sql = "update news set title=?,content=?,author=?,pubtime=?,keyword=?,newstype=? where id =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, news.getTitle());
            ps.setString(2, news.getContent());
            ps.setString(3, news.getAuthor());
            ps.setString(4, news.getPubtime());
            ps.setString(5, news.getKeyword());
            ps.setString(6, news.getNewstype());
            ps.setString(7, String.valueOf(news.getId()));
            n = ps.executeUpdate();
        } catch (SQLException e1) {
            System.out.println("modify" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        if (n > 0) {
            result = true;
        }
        return result;
    }

    /**
     * 删除新闻
     *
     * @param id 新闻id
     * @return 是否成功
     */
    public boolean deleteById(String id) {
        boolean result = false;
        Connection conn = null;
        PreparedStatement ps = null;
        CommentDAOImpl commentDAO = new CommentDAOImpl();

        try {
            conn = DBGet.getConnection();
            String sql = "DELETE FROM news WHERE id = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            if (ps.executeUpdate() == 1) {
                //删除对应新闻的评论
                commentDAO.deleteByNewsId(id);
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBGet.closeConnection(conn);
        }
        return result;
    }

    /**
     * 热点新闻
     *
     * @return 热点新闻列表
     */
    public ArrayList<News> getHotNews() {
        News news = null;
        ArrayList<News> newsList = new ArrayList<News>();
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            conn = DBGet.getConnection();
            stmt = conn.createStatement();
            String sql = "select * from news order by acnumber desc limit 5";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                news = new News();
                news.setId(rs.getInt("id"));
                String title = rs.getString("title");
                //截取过长的新闻标题
                if (title.length() > 18) {
                    title = title.substring(0, 18) + "......";
                }
                news.setTitle(title);
                news.setContent(rs.getString("content"));
                news.setAuthor(rs.getString("author"));
                news.setPubtime(rs.getString("pubtime"));
                news.setKeyword(rs.getString("keyword"));
                news.setAcnumber(rs.getString("acnumber"));
                news.setNewstype(rs.getString("newstype"));
                newsList.add(news);
            }
        } catch (SQLException e1) {
            System.out.println("getHotNews" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return newsList;
    }

    /**
     * 按关键字查询新闻
     *
     * @param keyword 关键字
     * @return 新闻列表
     */
    public ArrayList<News> getNewsByKeyword(String keyword) {
        News news = null;
        ArrayList<News> newsList = new ArrayList<News>();
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "select * from news where keyword = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, keyword);
            rs = ps.executeQuery();
            while (rs.next()) {
                news = new News();
                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setContent(rs.getString("content"));
                news.setAuthor(rs.getString("author"));
                news.setPubtime(rs.getString("pubtime"));
                news.setKeyword(rs.getString("keyword"));
                news.setAcnumber(rs.getString("acnumber"));
                news.setNewstype(rs.getString("newstype"));
                newsList.add(news);
            }
        } catch (SQLException e1) {
            System.out.println("getNewsByKeyword" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return newsList;
    }

    /**
     * 查询新闻条数
     */
    public int getNewsCount() {
        int count = 0;
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            conn = DBGet.getConnection();
            stmt = conn.createStatement();
            String sql = "select count(*) from news";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e1) {
            System.out.println("getNewsCount" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return count;
    }

    /**
     * 根据起始和条数查询新闻
     */
    public ArrayList<News> getNewsByST(int start, int count) {
        News news = null;
        ArrayList<News> newsList = new ArrayList<News>();
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            conn = DBGet.getConnection();
            String sql = "select * from news order by id desc limit ?,?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, start);
            ps.setInt(2, count);
            rs = ps.executeQuery();
            while (rs.next()) {
                news = new News();
                news.setId(rs.getInt("id"));
                news.setTitle(rs.getString("title"));
                news.setContent(rs.getString("content"));
                news.setAuthor(rs.getString("author"));
                news.setPubtime(rs.getString("pubtime"));
                news.setKeyword(rs.getString("keyword"));
                news.setAcnumber(rs.getString("acnumber"));
                news.setNewstype(rs.getString("newstype"));
                newsList.add(news);
            }
        } catch (SQLException e1) {
            System.out.println("getNewsByPage" + e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return newsList;
    }
}