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

<form class="form-horizontal well" action="/advisor/add_customer" method="post">
    <fieldset>
        <legend>Enter Information to create a new Customer</legend>
        <div class="control-group">
            <label class="control-label" for="firstname">Firstname</label>
            <div class="controls">
                <input type="text" class="input-xlarge" name="firstname" id="firstname"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="lastname">Lastname</label>
            <div class="controls">
                <input type="text" class="input-xlarge" name="lastname" id="lastname"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="email">Email</label>
            <div class="controls">
                <input type="text" class="input-xlarge" name="email" id="email"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="address">Address</label>
            <div class="controls">
                <input type="text" class="input-xlarge" name="address" id="address"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="zipcode">Zip Code</label>
            <div class="controls">
                <input type="text" class="input-xlarge" name="zipcode" id="zipcode"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="city">City</label>
            <div class="controls">
                <input type="text" class="input-xlarge" name="city" id="city"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label" for="phone">Phone</label>
            <div class="controls">
                <input type="text" class="input-xlarge" name="phone" id="phone"/>
            </div>
        </div>
        <div class="form-actions">
            <input type="submit" class="btn btn-primary" value="Add Customer" />
        </div>
    </fieldset>
</form>

<c:import url="/includes/footer.jsp" />
