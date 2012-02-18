<%-- 
    Document   : listCustomers
    Created on : Feb 10, 2012, 12:02:12 PM
    Author     : nico
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SupinBank | Customers</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <c:choose>
            <c:when test="${not empty customers}">
                <table>
                    <thead>
                        <tr>
                            <td>Name</td>
                            <td>Email</td>
                            <td>Actions</td>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${customers}" var="customer">
                            <tr>
                                <td><c:out value="${customer.firstname} ${customer.lastname}" /></td>
                                <td><c:out value="${customer.email}" /></td>
                                <td></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p>No customers in the database.</p>
            </c:otherwise>
        </c:choose>
    </body>
</html>
