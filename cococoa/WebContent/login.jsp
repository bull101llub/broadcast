<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
html, body {height: 100%;}  /*高さ100%に指定*/
body {
  margin: 0;
  padding: 0;
  position: relative;
  min-width: 216px;  /*中央配置するボックスの横幅*/
  min-height: 140px;  /*中央配置するボックス縦幅*/
  background-color: #2262aa;
}
.centerMiddle {
  margin: -70px 0 0 -109px;  /*縦横の半分をネガティブマージンでずらす*/
  position: absolute;  /*body要素に対して絶対配置*/
  top: 50%;  /*上端を中央に*/
  left: 50%;  /*左端を中央に*/
  width: 216px;  /*横幅*/
  height: 140px;  /*縦幅*/
  background-color: #fff;
  text-align:center;
}
</style>
<script>
    // 行選択時のsubmit
    function send() {

        var form = document.getElementById("loginform");
        form.action="Login"
        	form.method = "post";
        setParam(form, "key", "login")

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

<form id="loginform">
    <div class="centerMiddle" align="center">
        <table border="0">
            <tr>
                <td colspan="2">&nbsp;</td>
            </tr>
            <tr>
                <td width="100" align="right">ID：</td>
                <td width="100"><input name="userid" type="text" required maxlength="6" style="width:85px;" pattern="^[0-9A-Za-z]+$" value="aaa"></td>
            </tr>
            <tr>
                <td width="100" align="right">PW：</td>
                <td width="100"><input name="password" type="password" required maxlength="6" style="width:85px;" pattern="^[0-9A-Za-z]+$" value="bbb"></td>
            </tr>
            <tr>
                <td colspan="2">&nbsp;</td>
            </tr>
            <tr>
                <td width="100" align="center"><input type="button" value="キャンセル"></td>
                <td width="100" align="center"><input type="button"  onclick="send()" value="ログイン"></td>
            </tr>
            <tr>
                <td colspan="2">&nbsp;</td>
            </tr>
        </table>
    </div>
</form>
</body>
</html>