<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<jsp:useBean id="loginBean" class="jp.co.cococoa.common.bean.AuthInfoBean" scope="session" />
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
.tema{
   width:150px;
}
.c1{
   border: 1px;
   margin: 0px;
   overflow: hidden;
   height: 16em;
   width:600px;
}
.c2{
   border: 1px;
   margin: 0px;
   overflow: hidden;
   height: 18em;
   width:600px;
}
.c3{
   border: 1px;
   margin: 0px;
   overflow: hidden;
   height: 16em;
   width:450px;
}
.c4{
   border: 1px;
   margin: 0px;
   overflow: hidden;
   height: 19em;
   width:450px;
}

</style>

<script>
    // 行選択時のsubmit
    function move(actionName) {

        var target = document.getElementById("mainForm");
        target.action = actionName;
        target.method = "post";
        setParam(target, "key", "init")

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
<%
    String ownerid = loginBean.getOwnerid();
    String boothid = loginBean.getBoothid();
    String broadcastid = loginBean.getBroadcastid();

    System.out.println("ownerid" + ownerid);
    System.out.println("boothid" + boothid);
    System.out.println("broadcastid" + broadcastid);

%>

</head>
<body>
<form id="mainForm">
<table border="1px">
    <tr>
        <td valign="top"  align="left" >
            <p class="radioTitle">まいむラジオ
            ＆nbsp;＆nbsp;＆nbsp;<input type="button" name="startbtn" value="開始">
            <input type="button" name="endbtn" value="終了">
            </p>
        </td>
        <td align="right" valign="top">
            <div id="menu" align="right" >
                <ul>
                   <!--  <li><a href="./index.html">TOP</a></li> -->
                    <li><a href="javascript:move('Reserve')">RESERVE</a></li>
                    <li><a href="javascript:move('Booth')">BOOTH</a></li>
                    <li><a href="javascript:move('Owner')">OWNER</a></li>
                </ul>
            </div>
        </td>
    </tr>
    <tr>
        <td valign="top"><iframe class="c2" name="msg" src="msg.jsp"></iframe></td>
        <td valign="top"><iframe class="c4" name="content" src="Content?o=<%=ownerid%>&b=<%=boothid%>&a=<%=broadcastid%>&key=find"></iframe></td>
    </tr>
    <tr>
        <td>

        </td>
        <td>
        </td>
    </tr>
    <tr>
        <td valign="top"><iframe class="c1" name="msglist" src="Message?o=<%=ownerid%>&b=<%=boothid%>&a=<%=broadcastid%>&key=find"></iframe></td>
        <td valign="top"><iframe class="c3" name="rank" src="Rank?a=<%=broadcastid%>&key=init""></iframe></td>
    </tr>
</table>
</form>
</body>
</html>
