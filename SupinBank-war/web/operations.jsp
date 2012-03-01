<%-- 
    Document   : operations
    Created on : Mar 1, 2012, 3:28:21 PM
    Author     : nico
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/includes/header.jsp" />

<a href="${pageContext.servletContext.contextPath}/customer?id=${owner.id}">< Back to ${owner.firstname} ${owner.lastname}</a>

<div class="page-header">
    <h1>Operations</h1>
</div>

<form class="form-horizontal well" action="${pageContext.servletContext.contextPath}/operations" method="get">
    <div class="control-group">
        <label class="control-label">Select your account</label>
        <div class="controls">
            <select name="accountId">
                <c:forEach var="account" items="${accounts}">
                    <c:choose>
                        <c:when test="${account.id eq selectedAccountId}">
                            <option selected="selected" value="${account.id}">${account.name}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${account.id}">${account.name}</option>
                        </c:otherwise>
                    </c:choose>
                    
                </c:forEach>
            </select>
            <input class="btn btn-primary" type="submit" value="Select" />
        </div>
    </div>
</form>


<c:import url="/partials/_operations.jsp" />

<c:import url="/includes/footer.jsp" />
