<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시물 수정</title>
</head>
<body>
<form method="post" action="/post/modify">
    <input type="hidden" name="id" value="${post.id}" />
    제목: <input type="text" name="title" value="${post.title}" /><br />
    <textarea name="content" rows="10" cols="80">${post.content}</textarea><br />

    <input type="submit" />
</form>

</body>
</html>
