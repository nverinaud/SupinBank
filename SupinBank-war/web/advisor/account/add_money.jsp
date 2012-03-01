<%-- 
    Document   : add_money
    Created on : Mar 1, 2012, 11:53:02 AM
    Author     : nico
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/includes/header.jsp" />

<div class="page-header">
    <h1>Add Money : ${account.name}</h1>
</div>

<form class="form-horizontal well" action="${pageContext.servletContext.contextPath}/advisor/account/add_money?accountId=${account.id}" method="post">
    <fieldset>
        <div class="control-group">
            <label class="control-label" for="amount">Amount</label>
            <div class="controls">
                <input type="text" name="amount" id="amount" value="10" />
                <span class="help-inline"> euros</span>
            </div>
        </div>
        <div class="form-actions">
            <input type="submit" class="btn btn-primary" value="Cash $$" />
        </div>
    </fieldset>
</form>

<c:import url="/includes/footer.jsp" />
