<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
 <title>Title</title>
</head>
<body>
<%--save 는 상대경로이다. --%>
<form action="save" method="post">
     username: <input type="text" name="username" />
     age: <input type="text" name="age" />
 <button type="submit">전송</button>
</form>
</body>
</html>