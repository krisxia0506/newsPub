<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-10-27
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="news.beans.News" %>
<%@include file="checkvaild.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="newsDAO" class="news.beans.NewsDAO" scope="page"/>
<!DOCTYPE>
<html>
<head>
    <title>新闻发布系统V2</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <script src="js/fun.js"></script>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
</head>
<body>
<jsp:include page="common/top.jsp"/>
<div id="content">
    <div class="aside">
        <jsp:include page="common/left.jsp"/>
    </div>
    <div id="main">
        <div class="news_list">
            <div class="top-bar">
                <h1>新闻管理</h1>
                <button style="margin-left: 450px;" value="发布新闻" onclick="window.location.href='addNews.jsp';">
                    发布新闻
                </button>
            </div>
            <br>
            <div class="table">
                <table class="listing" cellspacing="0" cellpadding="0">
                    <tr>
                        <th class="first" width="40">序号</th>
                        <th>新闻标题</th>
                        <th>发布时间</th>
                        <th style="width: 40px;">修改</th>
                        <th style="width: 40px;">删除</th>
                    </tr>
                    <c:forEach var="news" items="${newsList}" varStatus="status">
                        <tr>
                            <td>${status.count}</td>
                            <td class="title">
                                <a href="news?action=disp&id=${news.id}">
                                        ${news.title}
                                </a>
                            </td>
                            <td class="ntime">${news.pubtime}</td>
                            <td>
                                <button value="修改" onclick="window.location.href='modiNews.jsp?id=${news.id}';">修改
                                </button>

                            </td>
                            <td>
                                <button value="删除" onclick="deleteNews(${news.id});">删除</button>

                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
    <div class="blank10"></div>
    <div class="blank20"></div>
</div>

<%@include file="common/bottom.txt" %>
</body>
</html>