<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div align="center" class="d-flex flex-column">
    <form method="post" action="/login" id="auth_form">
        <input type="login" placeholder="login" name="username"/>
        <input type="password" placeholder="password" name="password"/>
    </form>
</div>
<form action="/registration" method="get" id="reg_form"></form>
<div align="center">
    <button type="submit" class="btn btn-primary" form="auth_form">Sign in</button>
    <button type="submit" class="btn btn-secondary" form="reg_form">Sign Up</button>
</div>
<c:if test="${param.logout != null}">
    <p class="text-danger"><c:out value="You have been logged out."/></p>
</c:if>
<c:if test="${param.error != null}">
    <p class="text-danger"><c:out value="Invalid username and password."/></p>
</c:if>
<c:if test="${reg != null}">
    <p class="text-success"><c:out value="Registration is succesfull."/></p>
</c:if>
</body>
</html>