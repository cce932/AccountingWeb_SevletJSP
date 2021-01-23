<%@taglib prefix="f" uri="https://openhome.cc/jstl/fake" %>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css" />
        <title>會員申請</title>
    </head>

    <body class="all">
        <div class="form">
            <h1>會員申請</h1>

            <f:if test="${requestScope.errors != null}">
                <ul style='color: rgb(255, 0, 0);'>
                    <f:forEach var="error" items="${requestScope.errors}">
                        <li>${error}</li>
                    </f:forEach>
                </ul>
            </f:if>

            <form method='post' action='register'>
                <input type='text' name='email' value='${param.email}' placeholder="電子郵件" size='25' maxlength='100'>
                <input type='text' name='username' placeholder="名稱（最大 16 字元）" value='${param.username}' size='25'
                    maxlength='16'>
                <input type='password' name='password' placeholder="密碼（8 到 16 字元）" size='25' maxlength='16'>
                <input type='password' name='password2' placeholder="確認密碼" size='25' maxlength='16'>
                <input type='text' name='budget' placeholder="月固定預算(不可超過16位數)" size='25' maxlength='16'>

                <tr>
                    <td colspan='2' align='center'><input type='submit' class="buttonR" value='註冊'></td>
                </tr>
            </form>
        </div>

    </body>

    </html>