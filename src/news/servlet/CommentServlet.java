package news.servlet;

import news.beans.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created on 2022-11-21 11:05
 *
 * @author Xia Jiayi
 */
public class CommentServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        CommentDAO commentDAO = new CommentDAOImpl();
        ArrayList<Comment> commentList = new ArrayList<Comment>();
        ArrayList<News> newsList = new ArrayList<>();
        HttpSession session = request.getSession();
        String func = request.getParameter("action");
        if (func == null) {
            func = "";
        }
        //按news id查询评论
        switch (func) {
            case "disp" : {
                NewsDAO newsDAO = new NewsDAO();
                String newsid = request.getParameter("newsid");
                commentList = commentDAO.getByNewsId(Integer.parseInt(newsid));
                request.setAttribute("commentList", commentList);
                News news = newsDAO.getById(newsid);
                request.setAttribute("news", news);
                request.getRequestDispatcher("listComment.jsp").forward(request, response);


                break;
            }
            case "add" : {
                Comment comm = new Comment();
                String newsid = request.getParameter("newsid");
                String commentauthor = request.getParameter("commentauthor");
                String comment = request.getParameter("comment");
                comm.setComment(comment);
                comm.setCommentauthor(commentauthor);
                comm.setNewsid(Integer.parseInt(newsid));
                if (commentDAO.insert(comm)) {
                    request.getRequestDispatcher("comment?action=disp&newsid=" + newsid).forward(request, response);

                } else {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                break;
            }
            case "del" : {
                String id = request.getParameter("id");
                String username = request.getParameter("username");
                if (commentDAO.deleteById(id)) {
                    if ("admin".equals(username)) {
                        request.getRequestDispatcher("comment?action=manage").forward(request, response);

                    } else {
                        request.getRequestDispatcher("comment?action=usermanage&username=" + username).forward(request, response);
                    }
                } else {
                    request.getRequestDispatcher("listComment.jsp?error=1").forward(request, response);
                }
                break;
            }
            case "manage" : {//管理员评论管理
                commentList = commentDAO.getAll();
                NewsDAO newsDAO = new NewsDAO();
                newsList = newsDAO.getAllNews();

                //遍历
                for (Comment comment : commentList) {
                    for (News news : newsList) {
                        if (Objects.equals(news.getId(), comment.getNewsid())) {
                            comment.setNews(news);
                        }
                    }
                }
                request.setAttribute("commentList", commentList);
                request.getRequestDispatcher("manageComment.jsp").forward(request, response);
                break;
            }
            case "usermanage" : {//用户评论管理
                String username = request.getParameter("username");

                if (session.getAttribute("username").equals(username)) {
                    commentList = commentDAO.getByUsername(username);

                    NewsDAO newsDAO = new NewsDAO();
                    newsList = newsDAO.getAllNews();

                    //遍历
                    for (Comment comment : commentList) {
                        for (News news : newsList) {
                            if (Objects.equals(news.getId(), comment.getNewsid())) {
                                comment.setNews(news);
                            }
                        }
                    }
                    request.setAttribute("commentList", commentList);
                    request.getRequestDispatcher("manageComment.jsp").forward(request, response);

                } else {
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
                break;
            }
            default : request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
