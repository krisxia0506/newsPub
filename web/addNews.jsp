<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022-10-27
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="news.beans.Newstype" %>
<jsp:useBean id="newsDAO" class="news.beans.NewsDAO" scope="page"/>
<jsp:useBean id="newstypeDAO" class="news.beans.NewstypeDAO" scope="page"/>
<%
    request.setCharacterEncoding("UTF-8");
    ArrayList<Newstype> newstypeList = newstypeDAO.getAllNewstype();
    if(!Objects.equals(session.getAttribute("username"), "admin")){
        response.sendRedirect("index.jsp");
    }
%>
<!DOCTYPE>
<html>
<head>
    <title>新闻发布系统V2</title>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <meta http-equiv="content-type" content="text/html" charset="UTF-8"/>
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
                <h1>新闻发布</h1>
            </div>
            <br>
            <form action="news?action=add" method="post" onsubmit="return CheckAddNews()">
                <input type="hidden" name="author" value="<%=session.getAttribute("username")%>"></input><br>
                标题: <input type="text" name="title" id="title">
                <br><br>
                类别:
                <select name="newstype" id="">
                    <%
                        for (Newstype newstype : newstypeList) {
                    %>
                    <option value="<%=newstype.getId()%>">
                        <%=newstype.getNewstype()%>
                    </option>
                    <%}%>
                </select>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
                关键字： <input type="text" name="keyword" id="keyword"><br><br>
                内容：<textarea name="content" cols="35" rows="5" id="contentcontent"></textarea><br><br>
                <input type="submit" value="发布"></input>
            </form>
        </div>
    </div>
    <div class="blank10"></div>
    <div class="blank20"></div>
</div>
<%@include file="common/bottom.txt" %>
</body>
<script>
    function CheckAddNews()
    {
        if ( document.getElementById("title").value===""||document.getElementById("contentcontent").value==="")
        {
            alert('标题和内容不能为空');
            return false;
        }
        return true;
    }
</script>
</html>