<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>

    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="resources"/>

    <title>Title</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>


</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light navbar-static-top">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">

                <a class="navbar-brand">
                    <img src="/images/logo.png" alt="" width="30" height="24" class="d-inline-block align-text-top"/>
                </a>


                <c:if test="${sessionScope.lang.equals('en') or sessionScope.lang == null}">

                    <ul class="nav nav-pills ">
                        <li class="nav-item ">
                            <a class="nav-link active bg-gray-500" aria-current="page"
                               href="${pageContext.request.contextPath}/periodicals?command=changeLang&lang=en&pageName=${param.pageName}">EN</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page"
                               href="${pageContext.request.contextPath}/periodicals?command=changeLang&lang=uk&pageName=${param.pageName}">UA</a>
                        </li>
                    </ul>
                </c:if>

                <c:if test="${sessionScope.lang.equals('uk') }">

                    <ul class="nav nav-pills">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page"
                               href="${pageContext.request.contextPath}/periodicals?command=changeLang&lang=en&pageName=${param.pageName}">EN</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active bg-secondary" aria-current="page"
                               href="${pageContext.request.contextPath}/periodicals?command=changeLang&lang=uk&pageName=${param.pageName}">UA</a>
                        </li>
                    </ul>
                </c:if>

                <ul class="nav nav-pills">
                    <c:if test="${sessionScope.user != null}">


                        <li class="nav-item align-content-end">
                            <a class="nav-link" aria-current="page">Account balance ${sessionScope.user.account}</a>
                        </li>
                    </c:if>

                    <c:if test="${sessionScope.user != null or sessionScope.admin != null}">
                        <li class="nav-item position-absolute top-5 end-0">
                            <a class="nav-link active bg-secondary" aria-current="page"
                               href=${pageContext.request.contextPath}/periodicals?command=logout>${sessionScope.user.name}
                                | LOGOUT</a>
                        </li>
                    </c:if>

                </ul>


            </ul>
        </div>
    </div>
</nav>


</body>
</html>
