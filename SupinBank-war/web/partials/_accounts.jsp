<%-- 
    Document   : _accounts
    Created on : Feb 29, 2012, 8:57:44 PM
    Author     : nico
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty accounts}">
    <table class="table table-bordered table-striped">
        <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Balance</th>
                <th>Interest plan</th>
                <c:if test="${isAdvisor}">
                    <th></th>
                </c:if>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="account" items="${accounts}">
                <tr>
                    <td>${account.id}</td>
                    <td><a href="${pageContext.servletContext.contextPath}/operations?accountId=${account.id}">${account.name}</a></td>
                    <td>${account.balance} Eur</td>
                    <td>${account.interestsPlanDescription}</td>
                    <c:if test="${isAdvisor}">
                        <td><a class="btn" href="${pageContext.servletContext.contextPath}/advisor/account/add_money?accountId=${account.id}">Add Money $$</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>

<c:if test="${empty accounts}">
    <div class="row">
        <div class="span12">
            <p class="alert">
                No accounts found.
            </p>
        </div>
    </div>
</c:if>
