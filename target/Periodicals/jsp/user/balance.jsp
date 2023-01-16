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
        <a class="nav-link" href="${pageContext.request.contextPath}/periodicals?command=viewSubscribed"> <fmt:message key="Subscribed publications"/> </a>
    </li>
    <li class="nav-item">
        <a class="nav-link active" href="${pageContext.request.contextPath}/balance"> Account balance </a>
    </li>
</ul>

<section class="vh-10000" style="background-color: #f4f5f5;">
    <div class="container py-5 h-100">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col col-lg-6 mb-4 mb-lg-0">
                <div class="card mb-3" style="border-radius: .5rem;">
                    <div class="row g-0">
                        <div class="col-md-4 gradient-custom text-center text-black"
                             style="border-top-left-radius: .5rem; border-bottom-left-radius: .5rem;">
                            <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava1-bg.webp"
                                 alt="Avatar" class="img-fluid my-5" style="width: 80px;" />
                            <jsp:useBean id="user" scope="session" type="com.epam.periodicals.model.User"/>
                            <h5 class="text-muted">${user.name} ${user.surname}</h5>
                            <p class="text-muted">User</p>
                            <i class="far fa-edit mb-5"></i>
                        </div>
                        <div class="col-md-8 align-content-center">
                            <div class="card-body p-4">
                                <h6>Balance replenishment</h6>
                                <hr class="mt-0 mb-4">
                                <div class="row pt-1">
                                    <div class="col-6 mb-3">
                                        <h6>Account balance</h6>
                                        <p class="text-muted">${user.account}</p>
                                    </div>
                                    <form action="${pageContext.request.contextPath}/periodicals" method="post">
                                        <input type="hidden" name="command" value="addBalance"/>
                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="sum">
                                                Enter sum to add to your balance
                                            </label>
                                            <br/>

                                            <input class="form-range" type="range" id="sum" name="sum"
                                                   min="1" max="500" step="0.5" value="100"
                                                   oninput="this.nextElementSibling.value = this.value" required>
                                            <output>100</output>
                                            <br/>
                                            <fmt:message key="button.top-up" var="topUp"/>
                                            <input type="submit" class="btn btn-success"
                                                   role="button" value="${topUp}" aria-pressed="true"/>
                                        </div>
                                    </form>

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
