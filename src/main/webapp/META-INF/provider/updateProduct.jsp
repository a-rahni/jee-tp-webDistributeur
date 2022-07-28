<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Distributeur 2.0</title>
    </head>
    <body>
        <h1>Modifier un produit</h1>
        
        <form method="POST" action="UpdateProductServlet">
            <label> Id: </label><input  name="idProduct" value="<c:out value="${product.getId()}"/>" /> <br>
            <label>Nom: </label><input type="text" value="<c:out value="${product.getName()}" />" name="nameProduct" /> <br>
            <label>Quantité: </label><input type="number" value="<c:out value="${product.getQuantity()}" />" name="qteProduct" /> <br>
            <label>Prix: </label><input type="number" value="<c:out value="${product.getPrice()}" />" name="priceProduct" /> <br>
            <input type="submit" value="Valider" name="valider" />
            
        </form> 
           
            <c:if test="${updateMessage != null}" >
                <p style="color:green;"><c:out value="${updateMessage}" /> </p>
            </c:if>
            <c:if test="${updateError != null}" >
                <p style="color:red;"> <c:out value="${updateError}" /> </p>
            </c:if>
                
            <c:if test="${updateMessage != null || updateError != null}" >
                <p> vous allez être redirigé dans 5 secondes </p>
            </c:if>
        
            
                
    </body>
</html>
