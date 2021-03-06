﻿<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<jsp:useBean id="boothListBean" class="jp.co.cococoa.business.booth.BoothListBean" scope="session" />
<%@ page import="jp.co.cococoa.util.StringUtil" %>
<%@ page import="java.util.List" %>
<%@ page import="jp.co.cococoa.business.booth.BoothBean" %>

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
<link rel="stylesheet" type="text/css" href="./css/menu.css" />
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


table.table-header-fixed tbody td {
    float: left;
    height: 71px;
    border: thin solid;
    border-spacing: 0px;
    border-color:#757575;
}
table.table-header-fixed thead th {
    float: left;
    height: 25px;
    border: thin solid;
    border-spacing: 0px;
    border-color:#757575;
}
</style>
<script>
    // 行選択時のsubmit
    function send() {

        var target = document.getElementById("boothform");
        target.action="Booth"
        target.method = "post";

        var roomId = document.getElementById("k1").value;

        //ブースIDが未設定の場合は初期登録へ
        if(typeof roomId === "undefined" || roomId == "") {
            setParam(target, "key", "create")
        } else {
            setParam(target, "key", "save")
        }

        target.submit();
    }

    // 移動
    function move() {

        var target = document.getElementById("boothform");
        target.action="Main"
        target.method = "post";

        var roomId = document.getElementById("k1").value;

        //オーナーIDが未設定の場合は初期登録へ
        if(typeof roomId === "undefined" || roomId == "") {
            setParam(target, "key", "init")
        }

        target.submit();
    }

    function del() {
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
<table width="870" border="0px">
    <tr>
        <td width="525px"></>
        <td align="right" valign="top">
            <div id="menu"  valign="top"  align="right">
                <ul>
                    <!-- <li><a href="./index.html">TOP</a></li> -->
                    <!-- <li><a href="./reserve.html">RESERVE</a></li> -->
                    <!-- <li><a href="./room.html">BOOTH</a></li> -->
                    <!-- <li><a href="./owner.html">OWNER</a></li> -->
                </ul>
            </div>
        </td>
    </tr>
</table>
<%

List<BoothBean> list = boothListBean.getBoothList();
BoothBean bean = new BoothBean();
bean.setBoothid("");
bean.setBootname("");
bean.setDescription("");

if(null != list && list.size()>0) {
	bean = list.get(0);
}


%>
<form id=boothform>
    ブース選択：
    <select class="tema" name="tema" style="width:200px;">
        <option value="rn001">選択肢1</option>
    </select>
    <input type="button" name="find" value="移動" onclick="move()">
<br>
<br>
<br>
    <table id="main"  width="755" border="1" class="table-header-fixed">
        <thead>
            <tr>
                <th width="50">ID</th>
                <th width="250">ブース名</th>
                <th width="435">コメント</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td width="50"><input maxlength="150"  name="roomid" id="k1" type="text" style="width:245px;" value="<%=bean.getBoothid() %>"></td>
                <td width="250"><input maxlength="150"  name="roomname"  type="text" style="width:245px;" value="<%=bean.getBootname() %>"></td>
                <td width="435" valign="top"">
                    <textarea name="roommessage" cols="50" rows="4" class="example"><%=bean.getDescription() %></textarea>
                </td>
            </tr>
        </tbody>
    </table>
    <table width="７55" border="0px">
        <tr>
            <td width="657"></td>
            <td><input type="button" value="削除" onClick="del()" disabled></td>
            <td><input type="button" value="更新" onClick="send()"></td>
        </tr>
    </table>
</form>
</body>
</html>