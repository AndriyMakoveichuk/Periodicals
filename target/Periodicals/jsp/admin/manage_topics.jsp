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
    <jsp:param name="pageName" value="/periodicals?command=manageTopics"/>
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
        <a class="nav-link active" href="${pageContext.request.contextPath}/periodicals?command=manageTopics">
            <fmt:message
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
        <th>Title English</th>
        <th>Title Ukrainian</th>
        <th>Edit</th>
        <th>Delete</th>

    </tr>
    </thead>
    <tbody>
    <jsp:useBean id="topicsList" scope="request" type="java.util.List"/>


    <c:forEach var="topic" items="${topicsList}">
        <tr>

            <td>${topic.titleEn}</td>
            <td>${topic.titleUa}</td>

            <td>
                <c:if test="${!sessionScope.id.equals(topic.id.toString())}">
                    <a href="${pageContext.request.contextPath}/periodicals?command=editTopic&id=${topic.id}"
                       class="btn btn-success"> Edit </a>
                </c:if>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/periodicals?command=deleteTopic&id=${topic.id}"
                   class="btn btn-danger"> Delete </a>
            </td>


            <c:if test="${sessionScope.id.equals(topic.id.toString())}">
                <form action="${pageContext.request.contextPath}/periodicals" method="post">
                    <input type="hidden" name="command" value="changeTopic"/>

                    <div class="row g-2">
                        <div class="col-md">
                            <div class="form-floating">
                                <input type="search" class="form-control" id="titleUa" name="titleUa"
                                       value="${topic.titleUa}">
                                <label for="titleUa">Title in UA</label>
                            </div>
                        </div>
                        <div class="col-md">
                            <div class="form-floating">
                                <input type="search" class="form-control" id="titleEn" name="titleEn"
                                       value="${topic.titleEn}">
                                <label for="titleEn">Title in EN</label>
                            </div>
                        </div>

                        <div class="form-check form-switch">
                            <input class="form-check-input" type="checkbox" role="switch" id="flexSwitchCheckDefault"
                                   name="addAsNu">
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
