<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<t:adminConteiner>
<h7>::Заявки::</h7>
<nav>
    <div class="nav nav-tabs" id="nav-tab" role="tablist" >
        <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true" style="color:black">В обработке</a>
        <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false" style="color:black">История заявок</a>
    </div>
</nav>
<div class="tab-content" id="nav-tabContent">
    <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
        <h3>Поданные заявки</h3>
        <table class="table table-striped">
            <thead class="thead-dark">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Дата</th>
                <th scope="col">Фамилия</th>
                <th scope="col">Кабинет</th>
                <th scope="col">Срочность</th>
                <th scope="col">Статус</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${applications}" var="app">
                <tr>
                    <th scope="row">${app.id}</th>
                    <td><fmt:formatDate value="${app.date}" pattern = "dd-MM-yyyy HH:mm" /></td>
                    <td>${app.lastName}</td>
                    <td>${app.cabinet}</td>
                    <td><c:choose>
                        <c:when test="${app.urgently == 'true'}">
                            <span style="color:red">срочно</span>
                        </c:when>
                        <c:otherwise>
                            <span>не срочно</span>
                        </c:otherwise>
                    </c:choose></td>
                    <td><c:choose>
                        <c:when test="${app.supportStatus == 'PENDING'}">
                            <span style="color:red">в ожидании</span>
                        </c:when>
                        <c:when test="${app.supportStatus == 'PROCESSING'}">
                            <span style="color:orange">выполняется</span>
                        </c:when>
                        <c:when test="${app.supportStatus == 'FULFILLED'}">
                            <span style="color:green">выполнено</span>
                        </c:when>
                        <c:otherwise>
                            <span style="color:black">ошибка</span>
                        </c:otherwise>
                    </c:choose></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
        <h3>История заявок</h3>
        <table class="table table-striped">
            <thead class="thead-dark">
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Дата</th>
                <th scope="col">Фамилия</th>
                <th scope="col">Кабинет</th>
                <th scope="col">Срочность</th>
                <th scope="col">Статус</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${allApplications}" var="allApp">
                <tr>
                    <th scope="row">${allApp.id}</th>
                    <td><fmt:formatDate value="${allApp.date}" pattern = "dd-MM-yyyy HH:mm" /></td>
                    <td>${allApp.lastName}</td>
                    <td>${allApp.cabinet}</td>
                    <td><c:choose>
                        <c:when test="${allApp.urgently == 'true'}">
                            <span style="color:red">срочно</span>
                        </c:when>
                        <c:otherwise>
                            <span>не срочно</span>
                        </c:otherwise>
                    </c:choose></td>
                    <td><c:choose>
                        <c:when test="${allApp.supportStatus == 'PENDING'}">
                            <span style="color:red">в ожидании</span>
                        </c:when>
                        <c:when test="${allApp.supportStatus == 'PROCESSING'}">
                            <span style="color:orange">выполняется</span>
                        </c:when>
                        <c:when test="${allApp.supportStatus == 'FULFILLED'}">
                            <span style="color:green">выполнено</span>
                        </c:when>
                        <c:otherwise>
                            <span style="color:black">ошибка</span>
                        </c:otherwise>
                    </c:choose></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</t:adminConteiner>
<%--<script src="/resources/admin/js/adminApplication.js"></script>--%>