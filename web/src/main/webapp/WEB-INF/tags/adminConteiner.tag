<%@tag description="Simple Wrapper Tag" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Admin panel</title>
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <meta name="generator" content="Bootply"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="/resources/lib/bootstrap/css/bootstrap.css" rel="stylesheet" />
    <%--<link href="/resources/lib/bootstrap/css/bootstrap-responsive.css" rel="stylesheet" />--%>
    <!--[if lt IE 9]>
    <script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <link href="/resources/lib/iconic/css/open-iconic-bootstrap.css" rel="stylesheet" />
    <link href="/resources/admin/css/styles.css" rel="stylesheet">
</head>
<body>
<nav class="navbar sticky-top navbar-dark bg-dark">
    <h5>Панель администратора</h5>
    <div id="user">
    <sec:authorize access="isAuthenticated()">
        <sec:authentication property="principal.username" />
        <a href="/logout"><span class="oi oi-account-logout"></span> ВЫХОД</a>
    </sec:authorize>
    </div>

</nav>
<div class="row">
    <div class="col-sm-2" >
        <jsp:include page="/pages/admin/templates/container/menu.jsp"></jsp:include>
    </div>
    <div class="col-sm-9">
        <jsp:doBody/>
    </div>
</div>
<jsp:include page="/pages/admin/templates/container/footer.jsp"></jsp:include>

<jsp:include page="/include/adminContainer/scripts.jsp"></jsp:include>

</body>
</html>