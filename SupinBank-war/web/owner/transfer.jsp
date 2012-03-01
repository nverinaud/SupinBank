<%-- 
    Document   : between_myaccounts
    Created on : Mar 1, 2012, 4:38:37 PM
    Author     : nico
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/includes/header.jsp" />

<div class="page-header">
    <h1>Perform a Transfer</h1>
</div>

<form class="form-horizontal well" action="${pageContext.servletContext.contextPath}/owner/transfer" method="post">
    <c:import url="/partials/operation/_form.jsp" />
</form>

<c:import url="/includes/footer.jsp" />
