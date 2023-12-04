<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Shadyplace register</title>
    <link rel="icon" type="image/x-icon" href="images/logo-sunny-parasol-16px.png">
    <%@include file="parts/links-css.jsp"%>

</head>
<body>

<div class="container-md pt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">

            <form method="post" class="bg-bluesky rounded-3 p-3 shadow">
                <h1 class="card-title text-center">
                    <img style="max-height:60px" src="images/logo-sunny-parasol-60px.png">
                    Shadyplace
                </h1>
                <h2 class="card-subtitle text-center mb-3">Create a user account</h2>

                <div class="row justify-content-center">
                    <div class="col-lg-8">
                        <div class="">
                            <div class="mb-3">
                                <label for="firstname" class="form-label">Firstname</label>
                                <input id="firstname" type="text" placeholder="Firstname" name="firstname" class="form-control shadow-sm">
                            </div>

                            <div class="mb-3">
                                <label for="lastname" class="form-label">Lastname</label>
                                <input id="lastname" type="text" placeholder="Lastname" name="lastname" class="form-control shadow-sm">
                            </div>

                            <div class="mb-3">
                                <label for="email" class="form-label">Email</label>
                                <input id="email" type="text" placeholder="Email" name="email" class="form-control shadow-sm">
                            </div>

                            <div class="mb-3">
                                <label class="form-label">Country of residence</label>
                                <select name="country-name" class="form-select">
                                    <c:forEach items="${countries}" var="country">
                                        <option value="${country.name}">${country.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="password" class="form-label">Password</label>
                                <input id="password" type="text" placeholder="Password" name="password" class="form-control">
                            </div>

                            <div class="mb-3">
                                <label for="confirm_password" class="form-label">Confirm password</label>
                                <input id="confirm_password" type="text" placeholder="Confirm password" name="confirm_password" class="form-control">
                            </div>

                            <input type="submit" class="btn btn-success mb-3">

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

                            <a href="http://localhost:8083/login" class="card-link">I already have an account</a>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@include file="parts/scripts-js.jsp"%>

</body>
</html>
