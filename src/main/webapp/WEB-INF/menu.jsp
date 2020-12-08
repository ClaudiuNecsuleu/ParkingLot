<%-- 
    Document   : menu
    Created on : 27.10.2020, 12:47:10
    Author     : Clau
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${pageContext.request.contextPath}">Parking lot</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">Home <span class="sr-only">(curent)</span></a>
            </li>
            <c:if test="${pageContext.request.isUserInRole('AdminRole')}">
                <li class="nav-item ${activePage eq 'Cars' ? ' active' : ''}">
                    <a class="nav-link" href="${pageContext.request.contextPath}/Cars">Cars</a>
                </li>
            </c:if>
            <c:if test="${pageContext.request.isUserInRole('ClientRole')}">
                <li class="nav-item ${activePage eq 'Users' ? ' active' : ''}">
                    <a class="nav-link" href="${pageContext.request.contextPath}/Users">Users</a>
                </li>
            </c:if>
            <li class="nav-item ${pageContext.request.requestURI eq '/ParkingLot/about.jsp' ? ' active' : ''}">
                <a class="nav-link" href="${pageContext.request.contextPath}/about.jsp">About</a>
            </li>
       
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <c:choose>
                    <c:when test="${pageContext.request.getRemoteUser() == null}">
                        <a class="nav-link" href="${pageContext.request.contextPath}/Login">Login</a>
                    </c:when>
                    <c:otherwise>
                        <a class="nav-link" href="${pageContext.request.contextPath}/Logout">Logout</a>
                    </c:otherwise>
                </c:choose>
            </li>
        </ul>
    </div>

</nav>