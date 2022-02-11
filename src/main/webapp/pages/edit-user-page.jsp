<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<c:set var="username">
    <sec:authentication property="principal.username" />
</c:set>
<body>
<p align="right">Admin  ${username} <a href="/logout">(Logout)</a></p>
<div align="center">
    <h1>Edit user</h1>
    <p>Fill out the form (all fields are required)</p>
</div>
<form:form method="post" action="/admin/edit-user" modelAttribute="userFormDto">
    <div align="center" class="d-flex flex-column">
        <div>
            <br>Login:
            <form:input type="login" name="login" path="login" readonly="true"/>
        </div>
        <div>
            <br>Password:
            <form:input type="password" name="password" path="password"/>
            <div>
                <spring:hasBindErrors name="userFormDto">
                    <c:if test="${errors.hasFieldErrors('password')}">
                        <p class="text-danger"><c:out value="${errors.getFieldError('password').defaultMessage}"/></p>
                    </c:if>
                </spring:hasBindErrors>
            </div>
        </div>
        <div>
            <br> Password again:
            <form:input type="password" name="passwordRepeat" path="passwordRepeat"/>
            <div>
                <spring:hasBindErrors name="userFormDto">
                    <c:if test="${errors.hasFieldErrors('passwordRepeat')}">
                        <p class="text-danger"><c:out
                                value="${errors.getFieldError('passwordRepeat').defaultMessage}"/></p>
                    </c:if>
                </spring:hasBindErrors>
            </div>
        </div>
        <div>
            <br> Email Address:
            <form:input type="email" name="email" path="email" required="true"/>
            <div>
                <spring:hasBindErrors name="userFormDto">
                    <c:if test="${errors.hasFieldErrors('email')}">
                        <p class="text-danger"><c:out value="${errors.getFieldError('email').defaultMessage}"/></p>
                    </c:if>
                </spring:hasBindErrors>
            </div>
        </div>
        <div>
            <br> First name:
            <form:input type="text" name="firstName" path="firstName" required="true"/>
            <div>
                <spring:hasBindErrors name="userFormDto">
                    <c:if test="${errors.hasFieldErrors('firstName')}">
                        <p class="text-danger"><c:out value="${errors.getFieldError('firstName').defaultMessage}"/></p>
                    </c:if>
                </spring:hasBindErrors>
            </div>
        </div>
        <div>
            <br>Last name:
            <form:input type="text" name="lastName" path="lastName" required="true"/>
            <div>
                <spring:hasBindErrors name="userFormDto">
                    <c:if test="${errors.hasFieldErrors('lastName')}">
                        <p class="text-danger"><c:out value="${errors.getFieldError('lastName').defaultMessage}"/></p>
                    </c:if>
                </spring:hasBindErrors>
            </div>
        </div>
        <div>
            <br> Birthday:
            <form:input type="date" name="birthday" path="birthday" required="true"/>
            <div>
                <spring:hasBindErrors name="userFormDto">
                    <c:if test="${errors.hasFieldErrors('birthday')}">
                        <p class="text-danger"><c:out value="${errors.getFieldError('birthday').defaultMessage}"/></p>
                    </c:if>
                </spring:hasBindErrors>
            </div>
        </div>
        <div>
            <br> Role:
            <div>
                <form:select id="mySelect" name="role" path="role">
                    <form:option value="1">admin</form:option>
                    <form:option value="2">user</form:option>
                </form:select>
            </div>
            <div>
                <spring:hasBindErrors name="userFormDto">
                    <c:if test="${errors.hasFieldErrors('role')}">
                        <p class="text-danger"><c:out value="${errors.getFieldError('role').defaultMessage}"/></p>
                    </c:if>
                </spring:hasBindErrors>
            </div>
        </div>
        <div>
            <br><form:button type="submit" value="submit" id="submit" class="btn btn-primary">Edit</form:button>
        </div>
    </div>
</form:form>
<form action="/admin">
    <div align="center">
        <button type="submit" class="btn btn-secondary">Cancel</button>
    </div>
</form>
</body>
</html>
