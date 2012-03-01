<%-- 
    Document   : _operations
    Created on : Mar 1, 2012, 3:29:29 PM
    Author     : nico
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty operations}">
    <table class="table table-bordered table-striped">
        <thead>
            <tr>
                <th>Id</th>
                <th>Date</th>
                <th>Wording</th>
                <th>Amount</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="operation" items="${operations}">
                <tr>
                    <td>${operation.id}</td>
                    <td>${operation.date}</td>
                    <td>${operation.description}</td>
                    <td>${operation.amount} Eur</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>

<c:if test="${empty operations}">
    <div class="row">
        <div class="span12">
            <p class="alert">
                No operations.
            </p>
        </div>
    </div>
</c:if>
