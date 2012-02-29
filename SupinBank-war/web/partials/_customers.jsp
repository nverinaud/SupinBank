<%-- 
    Document   : _customers
    Created on : Feb 29, 2012, 7:42:53 PM
    Author     : nico
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${empty customers}">
        <div class="row">
            <div class="span12">
                <div class="alert alert-block">
                    <h4>No customers found.</h4>
                    Wanna create one ? <a href="${pageContext.servletContext.contextPath}/advisor/customer/new">Click here !</a>
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Email</th>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="customer" items="${customers}">
                    <tr>
                        <td>${customer.firstname} ${customer.lastname}</td>
                        <td>${customer.email}</td>
                        <td><a href="${pageContext.servletContext.contextPath}/advisor/customer?id=${customer.id}">Details</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>
