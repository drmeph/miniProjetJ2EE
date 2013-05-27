<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/css/style.css">
        <title>Welcome to the mini projet Web</title>
    </head>
    <body>
        <%
            String message = (String) request.getAttribute("message");

            if (message != null) {
                StringBuilder messageOut = new StringBuilder(message);
                out.print(messageOut+"<br>");
            }
        %>
        <a href="addContact">Créer un nouveau Conctact</a><br>
        <a href="removeContact">Supprimer un Conctact</a><br>
        <a href="updateContact">Mise a jour d'un Conctact</a><br>
        <a href="searchContact">Recherche d'un Conctact</a>
    </body>
</html>