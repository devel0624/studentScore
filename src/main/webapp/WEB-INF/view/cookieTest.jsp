<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cookie Test</title>
</head>
<body>
<a href='http://a.com:8080/cookie-test/write?domain=a.com'>set cookie: domain=a.com, path=/</a><br />
<a href='http://a.com:8080/cookie-test/write?domain=.a.com'>set cookie: domain=.a.com, path=/</a><br />
<a href='http://1.a.com:8080/cookie-test/write?domain=.1.a.com'>set cookie: domain=.1.a.com, path=/</a><br />
<a href='http://1.a.com:8080/cookie-test/more/write?domain=.a.com'>set cookie: domain=.a.com, path=/cookie-test/more</a><br />
<br />

<a href='http://a.com:8080/cookie-test/read'>get cookie: domain=a.com</a><br />
<a href='http://b.com:8080/cookie-test/read'>get cookie: domain=b.com</a><br />
<a href='http://1.a.com:8080/cookie-test/read'>get cookie: domain=1.a.com</a><br />
<a href='http://2.a.com:8080/cookie-test/read'>get cookie: domain=2.a.com</a><br />
<a href='http://1.a.com:8080/cookie-test/more/read'>get cookie: domain=1.a.com, path=/cookie-test/more/read</a><br />

</body>
</html>
