<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Distributeur 2.0</title>
    </head>
    <body>
        <h1>Ajouter un produit</h1>
        
        <form method="POST" action="AddProductServlet">
            <label> Id: </label><input type="number" name="idProduct" /> <br>
            <label>Nom: </label><input type="text"  name="nameProduct" /> <br>
            <label>Quantité: </label><input type="number"  name="qteProduct" /> <br>
            <label>Prix: </label><input type="number"  name="priceProduct" /> <br>
            <input type="submit" value="Valider" name="valider" />
            
        </form> 
        
        <c:if test="${addMessage != null}" >
                <p style="color:green;"><c:out value="${addMessage}" /> </p>
            </c:if>
            <c:if test="${addError != null}" >
                <p style="color:red;"> <c:out value="${addError}" /> </p>
            </c:if>
                
            <c:if test="${addMessage != null || addError != null}" >
                <p> vous allez être redirigé dans 5 secondes </p>
            </c:if>
    </body>
</html>
