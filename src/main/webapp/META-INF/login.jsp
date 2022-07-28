<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Distributeur 2.0</title>
    </head>
    <body>
        <h1>Page de connexion</h1>
        
        <form method="POST">
            <fieldset>
                <legend>Formulaire de connexion</legend>
                <p>
                    <label>Email : </label>
                    <input type="text" name="email" placeholder="email@email.com" required />

                    <br />
                    
                    <label>Mot de passe : </label>
                    <input type="password" name="password" required />
                </p>
                <input type="submit" value="Connexion" />
            </fieldset>
            <c:if test="${error != null}">
                <p style="color:red;"><c:out value="${error}" /></p>
            </c:if>
        </form>
    </body>
</html>
