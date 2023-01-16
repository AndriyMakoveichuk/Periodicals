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
    <jsp:param name="pageName" value="/admin-home"/>
</jsp:include>


<ul class="nav nav-tabs nav-fill">
    <li class="nav-item">
        <a class="nav-link active" href="${pageContext.request.contextPath}/admin-home"> <fmt:message key="Admin Home"/> </a>
    </li>
    <li class="nav-item">
        <a class="nav-link" aria-current="page"
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


<section class="vh-10000" style="background-color: #f4f5f7;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col col-lg-6 mb-4 mb-lg-0">
                <div class="card mb-3" style="border-radius: .5rem;">
                    <div class="row g-0">
                        <div class="col-md-4 gradient-custom text-center text-black"
                             style="border-top-left-radius: .5rem; border-bottom-left-radius: .5rem;">
                            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"
                                 alt="Avatar" class="img-fluid my-5" style="width: 80px;"/>
                            <jsp:useBean id="admin" scope="session" type="com.epam.periodicals.model.Admin"/>
                            <h5 class="text-muted">${admin.name} </h5>
                            <p class="text-muted">Admin</p>
                            <i class="far fa-edit mb-5"></i>
                        </div>
                        <div class="col-md-8">
                            <div class="card-body p-4">
                                <h6>Admins information</h6>
                                <hr class="mt-0 mb-4">
                                <div class="row pt-1">
                                    <div class="col-6 mb-3">
                                        <h6>Email</h6>
                                        <p class="text-muted"> ${admin.login}</p>
                                    </div>
                                    <div class="col-6 mb-3">
                                        <h6>Admin ID</h6>
                                        <p class="text-muted">${admin.id}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
</html>
