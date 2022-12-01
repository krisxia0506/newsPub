package news.beans;

import java.util.ArrayList;

/**
 * Created on 2022-11-23 16:38
 *
 * @author Xia Jiayi
 */
public class NewsPageDAO {
    /**
     * 根据每页新闻条数返回共有多少页
     * @param pageSize 每页显示的记录数
     * @return 共多少页
     */
    public int getPageCount(int pageSize){
        NewsDAO newsDAO = new NewsDAO();
        int recordCount=0,t1=0,t2=0;
        //新闻总条数
        recordCount = newsDAO.getNewsCount();
        //取余
        t1=recordCount%pageSize;
        //取商
        t2=recordCount/pageSize;
        //如果取余是0，就把t2的值返回，否则t2+1的值返回
        return t1==0?t2:t2+1;
    }

    /**
     * 获取指定页的新闻
     * @param pageNo 指定页
     * @param pageSize 每页显示的新闻条数
     * @return 指定页的新闻
     */
    public ArrayList<News> getNewsByPage(int pageNo,int pageSize){
        NewsDAO newsDAO = new NewsDAO();
        ArrayList<News> newsList = new ArrayList<News>();
        int start = (pageNo-1)*pageSize;
        newsList = newsDAO.getNewsByST(start,pageSize);
        return newsList;
    }

}
