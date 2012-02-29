<%-- 
    Document   : _accounts
    Created on : Feb 29, 2012, 8:57:44 PM
    Author     : nico
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty accounts}">
    <div class="row">
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Balance</th>
                    <th>Interest plan</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="account" items="${accounts}">
                    <tr>
                        <td>${account.id}</td>
                        <td>${account.name}</td>
                        <td>${account.balance}</td>
                        <td>${Account.stringFromInterestsPlan(account.interestsPlan)}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>
