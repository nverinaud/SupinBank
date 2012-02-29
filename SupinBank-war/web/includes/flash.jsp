<%-- 
    Document   : flash
    Created on : Feb 29, 2012, 3:00:27 PM
    Author     : nico
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty flashError}">
    <div class="row">
        <div class="span12">
            <div class="alert alert-error">
                <strong>Errors !</strong><br/>
                <c:out value="${flashError}" />
            </div>
        </div>
    </div>
</c:if>

<c:if test="${not empty flashSuccess}">
    <div class="row">
        <div class="span12">
            <div class="alert alert-success">
                <strong>Success !</strong><br/>
                <c:out value="${flashSuccess}" />
            </div>
        </div>
    </div>
</c:if>

<c:if test="${not empty flashInfo}">
    <div class="row">
        <div class="span12">
            <div class="alert alert-info">
                <strong>Information</strong><br/>
                <c:out value="${flashInfo}" />
            </div>
        </div>
    </div>
</c:if>
