<%-- 
    Document   : new
    Created on : Mar 1, 2012, 11:22:41 AM
    Author     : nico
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/includes/header.jsp" />

<div class="page-header">
    <h1>New Account : ${customer.firstname} ${customer.lastname}</h1>
</div>

<form class="form-horizontal well" action="${pageContext.servletContext.contextPath}/advisor/account/new?customerId=${customer.id}" method="post">
    <c:import url="/partials/account/_form.jsp" />
</form>

<c:import url="/includes/footer.jsp" />
