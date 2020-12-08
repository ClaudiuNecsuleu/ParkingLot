<%-- 
    Document   : user.jsp
    Created on : 10.11.2020, 13:35:05
    Author     : Clau
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pageTemplate pageTitle="Users">
     <h1> Users </h1>
    <c:if test="${pageContext.request.isUserInRole('AdminRole')}">
        <a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/Users/Create" role="button">Add user</a>
    </c:if>

   <h1>Cars</h1>
    <c:forEach var="user" items="${users}" varStatus="status">
        <div class="row">
             <div class="col-md-4">
                 ${user.username}
        </div>
         <div class="col-md-4">
                 ${user.email}
        </div>
         <div class="col-md-4">
                 ${user.position}
        </div>
        </div>
    </c:forEach>
</t:pageTemplate>