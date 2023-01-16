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
    <jsp:param name="pageName" value="/periodicals?command=managePublications"/>
</jsp:include>


<ul class="nav nav-tabs nav-fill">
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/admin-home"> <fmt:message key="Admin Home"/> </a>
    </li>
    <li class="nav-item">
        <a class="nav-link active" aria-current="page"
           href="${pageContext.request.contextPath}/periodicals?command=managePublications"> <fmt:message
                key="Manage publications"/> </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/periodicals?command=manageTopics"> <fmt:message
                key="Manage topics"/> </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="${pageContext.request.contextPath}/periodicals?command=manageUsers"> Manage users </a>
    </li>
</ul>

<c:if test="${not empty sessionScope.error}">
    <div class="alert alert-danger" role="alert">
            ${sessionScope.error}
    </div>
    <c:remove var="error" scope="session"/>
</c:if>

<table class="table table-striped">
    <thead>
    <tr>
        <th>Title</th>
        <th>Description</th>
        <th>Price</th>
        <th>Edit</th>
        <th>Delete</th>

    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="publicationsList" scope="request" type="java.util.List"/>
    <jsp:useBean id="topicsList" scope="request" type="java.util.List"/>


    <c:forEach var="publication" items="${publicationsList}">
        <tr>

            <td>${publication.titleEn}</td>
            <td>${publication.descriptionEn}</td>
            <td>${publication.price}</td>
            <td>
                <c:if test="${!sessionScope.id.equals(publication.id.toString())}">
                <a href="${pageContext.request.contextPath}/periodicals?command=editPublication&id=${publication.id}"
                   class="btn btn-success"> Edit </a>
                </c:if>
            </td>
            <td>
                    <a href="${pageContext.request.contextPath}/periodicals?command=deletePublication&id=${publication.id}"
                       class="btn btn-danger"> Delete </a>
            </td>


            <c:if test="${sessionScope.id.equals(publication.id.toString())}">
                <form action="${pageContext.request.contextPath}/periodicals" method="post">
                    <input type="hidden" name="command" value="changePublication"/>

                    <div class="row g-2">
                        <div class="col-md">
                            <div class="form-floating">
                                <input type="search" class="form-control" id="titleUa" name="titleUa" value="${publication.titleUa}">
                                <label for="titleUa">Title in UA</label>
                            </div>
                        </div>
                        <div class="col-md">
                            <div class="form-floating">
                                <input type="search" class="form-control" id="titleEn" name="titleEn" value="${publication.titleEn}">
                                <label for="titleEn">Title in EN</label>
                            </div>
                        </div>
                        <div class="col-md">
                            <div class="form-floating">
                                <input type="search" class="form-control" id="descriptionUa" name="descriptionUa" value="${publication.descriptionUa}">
                                <label for="descriptionUa">Description in UA</label>
                            </div>
                        </div>
                        <div class="col-md">
                            <div class="form-floating">
                                <input type="search" class="form-control" id="descriptionEn" name="descriptionEn" value="${publication.descriptionEn}">
                                <label for="descriptionEn">Description in EN</label>
                            </div>
                        </div>
                        <div class="col-md">
                            <div class="form-floating">
                                <input type="search" class="form-control" id="price" name="price" value="${publication.price}">
                                <label for="price">Price</label>
                            </div>
                        </div>
                        <div> Select topics </div>
                        <select class="form-select" multiple aria-label="multiple select example" name="topics">
                            <c:forEach var="topic" items="${topicsList}">
                            <option value=${topic.id}>${topic.titleEn}</option>
                            </c:forEach>
                        </select>

                        <div class="form-check form-switch">
                            <input class="form-check-input" type="checkbox" role="switch" id="flexSwitchCheckDefault" name="addAsNu">
                            <label class="form-check-label" for="flexSwitchCheckDefault">Add publication as new</label>
                        </div>



                    </div>

                    <button type="submit" class="btn btn-success">
                        Change
                    </button>

                </form>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>







</body>
</html>
