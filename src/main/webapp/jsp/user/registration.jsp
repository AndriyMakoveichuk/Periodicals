<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>


<html>
<head>
    <fmt:setLocale value="${sessionScope.lang}"/>
    <fmt:setBundle basename="resources"/>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up for Periodicals</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
            crossorigin="anonymous"></script>

    <!-- Font Icon -->
    <link rel="stylesheet"
          href="../../fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="../../css/style.css">
</head>
<body>
<jsp:include page="/jsp/common/navigationBar.jsp" >
    <jsp:param name="pageName" value="/registration" />
</jsp:include>
<div class="main">

    <!-- Sign up form -->
    <section class="signup">
        <div class="container">
            <div class="signup-content">
                <div class="signup-form">
                    <h2 class="form-title">Sign up</h2>

                    <form method="post" action="${pageContext.request.contextPath}/periodicals" class="register-form"
                          id="register-form">

                        <c:if test="${not empty sessionScope.error}">
                            <div class="alert alert-danger" role="alert">
                                    ${sessionScope.error}
                            </div>
                            ${sessionScope.remove("error")}
                        </c:if>

                        <input type="hidden" name="command" value="registration">
                        <div class="form-group">
                            <label for="name"><i
                                    class="zmdi zmdi-account material-icons-name"></i></label> <input
                                type="text" name="name" id="name" placeholder="Your Name"/>
                        </div>
                        <div class="form-group">
                            <label for="surname"><i
                                    class="zmdi zmdi-account material-icons-name"></i></label> <input
                                type="text" name="surname" id="surname" placeholder="Your Surname"/>
                        </div>
                        <div class="form-group">
                            <label for="email"><i class="zmdi zmdi-email"></i></label> <input
                                type="email" name="login" id="email" placeholder="Your Email"/>
                        </div>
                        <div class="form-group">
                            <label for="pass"><i class="zmdi zmdi-lock"></i></label> <input
                                type="password" name="pass" id="pass" placeholder="Password"/>
                        </div>
                        <div class="form-group">
                            <label for="re_pass"><i class="zmdi zmdi-lock-outline"></i></label>
                            <input type="password" name="re_pass" id="re_pass"
                                   placeholder="Repeat your password"/>
                        </div>

                        <div class="form-group">
                            <input type="checkbox" name="agree-term" id="agree-term"
                                   class="agree-term" required/> <label for="agree-term"
                                                                        class="label-agree-term"><span><span></span></span>I
                            agree all statements in <a href="#" class="term-service">Terms
                                of service</a></label>
                        </div>
                        <div class="form-group form-button">
                            <input type="submit" name="signup" id="signup"
                                   class="btn btn-primary" value="Register"/>
                        </div>
                    </form>
                </div>
                <div class="signup-image">
                    <figure>
                        <img src="../../images/SignUp.png" alt="sing up image">
                    </figure>
                    <a href="${pageContext.request.contextPath}/login" class="signup-image-link">I am already a
                        member</a>
                </div>
            </div>
        </div>
    </section>


</div>
<!-- JS -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="../../js/main.js"></script>


</body>

</html>