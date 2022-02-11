<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>
<body>
<div align="center">
    <h1>Registration user</h1>
    <p>Fill out the form (all fields are required)</p>
</div>
<form:form method="post" action="/registration" modelAttribute="userFormDto">

    <div align="center" class="d-flex flex-column">
        <div>
            <br>Login:
            <form:input type="login" name="login" path="login" required="true"/>
            <div>
                <spring:hasBindErrors name="userFormDto">
                    <c:if test="${errors.hasFieldErrors('login')}">
                        <p class="text-danger"><c:out value="${errors.getFieldError('login').defaultMessage}"/></p>
                    </c:if>
                </spring:hasBindErrors>
            </div>
        </div>
        <div>
            <br>Password:
            <form:input type="password" name="password" path="password" required="true"/>
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
            <form:input type="password" name="passwordRepeat" path="passwordRepeat" required="true"/>
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
        <div class="g-recaptcha" data-sitekey="6LeFlHYdAAAAAMaDKJvvyhRWAduY6EQt4mp1Mhkg"></div>
        <div>
            <br><form:button type="submit" value="submit" id="submit" class="btn btn-primary">Register</form:button>
        </div>
    </div>
</form:form>
<form action="/login">
    <div align="center">
        <button type="submit" class="btn btn-secondary">Cancel</button>
    </div>
</form>
</body>
</html>
