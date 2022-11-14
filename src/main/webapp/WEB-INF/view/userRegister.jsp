<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>사용자 등록</title>
</head>
<body>
    <form method="post" action="/user/register">
        아이디: <input type="text" name="id" /><br />
        비밀번호: <input type="password" name="password" /><br />
        나이: <input type="text" name="age" /><br />
        이름: <input type="text" name="name" /><br />
        <input type="submit" />
    </form>
</body>
</html>
