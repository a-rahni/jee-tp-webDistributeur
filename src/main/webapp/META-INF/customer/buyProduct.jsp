<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Distributeur 2.0 /></title>
    </head>
    <body>

        <h4> email compte: <c:out value="${sessionScope.user.getEmail()}"/> </h4>
        <h2>Crédit restant : <c:out value="${credit}" /></h2>

        <table>
            <caption>Liste des produit</caption>
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

        <br />

        <form method="POST">
            <fieldset>
                <legend>Ajouter du crédit</legend>
                <p>
                    <label>Montant : </label>
                    <input type="number" name="credit" />
                </p>
                <input type="submit" value="Ajouter" />
            </fieldset>
            <c:if test="${creditError != null}">
                <p style="color:red;"><c:out value="${creditError}" /></p>
            </c:if>
        </form>

        <br />

        <form method="POST">
            <fieldset>
                <legend>Acheter un produit</legend>
                <input type="number" name="productId" />
                <input type="submit" value="Acheter"/>
            </fieldset>
            <c:if test="${productError != null}">
                <p style="color:red;"><c:out value="${productError}" /></p>
            </c:if>
            <c:if test="${insufficientError != null}">
                <p style="color:red;"><c:out value="${insufficientError}" /></p>
            </c:if>
        </form>

    </body>
</html>
