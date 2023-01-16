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
<jsp:useBean id="totalPages" scope="request" type="java.lang.Integer"/>

<nav aria-label="...">
    <ul class="pagination justify-content-center">
        <li class="page-item">
            <a class="page-link"
               href="${pageContext.request.contextPath}/periodicals?command=viewAllPublications&page=${param.pageNum - 1}" tabindex="-1">Previous</a>
        </li>

        <% for(int i = 1; i <= totalPages; i++){ %>

        <li class="page-item">
            <a class="page-link"
               href="${pageContext.request.contextPath}/periodicals?command=viewAllPublications&page=<%=i%>"> <%=i%> </a>
        </li>

        <% } %>
        <li class="page-item">
            <a class="page-link" href="${pageContext.request.contextPath}/periodicals?command=viewAllPublications&page=${param.pageNum + 1}">Next</a>
        </li>
    </ul>
</nav>

</body>
</html>
