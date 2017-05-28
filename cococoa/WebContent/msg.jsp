<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
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
    body{
        background-color:#CCFF99
    }
    textarea {
        width: 500px;
        height: 10em;
    }
    textarea.example {
        line-height: 150%;
    }
    .point {
        width:70px;
        text-align:center;
        font-weight: bold;
    }


</style>
<script>
    // 行選択時のsubmit
    function putPoint(pointObj) {

    	var postname =  document.getElementById('postname').innerHTML
    	if(null == postname || "" == postname) {
    		return false;
    	}

    	var messageid =  document.getElementById("messageid").value;

        var star = pointObj.value
        if(null == star) {
        	return;
        }
        var point = 0;
        if(null == star) {
        	return;
        } else if("☆" == star) {
        	point = 1;
        } else if("☆☆" == star) {
        	point = 2;
        } else if("☆☆☆" == star) {
        	point = 3;
        } else if("-☆" == star) {
        	point = -1;
        }
        window.parent.rank.putPoint(point, messageid);
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
<P> </P>
<form name="messageform" id="messageform">
<table>
<tr>
    <td style="hight:100px" colspan="2">
       <span id="ctitile"></span>
    </td>
</tr>
<tr>
    <td style="width:200px;"><span id="postname"></span></td>
    <td colspan="2"><span id="posttime"></span></td>
</tr>
<tr>
<td colspan="2" rowspan="5" valign="top">
    <textarea name="msg" id="msg" cols="30" rows="10" class="example">
    </textarea>
</td>
<td>評価</td>
</tr>
<tr><td><input type="button"  class="point" value="☆☆☆" onclick="putPoint(this)"></td></tr>
<tr><td><input type="button"  class="point" value="☆☆"   onclick="putPoint(this)"></td></tr>
<tr><td><input type="button"  class="point" value="☆"     onclick="putPoint(this)"></td></tr>
<tr><td><input type="button"  class="point" value="-☆"    onclick="putPoint(this)"></td></tr>
</table>
<input type="hidden" id="messageid"  name="messageid" value="">
</form>
</body>
</html>