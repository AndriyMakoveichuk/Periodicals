<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>


<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="resources"/>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title><fmt:message key="periodicals"/></title>



    <!-- Font Icon -->
    <link rel="stylesheet"
          href="../../fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>

<jsp:include page="/jsp/common/navigationBar.jsp">
    <jsp:param name="pageName" value="/login"/>
</jsp:include>
<div class="main">

    <!-- Sing in  Form -->
    <section class="sign-in">
        <div class="container">
            <div class="signin-content">
                <div class="signin-image">
                    <figure>
                        <img src="${pageContext.request.contextPath}/images/signIn.png" class="align-self-start" alt="image">
                    </figure>
                    <a href="${pageContext.request.contextPath}/registration" class="signup-image-link"> <fmt:message
                            key="create account"/></a>
                </div>

                <div class="align-self-sm-end align-self-md-center">
                    <h2 class="form-title"><fmt:message key="Sign in"/></h2>
                    <form action="${pageContext.request.contextPath}/periodicals" method="post" class="register-form"
                          id="login-form">

                        <c:if test="${not empty sessionScope.error}">
                            <div class="alert alert-danger" role="alert">
                                    ${sessionScope.error}
                            </div>
                            <c:remove var="error" scope="session"/>
                        </c:if>

                        <input type="hidden" name="command" value="login"/>

                        <div class="form-group">
                            <label for="login"><i
                                    class="zmdi zmdi-account material-icons-name"></i></label> <input
                                type="text" name="login" id="login"
                                placeholder=<fmt:message key="Login"/>/>
                        </div>
                        <div class="form-group">
                            <label for="password"><i class="zmdi zmdi-lock"></i></label> <input
                                type="password" name="password" id="password"
                                placeholder=<fmt:message key="Password"/>/>
                        </div>
                        <div class="form-group">
                            <input type="checkbox" name="remember-me" id="remember-me"
                                   class="agree-term"/> <label for="remember-me"
                                                               class="label-agree-term"><span><span></span></span> <fmt:message key="Remember me"/></label>
                        </div>
                        <div class="form-group form-button">
                            <input type="submit" name="signin" id="signin"
                                   class="btn btn-primary" value="Log in"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>

</div>

<!-- JS -->
<script src="../../js/main.js"></script>
</body>

</html>