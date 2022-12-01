<%@ page import="java.util.ArrayList" %>
<%@ page import="news.beans.Newstype" %>
<%@ page import="news.beans.News" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-11-03
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="checkvaild.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="newsDAO" class="news.beans.NewsDAO" scope="page"/>
<jsp:useBean id="newstypeDAO" class="news.beans.NewstypeDAO" scope="page"/>
<%
    ArrayList<Newstype> newstypeList = newstypeDAO.getAllNewstype();
    String id = request.getParameter("id");
    News news = newsDAO.getById(id);
    request.setAttribute("newstypeList", newstypeList);
    request.setAttribute("news", news);
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE>
<html>
<head>
    <title>新闻发布系统V2</title>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
</head>
<body>
<jsp:include page="common/top.jsp"/>
<div id="content">
    <div class="aside">
        <jsp:include page="common/left.jsp"/>
    </div>
    <div id="main">
        <!-- main begin -->
        <div class="inputform">
            <div class="top-bar">
                <h1>新闻修改</h1>
                <div class="breadcrumbs"></div>
            </div>
            <br/>
            <form action="news?action=modi" method="post">
                <input type="hidden" name="author" value="${sessionScope.username}"/>
                <input type="hidden" name="id" value="${news.id}"/><br>
                <input type="hidden" name="pubtime" value="${news.pubtime}"/><br>
                标题：<input type="text" name="title" id="title" value="${news.title}"><br><br>
                类别：<select name="newstype">
                <c:forEach var="newstype" items="${newstypeList}">
                    <c:choose>
                        <c:when test="${newstype.id==news.newstype}">
                            <option value="${newstype.id}" selected="selected">
                                    ${newstype.newstype}
                            </option>
                        </c:when>
                        <c:otherwise>
                            <option value="${newstype.id}">
                                    ${newstype.newstype}
                            </option>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>
            </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                关键字：
                <input type="text" name="keyword" id="keyword" value="${news.keyword}"/>
                <br>
                <br>
                内容:<textarea name="content" cols="25" rows="5">${news.content}</textarea>
                <br><br>
                <input type="submit" value="修改"/><br>
            </form>
        </div>
        <!--main end -->
    </div>
    <div class="blank20"></div>
    <div class="blank10"></div>
</div>
<%@include file="common/bottom.txt" %>
</body>
</html>
