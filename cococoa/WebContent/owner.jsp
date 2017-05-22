<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<jsp:useBean id="ownerBean" class="jp.co.cococoa.business.owner.OwnerBean" scope="session" />
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

        var target = document.getElementById("ownerform");
        target.action="Owner"
        target.method = "post";

        var ownerId = document.getElementById("k1").value;

        //オーナーIDが未設定の場合は初期登録へ
        if(typeof ownerId === "undefined" || ownerId == "") {
            setParam(target, "key", "create")
        } else {
            setParam(target, "key", "save")
        }

        target.submit();
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
        <td width="170"></>
        <td align="right" valign="top">
            <div id="menu"  valign="top"  align="right">
                <ul>
                    <li><a href="./index.html">TOP</a></li>
                    <li><a href="./reserve.html">RESERVE</a></li>
                    <li><a href="./room.html">ROM</a></li>
                    <li><a href="./owner.html">OWNER</a></li>
                </ul>
            </div>
        </td>
    </tr>
</table>
Ouner Setting
<form id="ownerform">
<table id="main" class="table-header-fixed">
    <thead>
        <tr>
            <th width="100">id</th>
            <th width="150">name</th>
            <th width="344">comment</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td width="100"><input name="ownerid"  id="k1" type="text" style="width:95px;" readonly="readonly"  value="<%=StringUtil.NVL(ownerBean.getOwnerid(), "")%>"></td>
            <td width="150"><input name="ownername"  id="v1" type="text" style="width:145px;" value="<%=StringUtil.NVL(ownerBean.getOwnername(), "")%>"></td>
            <td width="344" valign="top"">
                <textarea name="description" id="v2"  cols="46" rows="5" class="example"><%=StringUtil.NVL(ownerBean.getDescription(), "")%></textarea>
            </td>
        </tr>
    </tbody>
</table>
</form>
<table width="608" border="1px">
    <tr>
        <td width="590"></td>
        <td><input type="button" value="更新" onclick="send()"></td>
    </tr>
</table>


</body>
</html>