<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<jsp:useBean id="rankBean" class="jp.co.cococoa.business.rank.RankBean" scope="session" />

<%@ page import="jp.co.cococoa.business.rank.RankUserBean" %>
<%@ page import="jp.co.cococoa.util.StringUtil" %>
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

table#main tr:hover {
 background-color: #FFFF99;
 cursor:pointer
}
table#main th {
 background-color: #CCFF99;
}
body{
   font-size : 11px;
   font-family: "ＭＳ ゴシック";
}


table.table-header-fixed tbody td,
table.table-header-fixed thead th {
    float: left;
    height: 12px;
    border: thin solid;
    border-spacing: 0px;
    border-color:#757575;
}

</style>
<script>

    function putPoint(point, messageid) {
        var form = document.getElementById("rank");
        form.action = "Message";
        form.method = "post";
        setParam(form, "point", point)
        setParam(form, "messageid", messageid)
        setParam(form, "key", "point")
        form.submit();
    }

    function setParam(form, paramName, value) {
        var input = document.createElement('input');
        input.setAttribute('type', 'hidden');
        input.setAttribute('name', paramName);
        input.setAttribute('value', value);
        form.appendChild(input);
    }

</script>
</head>
<body>
<br>
<br>
<br>
<br>
投稿ランキング
<table id="main" class="table-header-fixed">
    <thead>
        <tr>
            <th width="20">No</th>
            <th width="20">□</th>
            <th width="100">投稿者</th>
            <th width="50">ID</th>
            <th width="50">合計Pt</th>
            <th width="50">投稿数</th>
            <th width="50">得点率</th>
        </tr>
    </thead>
    <tbody>
    <%
    List<RankUserBean> rankList = rankBean.getRankUserBeanList();

    if(null != rankList && rankList.size() > 0) {
    %>
    <%
        Iterator<RankUserBean> it = rankList.iterator();
        int i = 0;
        while(it.hasNext()){
        	RankUserBean bean = it.next();
        	++i;
    %>
        <tr>
            <td width="20"><%=i %></td>
            <td width="20">□</td>
            <td width="100"><%=bean.getPostname() %></td>
            <td width="50"><%=bean.getPostid() %></td>
            <td style="text-align: right;" width="50"><%=bean.getPoint() %></td>
            <td style="text-align: right;" width="50"><%=bean.getTotalpost() %></td>
            <td width="50">
            </td>
        </tr>
       <%} %>
  <% }%>
    </tbody>
</table>
<form id="rank">
</form>
</body>
</html>