<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-11-09
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="news.beans.News" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="news.beans.Newstype" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="newsDAO" class="news.beans.NewsDAO" scope="page"/>
<jsp:useBean id="newstypeDAO" class="news.beans.NewstypeDAO" scope="page"/>

<%
    ArrayList<Newstype> newstypeList = newstypeDAO.getAllNewstype();
    request.setAttribute("newstypeList", newstypeList);
%>

<!DOCTYPE html>
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
            <h1 style="margin: 0 0 20px">新闻列表</h1>

            <ul>
                <c:forEach items="${requestScope.newsList}" var="news">
                    <li>
                        <div class="dd_lm">[
                            <c:forEach var="newstype" items="${newstypeList}">
                                <c:choose>
                                    <c:when test="${newstype.id==news.newstype}">
                                        ${newstype.newstype}
                                    </c:when>

                                </c:choose>

                            </c:forEach>]
                        </div>
                        <div class="dd_bt">
                            <a href="news?action=disp&id=${news.id}">${news.title}</a>
                        </div>
                        <div class="dd_time">${news.pubtime}</div>
                    </li>
                </c:forEach>
            </ul>
            <c:if test="${requestScope.newsList.size()==0}">
                <h3 style="text-align: center;margin-top: 100px">无结果</h3>
                </c:if>
            <div class="page">
                <div>
                <c:if test="${pageCount>0}">
                    <c:choose>
                        <c:when test="${pageNo==1}"><%--首页显示--%>
                            <a href="news?pageNo=${pageNo+1}" >下一页</a>
                            ${pageNo} / ${pageCount}
                            <a href="news?pageNo=${pageCount}">尾页</a>
                        </c:when>
                        <c:when test="${pageNo==pageCount}"><%--尾页显示--%>
                            <a href="news?pageNo=1" >首页</a>
                            ${pageNo} / ${pageCount}
                            <a href="news?pageNo=${pageNo-1}" >上一页</a>
                        </c:when>
                        <c:otherwise>
                            <a href="news?pageNo=1" >首页</a>
                            <a href="news?pageNo=${pageNo-1}" >上一页</a>
                            ${pageNo} / ${pageCount}
                            <a href="news?pageNo=${pageNo+1}" >下一页</a>
                            <a href="news?pageNo=${pageCount}">尾页</a>
                        </c:otherwise>
                    </c:choose>






                </c:if>
                </div>
            </div>
        </div>


    </div>
    <div class="blank10"></div>
    <div class="blank20"></div>
</div>

<%@ include file="common/bottom.txt" %>
</body>
</html>

