<%-- 
    Document   : index
    Created on : Feb 9, 2012, 12:01:16 PM
    Author     : nico
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="/includes/header.jsp" />

<div class="page-header">
    <h1>Welcome to SupinBank !</h1>
</div>

<form class="form-horizontal well" action="/signin" method="post">
    <fieldset>
        <legend>Please log in before continue.</legend>
        <div class="control-group">
            <label class="control-label" for="email">Email</label>
            <div class="controls">
                <input type="text" class="input-xlarge" name="email" id="email"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="password">Password</label>
            <div class="controls">
                <input type="password" class="input-xlarge" name="password" id="password"/>
            </div>
        </div>
        <div class="form-actions">
            <input type="submit" class="btn btn-primary" value="Sign In" />
        </div>
    </fieldset>
</form>

<c:import url="/includes/footer.jsp" />
