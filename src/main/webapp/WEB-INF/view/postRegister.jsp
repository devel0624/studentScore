<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>게시물 등록</title>
</head>
<body>
    <form method="post" action="/post/register">
        제목: <input type="text" name="title" /><br />
        <textarea name="content" rows="10" cols="80"></textarea><br />

        <input type="submit" />
    </form>
</body>
</html>
