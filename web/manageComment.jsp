<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-11-21
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="checkvaild.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <title>新闻发布系统V2</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <script src="js/fun.js"></script>
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
                <h1>
                    评论管理
                </h1>
                <div class="breadcrumbs"></div>
            </div>
            <br>
            <div class="table">
                <table class="listing" cellpadding="0" cellspacing="0" width="600" border="1">
                    <tbody>
                    <tr>
                        <th class="first" width="200px">新闻标题</th>
                        <th style="width: 200px">评论内容</th>
                        <th>评论作者</th>
                        <th style="width: 150px;">评论时间</th>
                        <th style="width: 100px;">删除</th>
                    </tr>
                    <c:forEach items="${requestScope.commentList}" var="comt">
                        <tr>
                            <td width="250px">
                                <a href="news?action=disp&id=${comt.newsid}">
                                        ${comt.news.title}
                                </a>
                            </td>
                            <td style="text-align: left;width: 100px;">
                                <a href="comment?action=disp&newsid=${comt.newsid}">
                                        ${comt.comment}
                                </a>
                            </td>
                            <td>${comt.commentauthor}</td>
                            <td>${comt.commenttime}</td>
                            <td width="10px">
                                <a href="comment?action=del&username=${sessionScope.username}&id=${comt.id}"
                                   onclick="return confirm('确定删除？')">
                                    <i class="iconfont">&#xe74b;</i>
                                </a>
                            </td>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
    <div class="blank10"></div>
    <div class="blank20"></div>
</div>

<%@ include file="common/bottom.txt" %>
</body>
</html>

