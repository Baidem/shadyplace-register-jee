
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Shadyplace register</title>
</head>
<body>
<h1>Shadyplace</h1>
<h2>Create a user account</h2>

<form method="post">
    <div>
        <Label for="firstname">Firstname</Label>
        <input id="firstname" type="text" placeholder="Firstname" name="firstname">
    </div>

    <div>
        <Label for="lastname">Lastname</Label>
        <input id="lastname" type="text" placeholder="Lastname" name="lastname">
    </div>

    <div>
        <Label for="email">Email</Label>
        <input id="email" type="text" placeholder="Email" name="email">
    </div>
    <div>
        <label>Country of residence</label>
        <select name="country-name">
            <option value=""></option>
            <c:forEach items="${countries}" var="country">
                <option value="${country.name}">${country.name}</option>
            </c:forEach>
        </select>
    </div>


    <div>
        <Label for="password">Password</Label>
        <input id="password" type="text" placeholder="Password" name="password">
    </div>

    <div>
        <Label for="confirm_password">Confirm password</Label>
        <input id="confirm_password" type="text" placeholder="Confirm password" name="confirm_password">
    </div>

    <input type="submit">

    <div class="text-center">
        <c:if test="${errorsHibernate.size() != 0}">
            <ol class="text-danger text-center">
                <c:forEach items="${errorsHibernate}" var="error">
                    <li>${error.message}</li>
                </c:forEach>
            </ol>
        </c:if>
        <c:if test="${errors.size() != 0}">
            <ol class="text-danger text-center">
                <c:forEach items="${errors}" var="error">
                    <li>${error}</li>
                </c:forEach>
            </ol>
        </c:if>
    </div>

    <a href="http://localhost:8083/login">I already have an accounte</a>


</form>

</body>
</html>
