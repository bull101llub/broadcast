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
<link rel="stylesheet" type="text/css" href="./css/menu.css" />
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
<script>
    // 行選択時のsubmit
    function update() {
        var postuser = document.getElementById("postname").value;
        var c0010 =  "";
        var c0011 =  "";
        var c0012 =  "";
        var c0013 =  "";

        if (document.getElementById("C0010")) {
        	c0010 = document.getElementById("C0010").value;
        }
        if (document.getElementById("C0011")) {
        	c0011 =  document.getElementById("C0011").value;
        }
        if (document.getElementById("C0012")) {
        	c0012 =  document.getElementById("C0012").value;
        }
        if (document.getElementById("C0013")) {
        	c0013 =  document.getElementById("C0013").value;
        }

        if("" == c0010 && "" == c0011 && "" == c0012 && "" == c0013) {
            alert("いずれかの項目にコメントしてください。");
            return;
        }

    	if("" == postuser) {
            alert("ラジオネームを入力してください。");
            return;
    	}

    	if (!dispflg()) {
            alert("入力可能な文字数を超えています。");
    		return;
    	}


    	if(window.confirm('投稿します。よろしいですか？')){
            var form = document.getElementById("postform");
            form.action="Message";
            form.method = "post";
            setParam(form, "key", "create");

            form.submit();
    	}
    }

    function setParam(form, paramName, value) {
        var input = document.createElement('input');
        input.setAttribute('type', 'hidden');
        input.setAttribute('name', paramName);
        input.setAttribute('value', value);
        form.appendChild(input);
    }

    var limitM=250; //最大文字数
    var limitS=0;  //最小文字数

    function lengthChk(obj){
        var contentsid = obj.getAttribute("id");
        var id = obj.getAttribute("id");
        var str = document.getElementById(id).value;


        val=str.replace(/\s|　/gm,'').length;//空白文字を除いた文字数

        //文字数表示
        document.getElementById('msg1' + contentsid).innerHTML= "<span style='color:blue ;'>"+val+"</span>.";

        //制限文字数を超えた時 max()を実行
        obj.setAttribute("isover", "false");
        if(val>limitM){
            max(contentsid);
            obj.setAttribute("isover", "true");
            return;
        }

        //制限文字数と文字数が同じなら"制限文字数です"を表示
        if(val==limitM){
            document.getElementById('msg2' + contentsid).innerHTML = "<span style='color:red;'></span>";
        } else {
            //残り文字数表示
            document.getElementById('msg2' + contentsid).innerHTML = " <span style='bold;color:blue ;'>"
            +(limitM-val)+"</span>";
        }
    }

    function max(contentsid){ //入力文字数が制限文字数を超えた時
        //超えた文字数表示
        document.getElementById('msg2' + contentsid).innerHTML = "制限を <span style='color:red;'>"
        +Math.abs(limitM-val)+"</span>";
    }

    function dispflg(){
       var elements =  document.getElementsByTagName("textarea");
       var flg = "false";

       for (var i = 0; i < elements.length; ++i) {
           flg = elements[i].getAttribute("isover");
           if ("true" == flg) {
               return false;
           }
       }
       return true;
    }



</script>

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