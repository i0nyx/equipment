<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:loginContainer>
    <section>
        <div class="tn-box tn-box-color">
            <a href="/">Главная</a>
        </div>
    </section>
    <c:url value="/j_spring_security_check" var="loginUrl"/>
    <form id="login" class="login" action="${loginUrl}" method="post">
        <c:choose>
            <c:when test="${empty error}">
                <h2>Авторизация</h2>
            </c:when>
            <c:otherwise>
                <h2>${error}</h2>
            </c:otherwise>
        </c:choose>
        <label for="user" class="icon-user">Логин:</label>
        <input class="user" id="user" name="j_username"/>
        <label for="password" class="icon-lock">Пароль:</label>
        <input type="password" class="password" id="password" name="j_password"/>
        <label for="remember"><input type="checkbox" id="remember"/><span class="remember"/> Запомнить меня</label>
        <input type="submit" id="login_button" value="Войти"/>
        <div class="extra">
            <a href="#" class="forgetPassword">Забыл пароль</a><a href="/registration" class="reg">Регистрация</a><%--<a href="#" class="facebook icon-facebook">Facebook</a><a href="#" class="googlePlus icon-google-plus-sign">Google+</a>--%>
        </div>
    </form>
</t:loginContainer>