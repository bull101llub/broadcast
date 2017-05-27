<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="jp.co.cococoa.business.msg.MessageBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<jsp:useBean id="messageListBean" class="jp.co.cococoa.business.msg.MessageListBean" scope="session" />

<%@ page import="jp.co.cococoa.util.StringUtil" %>
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
 background-color: #FFFF99; /* 行の背景色 */
 cursor:pointer
}

table#main tr.comp {
    color: #888888; /* 項目行の背景色 */
    font-weight:normal
}



table#main th {
 background-color: #CCFF99; /* 項目行の背景色 */
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
    font-weight:bold
}
</style>
<script>
    // 行選択時のsubmit
    function selrow(row, bane, creatymd, msg) {


      var option = document.form01.contents.options;
      var Idx = document.form01.contents.selectedIndex;
      var value = option[ Idx ];
      var title = value.innerHTML;
      var target = document.getElementById("form01");

      parent.msg.document.messageform.msg.value=msg;
      parent.msg.document.getElementById('postname').innerHTML = "投稿者：" + bane;
      parent.msg.document.getElementById('posttime').innerHTML = "投稿日時：" + creatymd;
      parent.msg.document.getElementById('ctitile').innerHTML = title;


    }

    // 一覧へのポイント反映
    function findRow(param, point){
        var target = document.getElementById("main");
        var rows = target.rows;
        if (null == rows || rows.length==0){
            return;
        }
        for(var i=0; i< rows.length; ++i) {
            if (param == rows[i].id) {
                 var cell= rows[i].cells[1];
                 cell.innerText = -5
            }
        }
    }

    function contentsChenge(obj){
    	var index = obj.selectedIndex;
    	if (index != 0){
    	    val = obj.options[index].value;
    	    move(val);
    	}
    }
    function move(value) {
        var form = document.getElementById("form01");
        form.action="Message"
        form.method = "post";
        setParam(form, "o", '<%=messageListBean.getOwnerid() %>')
        setParam(form, "b", '<%=messageListBean.getBoothid() %>')
        setParam(form, "a", '<%=messageListBean.getBroadcastid() %>')
        setParam(form, "c", value)
        setParam(form, "key", "find")
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
<%
    List<MessageBean> list = messageListBean.getList();
    Map<String, String> contentMap = messageListBean.getContentMap();
    System.out.println("メッセージ件数；" + list.size());

%>
<%= list.size() %>件
C:<%=messageListBean.getContentid() %>

<input type="hidden"  id="contentid" value="<%=messageListBean.getContentid() %>">
<input type="button" value="検索" onclick="findRow('msg2')">
<form id="form01" name="form01">
<table id="top">
    <tr>
        <td>
            <select class="tema" name="contents"  id="contentsList" onChange="contentsChenge(this)">
                <option value=""></option>
                <%
                    Iterator<String> itm = contentMap.keySet().iterator();
                    while (itm.hasNext()) {
                    	String k = itm.next();
                %>
                        <option value="<%=k %>"  <%if(k.equals(messageListBean.getContentid())) {%> selected<%} %>><%=k %>:<%=contentMap.get(k) %></option>
                <%} %>

            </select>
            <input type="button" name="find" value="最新表示">
            <input type="checkbox" name="riyu" value="1">既除く
        </td>
    </tr>
</table>
<br>
<br>
<table id="main" class="table-header-fixed">
    <thead>
        <tr id="header">
            <th width="20">No</th>
            <th width="20">point</th>
            <th width="20"></th>
            <th width="100">リスナー</th>
            <th width="50">twitter id</th>
            <th width="150">メッセージ</th>
            <th width="150">投稿日時</th>
        </tr>
    </thead>
    <tbody>
    <% if (null != list && list.size() > 0) { %>

     <%    Iterator<MessageBean> it = list.iterator();
           int i = 0;
           while(it.hasNext()) {
        	   i++;
        	   MessageBean bean = it.next();
    %>
        <tr id="<%=bean.getMsgid()%>" ondblclick="selrow(this, '<%=bean.getPostname() %>', '<%=bean.getCreateymd() %>', '<%=bean.getMsg()%>')">
            <td width="20"><%=i %></td>
            <td width="20"><%=bean.getPoint()%></td>
            <td width="20"></td>
            <td width="100"><%=bean.getPostname() %></td>
            <td width="50"></td>
            <td width="150"><%=StringUtil.leftString(bean.getMsg(), 10, "…") %></td>
            <td width="150"><%=bean.getCreateymd() %></td>
        </tr>
       <% }%>
    <%} %>
    <% if (null == list || list.size() == 0) { %>
        <tr id="msg1" ondblclick="selrow(this)">
            <td width="20"></td>
            <td width="20"></td>
            <td width="20"></td>
            <td width="100"></td>
            <td width="50"></td>
            <td width="150"></td>
            <td width="150"></td>
        </tr>
    <%} %>
    </tbody>
</table>
</form>
</body>
</html>