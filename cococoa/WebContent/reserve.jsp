<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<jsp:useBean id="reserveBean" class="jp.co.cococoa.business.reserve.ReserveBean" scope="session" />
<jsp:useBean id="loginBean" class="jp.co.cococoa.common.bean.AuthInfoBean" scope="session" />
<%@ page import="jp.co.cococoa.business.reserve.ReserveContentsBean" %>
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
    height: 80px;
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

    // 保存時のsubmit
    function update() {
        var form = document.getElementById("reserveform");
        form.action="Reserve";
        form.method = "post";
        setParam(form, "key", "save");
        form.submit();
    }

    // 予約時のsubmit
    function reserve() {
        var form = document.getElementById("reserveform");
        form.action="Reserve";
        form.method = "post";
        setParam(form, "key", "reserve");
        form.submit();
    }

    // 予約取消し時のsubmit
    function cancel() {
        var form = document.getElementById("reserveform");
        form.action="Reserve";
        form.method = "post";
        setParam(form, "key", "cancel");
        form.submit();
    }

    function move(actionName) {
        var form = document.getElementById("reserveform");
        form.action="Main"
        form.method = "post";
        setParam(form, "key", "init")
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
<table width="630" border="0px">
    <tr>
        <td width="220"></>
        <td align="right" valign="top">
            <div id="menu"  valign="top"  align="right">
                <ul>
                    <li><a href="javascript:move('Main')">TOP</a></li>
                    <!-- <li><a href="./reserve.html">RESERVE</a></li>  -->
                    <li><a href="./room.html">BOOTH</a></li>
                    <li><a href="./owner.html">OWNER</a></li>
                </ul>
            </div>
        </td>
    </tr>
</table>

Contents Setting
<form id="reserveform">
<input type="hidden" id="k1" name="reserveno" value="test001">
<table width="615" border="0px">
    <tr>
        <td width="320"></td>
        <td width="50" valign="bottom">予約中</td>
        <td width="130" valign="bottom"></td>
        <td width="70" valign="bottom"><input type="checkbox" name="riyu" value="1">Twitter連携</td>
        <td><input type="button" value="予約実行" onClick="reserve()"></td>
        <td><input type="button" value="予約取消" onClick="cancel()"></td>
    </tr>
</table>

<table id="main" class="table-header-fixed">
    <thead>
        <tr>
            <th width="25">投稿<br>受付</th>
            <th width="10">No</th>
            <th width="30">区分</th>
            <th width="150">コーナータイトル</th>
            <th width="150">コーナー説明<br>今回のお題</th>
            <th width="273">台本</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td width="25"><input type="checkbox" name="collecting" value="<%=reserveBean.getContentsList().get(0).getContentid() %>" disabled></td>
            <td width="10">1</td>
            <td width="30"><input type="hidden" name="idOp" value="<%=reserveBean.getContentsList().get(0).getContentid()%>">オープニング</td>
            <td width="150" valign="top">
                <textarea name="titleOp" cols="18" rows="5" class="example"><%=reserveBean.getContentsList().get(0).getTitle()%></textarea>
            </td>
            <td width="150">
                <textarea name="textOp" cols="18" rows="5" class="example"><%=reserveBean.getContentsList().get(0).getDescription()%></textarea>
            </td>
            <td width="253">
                <textarea name="scriptOp" cols="33" rows="5" class="example"><%=reserveBean.getContentsList().get(0).getScript()%></textarea>
            </td>
        </tr>
        <tr>
            <td width="25">
                <input type="checkbox" name="collecting"  value="<%=reserveBean.getContentsList().get(1).getContentid()%>" <% if(reserveBean.getContentsList().get(1).getCntributflg().equals("1")){%> checked <% }%>>
            </td>
            <td width="10">2</td>
            <td width="30"><input type="hidden" name="idFr" value="<%=reserveBean.getContentsList().get(1).getContentid()%>">フリートーク</td>
            <td width="150" valign="top">
                <textarea name="titleFr" cols="18" rows="5" class="example"><%=reserveBean.getContentsList().get(1).getTitle()%></textarea>
            </td>
            <td width="150">
                <textarea name="textFr" cols="18" rows="5" class="example"><%=reserveBean.getContentsList().get(1).getDescription()%></textarea>
            </td>
            <td width="253">
                <textarea name="scriptFr" cols="33" rows="5" class="example"><%=reserveBean.getContentsList().get(1).getScript()%></textarea>
            </td>
        </tr>
        <tr>
            <td width="25">
                <input type="checkbox" name="collecting"  value="<%=reserveBean.getContentsList().get(2).getContentid()%>" <% if(reserveBean.getContentsList().get(2).getCntributflg().equals("1")){%> checked <% }%>>
            </td>
            <td width="10">3</td>
            <td width="30"><input type="hidden" name="idTm" value="<%=reserveBean.getContentsList().get(2).getContentid()%>">今日のテーマ</td>
            <td width="150" valign="top">
                <textarea name="titleTm" cols="18" rows="5" class="example"><%=reserveBean.getContentsList().get(2).getTitle()%></textarea>
            </td>
            <td width="150">
                <textarea name="textTm" cols="18" rows="5" class="example"><%=reserveBean.getContentsList().get(2).getDescription()%></textarea>
            </td>
            <td width="253">
                <textarea name="scriptTm" cols="33" rows="5" class="example"><%=reserveBean.getContentsList().get(2).getScript()%></textarea>
            </td>
        </tr>
        <tr>
            <td width="25">
                <input type="checkbox" name="collecting"  value="<%=reserveBean.getContentsList().get(3).getContentid()%>" <% if(reserveBean.getContentsList().get(3).getCntributflg().equals("1")){%> checked <% }%>>
            </td>
            <td width="10">4</td>
            <td width="30"><input type="hidden" name="idC1" value="<%=reserveBean.getContentsList().get(3).getContentid()%>">コーナー１</td>
            <td width="150" valign="top">
                <textarea name="titleC1" cols="18" rows="5" class="example"><%=reserveBean.getContentsList().get(3).getTitle()%></textarea>
            </td>
            <td width="150">
                <textarea name="textC1" cols="18" rows="5" class="example"><%=reserveBean.getContentsList().get(3).getDescription()%></textarea>
            </td>
            <td width="253">
                <textarea name="scriptC1" cols="33" rows="5" class="example"><%=reserveBean.getContentsList().get(3).getScript()%></textarea>
            </td>
        </tr>
        <tr>
            <td width="25">
                <input type="checkbox" name="collecting"  value="<%=reserveBean.getContentsList().get(4).getContentid()%>" <% if(reserveBean.getContentsList().get(4).getCntributflg().equals("1")){%> checked <% }%>>
            </td>
            <td width="10">5</td>
            <td width="30"><input type="hidden" name="idC2" value="<%=reserveBean.getContentsList().get(4).getContentid()%>">コーナー２</td>
            <td width="150" valign="top">
                <textarea name="titleC2" cols="18" rows="5" class="example"><%=reserveBean.getContentsList().get(4).getTitle()%></textarea>
            </td>
            <td width="150">
                <textarea name="textC2" cols="18" rows="5" class="example"><%=reserveBean.getContentsList().get(4).getDescription()%></textarea>
            </td>
            <td width="253">
                <textarea name="scriptC2" cols="33" rows="5" class="example"><%=reserveBean.getContentsList().get(4).getScript()%></textarea>
            </td>
        </tr>
        <tr>
            <td width="25">
                <input type="checkbox" name="collecting"  value="<%=reserveBean.getContentsList().get(5).getContentid()%>" disabled>
            </td>
            <td width="10">6</td>
            <td width="30"><input type="hidden" name="idCl" value="<%=reserveBean.getContentsList().get(5).getContentid()%>">クロージング</td>
            <td width="150" valign="top">
                <textarea name="titleCl" cols="18" rows="5" class="example"><%=reserveBean.getContentsList().get(5).getTitle()%></textarea>
            </td>
            <td width="150">
                <textarea name="textCl" cols="18" rows="5" class="example"><%=reserveBean.getContentsList().get(5).getDescription()%></textarea>
            </td>
            <td width="253">
                <textarea name="scriptCl" cols="33" rows="5" class="example"><%=reserveBean.getContentsList().get(5).getScript()%></textarea>
            </td>
        </tr>
    </tbody>
</table>
<table width="615" border="0px">
    <tr>
        <td width="680" style="vertical-align:bottom;">◆投稿用ページURL</td>
        <td><input type="button" value="保存" onClick="update()"></td>
    </tr>
    <tr>
        <td colspan="2">
            <textarea id="posturl"  name="posturl" cols="87" rows="5"></textarea>
        </td>
    </tr>
</table>
</form>
</body>
<script>
var url = window.location.href
var before = "Reserve" ;
var domain = url.replace( /Reserve/g , "" ) ;
var postUrl = domain + "PostMessage?o=<%=loginBean.getOwnerid()%>&b=<%=loginBean.getBoothid()%>&a=<%=loginBean.getBroadcastid()%>&key=init"
var obj = document.getElementById("posturl");
document.getElementById("posturl").value = postUrl;

</script>
</html>