<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <div class="navbar navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container">
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <a class="brand" href="#">SupinBank</a>
                <c:choose>
                    <c:when test="${not empty userEmail}">
                        <div class="nav-collapse">
                            <ul class="nav">
                                <c:choose>
                                    <c:when test="${isAdvisor}">
                                        <li><a href="#">List customer</a></li>
                                        <li><a href="${pageContext.servletContext.contextPath}/advisor/add_customer">Add customer</a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li><a href="#">Perform a transfer</a></li>
                                        <li><a href="#">My operations</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </div>
                        <div class="nav-collapse">
                            <p class="navbar-text pull-right">
                                Welcome <strong><c:out value="${userEmail}" /></strong> ! <a href="${pageContext.servletContext.contextPath}/signout">Signout</a>
                            </p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="nav-collapse">
                            <form class="navbar-search pull-right" action="${pageContext.servletContext.contextPath}/signin" method="post">
                                <input type="text" class="search-query span3" name="email" placeholder="Email" />
                                <input type="password" class="search-query span2" name="password" placeholder="Password" />
                                <input type="submit" class="btn" value="Sign In" />
                            </form>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>