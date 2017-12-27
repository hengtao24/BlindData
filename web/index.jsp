<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>用户登录</title>
</head>

<body>
    <div>
    <div>
    <div>
    <h1>用户登录</h1>
    <form id="loginForm" method="post" action="login.do" onsubmit="return checkForm()">
        <table>
        ${message }
        <tr>
          <td> 用户名: </td>
          <td>
            <input class="text" type="text" name="userName" value="${userName}"
                 onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span>
          </td>
        </tr>
        <tr>
          <td>登录密码：</td>
              <td><input class="text" type="password" id="passWord" name="passWord"
              value="${passWord }" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
        </tr>
        <tr>
          <td>验证码：</td>
          <td><input  type="text" name="veryCode" onfocus="FocusItem(this)"
            onblur="CheckItem(this);" /><img  src="" /><span></span></td>
        </tr>
        <tr>
          <td></td>
          <td>
            <input type="submit" name="submit" value="立即登录"/></label>
          </td>
        </tr>
        </table>
    </form>
    </div>
    </div>
    </div>
</body>
</html>