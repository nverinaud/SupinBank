<%-- 
    Document   : add_customer
    Created on : Feb 29, 2012, 10:43:40 AM
    Author     : nico
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/includes/header.jsp" />

<div class="page-header">
    <h1>Add Customer</h1>
</div>

<form class="form-horizontal well" action="${pageContext.servletContext.contextPath}/advisor/customer/new" method="post">
    <c:import url="/partials/customer/_form.jsp" />
</form>

<c:import url="/includes/footer.jsp" />
