package news.servlet;

import news.beans.News;
import news.beans.NewsDAO;
import news.beans.NewsPageDAO;
import news.beans.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created on 2022-11-09 16:27
 *
 * @author Xia Jiayi
 */
public class NewsServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        ArrayList<News> newsList = new ArrayList<News>();
        NewsDAO newsDAO = new NewsDAO();
        NewsPageDAO newsPageDAO = new NewsPageDAO();
        int pageNo = 1;
        int pageSize = 6;
        String strPageNo = request.getParameter("pageNo");
        if (strPageNo!=null){
            pageNo=Integer.parseInt(strPageNo);
        }

        String func = request.getParameter("action");
        if (func == null) {
            func = "";
        }
        switch (func) {
            case "query" : {
                String keyword = request.getParameter("keyword");
                newsList = newsDAO.getNewsByKeyword(keyword);
                request.setAttribute("newsList", newsList);
                request.getRequestDispatcher("listNews.jsp").forward(request, response);
                break;
            }
            case "modi" : {
                News news = new News();
                String id = request.getParameter("id");
                String author = request.getParameter("author");
                String pubtime = request.getParameter("pubtime");
                String title = request.getParameter("title");
                String newstype = request.getParameter("newstype");
                String keyword = request.getParameter("keyword");
                String content = request.getParameter("content");
                news.setId(Integer.parseInt(id));
                news.setAuthor(author);
                news.setPubtime(pubtime);
                news.setTitle(title);
                news.setNewstype(newstype);
                news.setKeyword(keyword);
                news.setContent(content);
                if (newsDAO.modifyNews(news)) {
                    request.getRequestDispatcher("news?action=manage").forward(request, response);
                } else {
                    request.getRequestDispatcher("news?action=manage").forward(request, response);
                }
                break;
            }
            case "manage" : {
                newsList = newsDAO.getAllNews();
                request.setAttribute("newsList", newsList);
                request.getRequestDispatcher("manageNews.jsp").forward(request, response);
                break;
            }
            case "del" : {
                String newsId = request.getParameter("id");
                if (newsDAO.deleteById(newsId)) {
                    request.getRequestDispatcher("news?action=manage").forward(request, response);
                } else {
                    System.out.println();
                }
                break;
            }
            case "add" : {
                News news = new News();
                String author = request.getParameter("author");
                String title = request.getParameter("title");
                String newstype = request.getParameter("newstype");
                String keyword = request.getParameter("keyword");
                String content = request.getParameter("content");
                news.setAuthor(author);
                news.setTitle(title);
                news.setNewstype(newstype);
                news.setKeyword(keyword);
                news.setContent(content);
                if (newsDAO.insertNews(news)) {
                    request.getRequestDispatcher("news?action=manage").forward(request, response);
                } else {
                    System.out.println("发布失败");
                    request.getRequestDispatcher("news?action=manage").forward(request, response);
                }
                break;
            }
            case "disp" : {
                String id = request.getParameter("id");
                if (newsDAO.getById(id).getId() != null) {
                    newsDAO.increaseAc(id);
                    News news = newsDAO.getById(id);
                    request.setAttribute("news", news);
                    request.setAttribute("relateNews", newsDAO.getRelate(id));
                    request.getRequestDispatcher("dispNews.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                break;
            }
            default : {
                String uname = null;
                uname = (String) session.getAttribute("username");
                if (uname == null) {
                    Cookie[] cookies = request.getCookies();
                    String autologin = null;
                    if (cookies != null) {
                        for (Cookie cookie : cookies) {
                            if ("autologin".equals(cookie.getName())) {
                                autologin = cookie.getValue();
                                break;
                            }
                        }
                    }
                    if (autologin != null) {
                        String[] parts = autologin.split("-");
                        String name = parts[0];
                        String pwd = parts[1];
                        UserDAO userDAO = new UserDAO();
                        if (!userDAO.queryByNamePwd(name, pwd)) {
                            response.sendRedirect("userLogin.jsp ");
                        } else {
                            session.setAttribute("username", name);
                        }
                    }
                }
                newsList = newsPageDAO.getNewsByPage(pageNo, pageSize);
                Integer pageCount = newsPageDAO.getPageCount(pageSize);
                request.setAttribute("pageCount",pageCount);
                request.setAttribute("pageNo",pageNo);
                request.setAttribute("newsList", newsList);
                request.getRequestDispatcher("listNews.jsp").forward(request, response);
            }
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
