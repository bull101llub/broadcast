<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<jsp:useBean id="postMessageBean" class="jp.co.cococoa.business.postmessage.PostMessageBean" scope="session" />
<%@ page import="jp.co.cococoa.business.postmessage.PostMessageContentsBean" %>
<%@ page import="jp.co.cococoa.util.StringUtil" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="Content-Script-Type" content="text/javascript">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">

<title>投稿フォーム</title>
<link rel="stylesheet" type="text/css" href="http://localhost:8080/cococoa/css/menu.css" />
<style>
@media screen and (max-width: 380px){
    table {
        width:100%;
    }
}
table th {
 background-color: #CCFF99;
 text-align: left;
}
body{
   font-size : 12px;
   font-family: "ＭＳ ゴシック";
}


table.table-header-fixed tbody td {
    border-color:#757575;
    word-wrap: break-word;
    font-size : 12px;
}
table.table-header-fixed thead th {
    border-color:#757575;
    font-size : 15px;
}
</style>

<script type="text/javascript" src="./js/postmessage.js"></script>

</head>
<body>
<table width="630" border="0px">
    <tr>
        <td align="right" valign="top">
            <div id="menu"  valign="top"  align="left">
                <ul>
                    <li><a href="">RANK</a></li>
                </ul>
            </div>
        </td>
    </tr>
</table>
<br>
<p>投稿フォーム</p>
<p><%=postMessageBean.getBoothnm()%></p>
<form id="postform">
<input type="hidden" name="o" value="<%=postMessageBean.getOwnerid() %>">
<input type="hidden" name="b" value="<%=postMessageBean.getBoothid() %>">
<input type="hidden" name="a" value="<%=postMessageBean.getBroadcastid() %>">
ラジオネーム：<input type="text" id="postname" name="postname" value=""/><input type="button" value="投稿" onClick="update()">

<%
    List<PostMessageContentsBean> contentsList = postMessageBean.getPostMessageContentsList();
    if (null != contentsList && contentsList.size() > 0) {
        Iterator<PostMessageContentsBean> it = contentsList.iterator();
        while(it.hasNext()) {
        	PostMessageContentsBean bean = it.next();
%>
    <table id="free" class="table-header-fixed">
        <thead>
            <tr><th><%=bean.getTitle() %></th></tr>
        </thead>
        <tbody>
            <tr><td><%=bean.getDescription()%></td></tr>
            <tr><td><textarea id="<%=bean.getContentid()%>" name="<%=bean.getContentid()%>"
                                     cols="30" rows="5" class="example"  isover="false" onKeyup="lengthChk(this);"></textarea></td></tr>

            <tr><td><span id="msg1<%=bean.getContentid()%>"></span> / <span id="msg2<%=bean.getContentid()%>"></span></td></tr>
        </tbody>
    </table>
<%} } else {}%>
</form>
</body>
</html>