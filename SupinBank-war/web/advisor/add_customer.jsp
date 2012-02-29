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

<form class="form-horizontal well" action="${pageContext.servletContext.contextPath}/advisor/add_customer" method="post">
    <fieldset>
        <legend>New Customer Information</legend>
        <c:choose>
            <c:when test="${not empty errors && errors.containsKey('firstname')}">
                <div class="control-group error">
                    <label class="control-label" for="firstname">Firstname</label>
                    <div class="controls">
                        <input type="text" class="input-xlarge" name="firstname" id="firstname"/>
                        <span class="help-inline"><c:out value="${errors.get('firstname')}"/></span>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="control-group">
                    <label class="control-label" for="firstname">Firstname</label>
                    <div class="controls">
                        <input type="text" class="input-xlarge" name="firstname" id="firstname" value="${customer.getFirstname()}"/>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        
        <c:choose>
            <c:when test="${not empty errors && errors.containsKey('lastname')}">
                <div class="control-group error">
                    <label class="control-label" for="lastname">Lastname</label>
                    <div class="controls">
                        <input type="text" class="input-xlarge" name="lastname" id="lastname"/>
                        <span class="help-inline"><c:out value="${errors.get('lastname')}"/></span>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="control-group">
                    <label class="control-label" for="lastname">Lastname</label>
                    <div class="controls">
                        <input type="text" class="input-xlarge" name="lastname" id="lastname" value="${customer.getLastname()}"/>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        
        <c:choose>
            <c:when test="${not empty errors && errors.containsKey('email')}">
                <div class="control-group error">
                    <label class="control-label" for="email">Email</label>
                    <div class="controls">
                        <input type="text" class="input-xlarge" name="email" id="email"/>
                        <span class="help-inline"><c:out value="${errors.get('email')}"/></span>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="control-group">
                    <label class="control-label" for="email">Email</label>
                    <div class="controls">
                        <input type="text" class="input-xlarge" name="email" id="email" value="${customer.getEmail()}"/>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        
        <c:choose>
            <c:when test="${not empty errors && errors.containsKey('address')}">
                <div class="control-group error">
                    <label class="control-label" for="address">Address</label>
                    <div class="controls">
                        <input type="text" class="input-xlarge" name="address" id="address"/>
                        <span class="help-inline"><c:out value="${errors.get('address')}"/></span>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="control-group">
                    <label class="control-label" for="address">Address</label>
                    <div class="controls">
                        <input type="text" class="input-xlarge" name="address" id="address" value="${customer.getAddress()}"/>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        
        <c:choose>
            <c:when test="${not empty errors && errors.containsKey('zipCode')}">
                <div class="control-group error">
                    <label class="control-label" for="zipCode">Zip Code</label>
                    <div class="controls">
                        <input type="text" class="input-xlarge" name="zipCode" id="zipCode"/>
                        <span class="help-inline"><c:out value="${errors.get('zipCode')}"/></span>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="control-group">
                    <label class="control-label" for="zipCode">Zip Code</label>
                    <div class="controls">
                        <input type="text" class="input-xlarge" name="zipCode" id="zipCode" value="${customer.getZipCode()}"/>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        
        <c:choose>
            <c:when test="${not empty errors && errors.containsKey('city')}">
                <div class="control-group error">
                    <label class="control-label" for="city">City</label>
                    <div class="controls">
                        <input type="text" class="input-xlarge" name="city" id="city"/>
                        <span class="help-inline"><c:out value="${errors.get('city')}"/></span>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="control-group">
                    <label class="control-label" for="city">City</label>
                    <div class="controls">
                        <input type="text" class="input-xlarge" name="city" id="city" value="${customer.getCity()}"/>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>
        
        <c:choose>
            <c:when test="${not empty errors && errors.containsKey('phone')}">
                <div class="control-group error">
                    <label class="control-label" for="phone">Phone</label>
                    <div class="controls">
                        <input type="text" class="input-xlarge" name="phone" id="phone"/>
                        <span class="help-inline"><c:out value="${errors.get('phone')}"/></span>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="control-group">
                    <label class="control-label" for="phone">Phone</label>
                    <div class="controls">
                        <input type="text" class="input-xlarge" name="phone" id="phone" value="${customer.getPhone()}"/>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>

        <div class="form-actions">
            <input type="submit" class="btn btn-primary" value="Add Customer" />
        </div>
    </fieldset>
</form>

<c:import url="/includes/footer.jsp" />
