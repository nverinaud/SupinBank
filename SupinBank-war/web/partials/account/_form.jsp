<%-- 
    Document   : _account
    Created on : Feb 29, 2012, 3:46:06 PM
    Author     : nico

    Information:
        You should provide a customer object (instance of Customer class) to this partial.
        You can provide an account object (instance of Account class) to this partial for pre-filled form.

    **Warning**:
        This partial only contains the content of the form, not the <form> tag itself.
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fieldset>
    <legend>Account Information</legend>
    <c:choose>
        <c:when test="${not empty errors && errors.containsKey('name')}">
            <div class="control-group error">
                <label class="control-label" for="name">Name</label>
                <div class="controls">
                    <input type="text" class="input-xlarge" name="name" id="name"/>
                    <span class="help-inline"><c:out value="${errors.get('name')}"/></span>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="control-group">
                <label class="control-label" for="name">Name</label>
                <div class="controls">
                    <input type="text" class="input-xlarge" name="name" id="name" value="${account.getName()}"/>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
    
    <div class="control-group">
        <label class="control-label">Interests Plan</label>
        <div class="controls">
            <select name="interestsPlan">
                <c:forEach var="interestsPlanDescription" items="${account.getInterestsPlansDescriptions()}">
                    <option>${interestsPlanDescription}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="form-actions">
        <input type="submit" class="btn btn-primary" value="New Account" />
        <a class="btn" href="${pageContext.servletContext.contextPath}/advisor/customers">Cancel</a>
    </div>
</fieldset>
