<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Shadyplace register</title>
    <link rel="icon" type="image/x-icon" href="images/logo-sunny-parasol-16px.png">
    <%@include file="parts/links-css.jsp"%>

</head>
<body class="bg-elegantgray d-flex align-items-center justify-content-center">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-auto mx-1">
                <div class="card bg-bluesky shadow" style="width: 28rem;">
                    <div class="card-body ">
                        <div class="text-center text-light border-bottom border-light pb-2 mb-3">
                            <h1 class="card-title fs-3">
                                <img style="max-height:60px" src="images/logo-main-light.png">
                            </h1>
                            <h2 class="card-subtitle mb-3 fs-4">
                                Create your account
                            </h2>
                        </div>

                        <form method="post" class="m-0">
                            <div class="form-floating mb-3">
                                <input name="firstname" type="text" class="form-control" id="firstname" placeholder="first name">
                                <label for="firstname">First name</label>
                            </div>
                            <div class="form-floating  mb-3">
                                <input name="lastname" type="text" class="form-control" id="lastname" placeholder="last name">
                                <label for="lastname">Last name</label>
                            </div>
                            <div class="form-floating  mb-3">
                                <input name="email" type="email" class="form-control" id="email" placeholder="name@example.com">
                                <label for="email">Email</label>
                            </div>
                            <div class="form-floating mb-3">
                                <select name="country-name" class="form-select" id="floatingSelect" aria-label="Country of residence">
                                    <c:forEach items="${countries}" var="country">
                                        <option value="${country.name}">${country.name}</option>
                                    </c:forEach>
                                </select>
                                <label for="floatingSelect">Country of residence</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input name="password" type="password" class="form-control" id="floatingPassword" placeholder="Password">
                                <label for="floatingPassword">Password</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input name="confirm_password" type="password" class="form-control" id="floatingConfirm" placeholder="Confirm password">
                                <label for="floatingConfirm">Confirm password</label>
                            </div>

                            <div class="row my-1">
                                <div class="col-auto">
                                    <c:if test="${errorsHibernate.size() != 0}">
                                        <c:forEach items="${errorsHibernate}" var="error">
                                            <div class="text-light">${error.message}</div>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${errors.size() != 0}">
                                        <div class="text-light text-center">
                                            <c:forEach items="${errors}" var="error">
                                                <li class="list-group-item list-group-item-danger">${error}</li>
                                            </c:forEach>
                                        </div>
                                    </c:if>
                                </div>
                            </div>

                            <div class="row justify-content-center gap-2">
                                <a href="http://localhost:8083/login" class="col-auto btn btn-secondary shadow-sm">I have an account</a>

                                <button type="submit" class="col-auto btn btn-success shadow-sm">Submit new account</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

<%@include file="parts/scripts-js.jsp"%>

</body>
</html>
