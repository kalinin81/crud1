<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jimmim
  Date: 22.05.2020
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
</head>
<body>
<form action="/" method="post">
    login: <input type="text" name="login"/>
    Password: <input type="text" name="password"/>
    email: <input type="text" name="email"/>
    <br>
    <input type="submit" name="add" value="add1">
    <input type="submit" name="edit" value="edit1">
    <input type="submit" name="delete" value="delete1">
<%--    <form action="http://ab-w.net/info.php" method="post" name="drop_down_box">--%>
        <select name="className" size="2">
            <%
                String className1 = "DAO.UserJdbcDao";
                String className2 = "DAO.UserHibernateDao";
                String classNameSelected = request.getParameter("className");
                String className = "";
                if (classNameSelected == null) {
                    classNameSelected = className1;
                }
                if (classNameSelected.equals(className1)) {
                    className = className2;
                } else {
                    className = className1;
                }
            %>
<%--
            %>
            Hello, world !
            <%            } else {
            %>
            Hello, world ! I'm <%= name%>
            <%
                }
            %>
            <option value="DAO.UserJdbcDao">DAO.UserJdbcDao</option>
            <option selected="selected" value="DAO.UserHibernateDao">DAO.UserHibernateDao</option>
--%>
            <option value="<%= className%>"><%= className%></option>
            <option selected="selected" value="<%= classNameSelected%>"><%= classNameSelected%></option>
        </select>
<%--    </form>--%>
</form>
<br>
<table>
    <tr>
        <th> </th>
        <th>login</th>
        <th>password</th>
        <th>email</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.password}"/></td>
            <td><c:out value="${user.email}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
