<%-- 
    Document   : customer
    Created on : Feb 29, 2012, 8:56:55 PM
    Author     : nico
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/includes/header.jsp" />

<div class="page-header">
    <h1>${customer.firstname} ${customer.lastname} accounts</h1>
</div>

<c:import url="/partials/customer/_details.jsp" />

<c:import url="/partials/_accounts.jsp" />

<c:import url="/includes/footer.jsp" />
