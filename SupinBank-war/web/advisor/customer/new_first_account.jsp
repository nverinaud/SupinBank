<%-- 
    Document   : new_first_account
    Created on : Feb 29, 2012, 3:45:20 PM
    Author     : nico
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/includes/header.jsp" />

<div class="page-header">
    <h1>First Account for ${customer.getFirstname()} ${customer.getLastname()}</h1>
</div>

<form class="form-horizontal well" action="${pageContext.servletContext.contextPath}/advisor/customer/new_first_account" method="post">
    <c:import url="/partials/account/_form.jsp" />
</form>

<c:import url="/includes/footer.jsp" />
