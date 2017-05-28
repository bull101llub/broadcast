<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<jsp:useBean id="reserveBean" class="jp.co.cococoa.business.reserve.ReserveBean" scope="session" />
<%@ page import="jp.co.cococoa.business.reserve.ReserveContentsBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>



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
<title>Insert title here</title>
<style>
a {
  border-radius: 6px 6px 0 0;
  display: inline-block;
  line-height: 32px;
  font: sans-serif;
  text-align: center;
  background-color: #efefef;
  color: black;
  margin-left: 4px;
  text-decoration: none;
  padding: 0 22px;
}

a:nth-child(1) { border-top: 6px solid tomato; }
a:nth-child(3) { border-top: 6px solid steelblue; }
a:nth-child(2) { border-top: 6px solid gold; }
a:nth-child(4) { border-top: 6px solid teal; }
a:nth-child(5) { border-top: 6px solid blue; }
a:nth-child(6) { border-top: 6px solid red; }

.content {
  border: 1px solid lightgray;
  height: 150px;
  display: none;
  padding: 5px;
}
a:hover{
    background-color: #F3D898;
}

.content:target {
  display: block;
}


</style>

</head>
<body>
<nav>
<%
List<ReserveContentsBean> list = reserveBean.getContentsList();
Iterator<ReserveContentsBean> it = list.iterator();
while(it.hasNext()) {
	ReserveContentsBean rbean = it.next();
%>
  <a href='#<%=rbean.getContentid()%>'><%=rbean.getTitle()%></a>
<% } %>
</nav>
<%
Iterator<ReserveContentsBean> it2 = list.iterator();
while(it2.hasNext()) {
	ReserveContentsBean rbean2 = it2.next();
%>
    <div id='<%=rbean2.getContentid()%>' class='content'><B><U><%=rbean2.getDescription()%></U></B><BR><%=rbean2.getScript()%></div>
<% } %>

</body>
</html>