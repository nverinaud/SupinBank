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
                <div class="nav-collapse">
                    <ul class="nav">
                        <li class="active"><a href="#">Home</a></li>
                        <li><a href="#about">About</a></li>
                        <li><a href="#contact">Contact</a></li>
                    </ul>
                </div>
                <c:choose>
                    <c:when test="${not empty userEmail}">
                        <p class="navbar-text pull-right">
                            Welcome <strong><c:out value="${userEmail}" /></strong> ! <a href="signout">Signout</a>
                        </p>
                    </c:when>
                    <c:otherwise>
                        <form class="navbar-search pull-right" action="signin" method="post">
                            <input type="text" class="search-query" name="email" placeholder="Email" />
                            <input type="password" class="search-query input-medium" name="password" placeholder="Password" />
                            <input type="submit" class="btn-inverse btn-small" value="Sign In" />
                        </form>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>