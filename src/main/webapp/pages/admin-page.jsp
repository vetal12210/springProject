<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/custom-tags.tld" prefix="ct" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<div class="row">
    <div class="mx-auto" style="width: 1000px;">
        <div class="input-group mb-3">
            <h2>Hello, ${username}</h2>
        </div>
    </div>
</div>

<div class="row">
    <div class="mx-auto" style="width: 1000px;">
        <div class="input-group mb-3">
            <a class="link-danger" href="/logout">LOGOUT</a><br/>
        </div>
    </div>
</div>
<div class="row">
    <div class="mx-auto" style="width: 1000px;">
        <div class="input-group mb-3">
            <form action="/admin/add-user" style="margin-bottom: 20px;">
                <button type="submit" class="btn btn-primary">Add user</button>
            </form>
            <ct:adminPageTag users="${users}" username="${username}"></ct:adminPageTag>
        </div>
    </div>
</div>
</body>
</html>