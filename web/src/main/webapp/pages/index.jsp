<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:index>
    <sec:authorize access="isAuthenticated()">
        <section>
            <div class="tn-box tn-box-color">
                <p>Здравствуйте ${user.firstName} ${user.lastName}.</p>
                <p>Ваш логин: <sec:authentication property="principal.username" /></p>
                <a href="/logout">Выйти</a>
            </div>
        </section>
    </sec:authorize>
    <sec:authorize access="!isAuthenticated()">
        <section>
            <div class="tn-box tn-box-color">
                <a href="/login">Войти</a>
            </div>
        </section>
    </sec:authorize>

    <div class="menu">
        <div class="btn trigger">
            <span class="line"></span>
        </div>
        <div class="icons">
            <div class="rotater">
                <div class="btn btn-icon">
                    <a href="/profile"><i class="fa fa-user"></i></a>
                </div>
            </div>
            <div class="rotater">
                <div class="btn btn-icon">
                    <a href="/cartridges_and_printers"><i class="fa fa-print"></i></a>
                </div>
            </div>
            <div class="rotater">
                <div class="btn btn-icon">
                    <a href="/computers"><i class="fa fa-laptop"></i></a>
                </div>
            </div>
            <div class="rotater">
                <div class="btn btn-icon">
                    <a href="/order"><i class="fa fa-gears"></i></a>
                </div>
            </div>
            <div class="rotater">
                <%--<div class="btn btn-icon">
                    <a href="/order"><i class="fa fa-gears"></i></a>
                </div>--%>
            </div>
            <div class="rotater">
                <div class="btn btn-icon ">
                    <ul class="ttw-notification-menu" id="notification" style="display:none">
                        <li class="notification-menu-item first-item"><a href="#" id="countNotifications"></a></li>
                    </ul>
                    <a href="/admin"><i class="fa fa-wrench"></i></a>
                </div>
            </div>
                <%--<div class="rotater">
                    <div class="btn btn-icon">
                        <i class="fa fa-gears"></i>
                    </div>
                </div>
                <div class="rotater">
                    <div class="btn btn-icon">
                        <i class="fa fa-dribbble"></i>
                    </div>
                </div>--%>

                <%--<div class="rotater">
                    <div class="btn btn-icon">
                        <i class="fa fa-github"></i>
                    </div>
                </div>
                <div class="rotater">
                    <div class="btn btn-icon">
                        <i class="fa fa-user"></i>
                    </div>
                </div>--%>
        </div>
    </div>

</t:index>
