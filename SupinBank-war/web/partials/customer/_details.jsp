<%-- 
    Document   : _index
    Created on : Feb 29, 2012, 8:57:24 PM
    Author     : nico
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty customer}">
    <div class="row">
        <div class="span10">
            <p>Email: ${customer.email}</p>
        </div>
        <div class="span2">
            <a class="btn btn-primary" href="${pageContext.servletContext.contextPath}/accounts/new?customerid=${customer.id}">Add Account</a>
        </div>
    </div>
    <br/>
</c:if>
