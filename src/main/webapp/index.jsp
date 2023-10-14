<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Index</title>
    <link rel="icon" type="image/x-icon" href="images/logo-sunny-parasol-16px.png">
    <%@include file="parts/links-css.jsp"%>
    <style>
        html, body {
        height: 100%;
        margin: 0;
        overflow: hidden;
    }
    </style>
</head>
<body class="">
<div class="row text-center index">
    <h1 class="card-title">Business Case : Register Jakarta</h1>
    <br/>
    <a href="user-register" class="card-link">Register</a>
</div>

<%@include file="parts/scripts-js.jsp"%>

</body>
</html>