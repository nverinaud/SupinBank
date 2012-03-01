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

<div class="row">
    <div class="span12">
        <a href="${pageContext.servletContext.contextPath}/db/populate?count=10">
            <div class="alert alert-info">
                Click here to populate the database with the advisor and 10 customers with accounts !<br/>
                <em>(see glassfish logs for information about customer's clear password)</em>
            </div>
        </a>
    </div>
</div>

<form class="form-horizontal well" action="${pageContext.servletContext.contextPath}/signin" method="post">
    <fieldset>
        <legend>Please log in before continue.</legend>
        <div class="control-group success">
            <label class="control-label" for="email">Email</label>
            <div class="controls">
                <input type="text" class="input-xlarge" name="email" id="email" value="advisor@supinbank.com"/>
            </div>
        </div>
        <div class="control-group success">
            <label class="control-label" for="password">Password</label>
            <div class="controls">
                <input type="password" class="input-xlarge" name="password" id="password" value="foobar"/>
                <span class="help-inline">"foobar"</span>
            </div>
        </div>
        <div class="form-actions">
            <input type="submit" class="btn btn-primary" value="Sign In" />
        </div>
    </fieldset>
</form>

<c:import url="/includes/footer.jsp" />
