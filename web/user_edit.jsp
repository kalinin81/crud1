<%--
  Created by IntelliJ IDEA.
  User: jimmim
  Date: 25.05.2020
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>user edit</title>
</head>
<body>
<form action="/edit" method="post">
    login: <input type="text" name="login" value="${login}"/>
    Password: <input type="text" name="password" value="${password}"/>
    email: <input type="text" name="email" value="${email}"/>
    <br>
    <button type="submit" name="id" value="${id}">edit</button>
</form>

</body>
</html>
