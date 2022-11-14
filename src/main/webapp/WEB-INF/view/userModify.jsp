<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>사용자 수정</title>
</head>
<body>
<form method="post" action="/user/${user.id}/modify">
    아이디: ${user.id}<br />
    나이: <input type="text" name="age" value="${user.age}" /><br />
    이름: <input type="text" name="name" value="${user.name}" /><br />
    <input type="submit" />
</form>
</body>
</html>
