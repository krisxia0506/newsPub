package news.beans;

import news.util.DBGet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created on 2022-10-27 23:05
 *
 * @author Xia Jiayi
 */
public class NewstypeDAO {
    public ArrayList<Newstype> getAllNewstype() {
        Newstype newstype = null;
        ArrayList<Newstype> newstypeList = new ArrayList<Newstype>();
        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;
        try {
            conn = DBGet.getConnection();
            stmt = conn.createStatement();
            String sql = "select * from newstype";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                newstype = new Newstype();
                newstype.setId(rs.getInt("id"));
                newstype.setNewstype(rs.getString("newstype"));
                newstypeList.add(newstype);
            }
        } catch (SQLException e1) {
            System.out.println("getAllNewstype"+e1);
        } finally {
            DBGet.closeConnection(conn);
        }
        return newstypeList;
    }
}
