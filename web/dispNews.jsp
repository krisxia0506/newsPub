<%@ page import="news.beans.News" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-10-27
  Time: 23:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="newsDAO" class="news.beans.NewsDAO" scope="page"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
    <title>新闻发布系统V2</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
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
                <h1 style="text-align: left;">${requestScope.news.title}
                </h1>
            </div>
            <div class="news_time">
                <span class="left-t">${requestScope.news.pubtime}</span>
                <span class="right-t">（单击：${requestScope.news.acnumber})
                    <i class="iconfont">&#xe663;</i>
                    <a href="comment?action=disp&newsid=${requestScope.news.id}">查看评论</a>
                </span>
                <div class="clear"></div>
            </div>
            <%--正文--%>
            <div class="left_zw" style="position: relative">
                ${requestScope.news.content}
            </div>
            <%--相关新闻--%>
            <div class="div624 border-top-darshd">
                <h4 class="padding-left20" style="margin-top: 0px;color: rgb(30,80,162);">
                    相关新闻：
                </h4>
                <ul class="padding-left20" style="margin-top: -20px">
                    <c:forEach items="${requestScope.relateNews}" var="rnews">
                        <li>
                            <a href="news?action=disp&id=${rnews.id}">${rnews.title}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>


    </div>


</div>
<div class="blank10"></div>
<div class="blank20"></div>
</div>

<%@include file="common/bottom.txt" %>
</body>
</html>
