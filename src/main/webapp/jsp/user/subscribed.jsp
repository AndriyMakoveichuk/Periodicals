<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="resources"/>

    <title>Title</title>
</head>
<body>
<jsp:include page="/jsp/common/navigationBar.jsp" >
    <jsp:param name="pageName" value="/home" />
</jsp:include>

<ul class="nav nav-tabs nav-fill">
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/home">  <fmt:message key="Account"/> </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" aria-current="page"
           href="${pageContext.request.contextPath}/periodicals?command=viewAllPublications"> <fmt:message key="Publications list"/> </a>
    </li>
    <li class="nav-item">
        <a class="nav-link active" href="${pageContext.request.contextPath}/periodicals?command=viewSubscribed"> <fmt:message key="Subscribed publications"/> </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/balance"> Account balance </a>
    </li>
</ul>

<table class="table table-striped">
    <thead>
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Price</th>
        <th>Unsubscribe</th>

    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="publicationsList" scope="request" type="java.util.List"/>
    <jsp:useBean id="user" scope="session" type="com.epam.periodicals.model.User"/>




    <c:forEach var="publication" items="${publicationsList}">
        <tr>

            <c:if test="${not empty sessionScope.error}">
                <div class="alert alert-danger" role="alert">
                        ${sessionScope.error}
                </div>
                <c:remove var="error" scope="session"/>
            </c:if>

            <td>${publication.titleEn}</td>
            <td>${publication.descriptionEn}</td>
            <td>${publication.price}</td>
            <td>
                    <a href="${pageContext.request.contextPath}/periodicals?command=unsubscribe&id=${publication.id}"
                       class="btn btn-success"> Unsubscribe </a>
            </td>


        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
