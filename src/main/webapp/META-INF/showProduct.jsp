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
        <hr />
        
        <c:if test="${sessionScope.user != null}">
            <a href="/webdistributeur/customer/BuyProductServlet">Acheter </a>
        </c:if>
            
        <c:if test="${sessionScope.user == null }">
            <a href="/webdistributeur/LoginServlet">Se Connecter </a>
        </c:if>
        <!-- <a href="/webdistributeur/customer/BuyProductServlet">Se Connecter </a> -->
        <!--<a href="/webdistributeur/customer/LoginServlet">Se Connecter </a> -->
    </body>
</html>