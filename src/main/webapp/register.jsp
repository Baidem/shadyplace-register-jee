
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Business case register</title>
</head>
<body>
<h1>Créer un compte</h1>

<form method="post">
    <div>
        <Label for="nom">Nom</Label>
        <input id="nom" type="text" placeholder="Nom" name="nom">
    </div>

    <div>
        <Label for="prenom">Prénom</Label>
        <input id="prenom" type="text" placeholder="Prénom" name="prenom">
    </div>

    <div>
        <Label for="email">Email</Label>
        <input id="email" type="text" placeholder="Email" name="email">
    </div>

    <div>
        <Label for="password">Mot de passe</Label>
        <input id="password" type="text" placeholder="Mot de passe" name="password">
    </div>

    <div>
        <Label for="confirm_password">Confirmer le mot de passe</Label>
        <input id="confirm_password" type="text" placeholder="Confirmation" name="confirm_password">
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

    <a href="http://localhost:8083/login">J'ai déjà un compte</a>

</form>

</body>
</html>
