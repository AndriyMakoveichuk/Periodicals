<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="resources"/>
    <title>Home</title>
</head>
<body>
<jsp:include page="/jsp/common/navigationBar.jsp">
    <jsp:param name="pageName" value="/periodicals?command=manageUsers"/>
</jsp:include>


<ul class="nav nav-tabs nav-fill">
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/admin-home"> <fmt:message key="Admin Home"/> </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" aria-current="page"
           href="${pageContext.request.contextPath}/periodicals?command=managePublications"> <fmt:message
                key="Manage publications"/> </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/periodicals?command=manageTopics">
            <fmt:message
                    key="Manage topics"/> </a>
    </li>
    <li class="nav-item">
        <a class="nav-link  active" href="${pageContext.request.contextPath}/periodicals?command=manageUsers"> Manage
            users </a>
    </li>
</ul>

<table class="table table-striped">
    <thead>
    <tr>
        <th>Name</th>
        <th>Surname</th>
        <th>Login</th>
        <th>Account balance</th>
        <th>Block user</th>
        <th>Delete user</th>

    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="usersList" scope="request" type="java.util.List"/>


    <c:forEach var="user" items="${usersList}">
        <tr>

            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td>${user.login}</td>
            <td>${user.account}</td>

            <td>
                <c:choose>
                    <c:when test="${user.isActive()}">
                        <a href="${pageContext.request.contextPath}/periodicals?command=blockUser&id=${user.id}"
                           class="btn btn-danger"> Block </a>
                    </c:when>
                    <c:when test="${!user.isActive()}">
                        <a class="btn btn-secondary"
                           href="${pageContext.request.contextPath}/periodicals?command=blockUser&id=${user.id}"> Unblock </a>
                    </c:when>
                </c:choose>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/periodicals?command=deleteUser&id=${user.id}"
                   class="btn btn-danger"> Delete </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>