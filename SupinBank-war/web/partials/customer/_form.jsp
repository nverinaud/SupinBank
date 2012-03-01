<%-- 
    Document   : _form
    Created on : Feb 29, 2012, 3:53:56 PM
    Author     : nico
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fieldset>
    <legend>Customer Information</legend>
    <c:choose>
        <c:when test="${not empty errors && errors.containsKey('firstname')}">
            <div class="control-group error">
                <label class="control-label" for="firstname">Firstname</label>
                <div class="controls">
                    <input type="text" class="input-xlarge" name="firstname" id="firstname" value="${customer.getFirstname()}"/>
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
                    <input type="text" class="input-xlarge" name="lastname" id="lastname" value="${customer.getLastname()}"/>
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
                    <input type="text" class="input-xlarge" name="email" id="email" value="${customer.getEmail()}"/>
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
                    <input type="text" class="input-xlarge" name="address" id="address" value="${customer.getAddress()}"/>
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
                    <input type="text" class="input-xlarge" name="zipCode" id="zipCode" value="${customer.getZipCode()}"/>
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
                    <input type="text" class="input-xlarge" name="city" id="city" value="${customer.getCity()}"/>
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
                    <input type="text" class="input-xlarge" name="phone" id="phone" value="${customer.getPhone()}"/>
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
        <input type="submit" class="btn btn-primary" value="New Customer" />
    </div>
</fieldset>
