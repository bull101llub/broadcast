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
</style>
</head>
<body>
<P> </P>
<form name="messageform">
<table border="0">
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
    <textarea name="msg" cols="30" rows="10" class="example">
    </textarea>
</td>
<td>評価</td>
</tr>
<tr><td><input type="button" value="☆☆☆"></td></tr>
<tr><td><input type="button" value="☆☆"></td></tr>
<tr><td><input type="button" value="☆"></td></tr>
<tr><td><input type="button" value="-☆"></td></tr>
</table>
</form>


</body>
</html>