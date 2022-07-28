<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Distributeur 2.0</title>
    </head>
    <body>
        
        <c:if test="${sessionScope.user != null}">
            <h4> email compte: <c:out value="${sessionScope.user.getEmail()}"/> </h4>
        </c:if>
            
        <h1>Gestion des produits</h1>
        
        <table>
            <caption>Liste des produits</caption>
            
            <tr>
                <th>Numéro de produit</th>
                <th>Nom</th>
                <th>Quantité</th>
                <th>Prix</th>
            </tr>
            <c:forEach var="product" items="${stock}">
                <tr>
                    <td><c:out value="${product.getId()}"/></td>
                    <td><c:out value="${product.getName()}"/></td>
                    <td><c:out value="${product.getQuantity()}"/></td>
                    <td><c:out value="${product.getPrice()}"/></td>
                </tr>
            </c:forEach>  
        </table>
        
        <fieldset>
                <legend>Modifier le produit</legend>
                <form method="POST">
                    <label> Numero de produit</label>
                    <input type="number" name="idProduct" value="id" />
                    <input type="submit" name="modif" value="modifier"/>
                    <input type="submit" name="supp" value="supprimer" />       
                </form> 
                <c:if test="${productError != null}">
                    <c:out value="${productError}" />
                </c:if>
        </fieldset>
        <a href="AddProductServlet" >Ajouter un produit</a>
        
    </body>
</html>
