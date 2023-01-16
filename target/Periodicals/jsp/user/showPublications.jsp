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
<jsp:include page="/jsp/common/navigationBar.jsp">
    <jsp:param name="pageName" value="/home"/>
</jsp:include>

<ul class="nav nav-tabs nav-fill">
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/home"> <fmt:message key="Account"/> </a>
    </li>
    <li class="nav-item">
        <a class="nav-link active" aria-current="page"
           href="${pageContext.request.contextPath}/periodicals?command=viewAllPublications"> <fmt:message
                key="Publications list"/> </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/periodicals?command=viewSubscribed"> <fmt:message
                key="Subscribed publications"/> </a>
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
        <th>Subscribe</th>

    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="publicationsList" scope="request" type="java.util.List"/>
    <jsp:useBean id="topicList" scope="request" type="java.util.List"/>
    <jsp:useBean id="totalPages" scope="request" type="java.lang.Integer"/>
    <jsp:useBean id="pageNum" scope="request" type="java.lang.Integer"/>
    <jsp:useBean id="user" scope="session" type="com.epam.periodicals.model.User"/>


    <form action="${pageContext.request.contextPath}/periodicals" method="post">
        <input type="hidden" name="command" value="viewAllPublications"/>

        <div class="row g-2">
            <div class="col-md">
                <div class="form-floating">
                    <input type="search" class="form-control" id="search" name="search">
                    <label for="search">Search by title</label>
                </div>
            </div>
            <div class="col-md">
                <div class="form-floating">
                    <select id="sorting" name="sorting" class="form-select">
                        <option selected value="0">Default</option>
                        <option value="1">By Price</option>
                        <option value="2">By title</option>
                    </select>
                    <label for="sorting">Sort publications</label>
                </div>
            </div>
            <div class="col-md">
                <div class="form-floating">
                    <select id="filter" name="filter" class="form-select">
                        <option selected value="0">Default</option>
                        <c:forEach var="topic" items="${topicList}">
                            <option value="${topic.id}"> ${topic.titleEn}</option>
                        </c:forEach>
                    </select>
                    <label for="filter">Filter publications</label>
                </div>
            </div>
        </div>


        <button type="submit" class="btn btn-success">
            Apply
        </button>

    </form>


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
                <c:if test="${!user.isSubscribed(publication.id)}">
                    <a href="${pageContext.request.contextPath}/periodicals?command=subscribe&id=${publication.id}"
                       class="btn btn-success"> Subscribe </a>
                </c:if>

                <c:if test="${user.isSubscribed(publication.id)}">
                    <a class="btn btn-secondary"> Subscribed </a>
                </c:if>
            </td>


        </tr>
    </c:forEach>


    </tbody>
</table>

<jsp:include page="/jsp/common/Pagination.jsp">
    <jsp:param name="pageName" value="/showPublications"/>
    <jsp:param name="totalPages" value="${totalPages}"/>
    <jsp:param name="pageNum" value="${pageNum}"/>
</jsp:include>

</body>
</html>
