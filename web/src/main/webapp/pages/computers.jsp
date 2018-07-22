<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:pagesConteiner>
    <nav>
        <div id="menu">
            <ul id="main">
                <li><a href="/">Главная</a></li>
                <li id="computer_link">Компьютеры</li>
                <li id="notebook_link">Ноутбуки</li>
                <li id="support_link">Заявка</li>
                <div id="marker"></div>
            </ul>
        </div>
    </nav>

    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div id="support">
                    <div class="row">
                        <div class="col-sm-4">
                            <h3>Подать заявку:</h3>
                            <form id="support_form">
                                <div class="form-group">
                                    <label for="lastName">Фамилия</label>
                                    <input type="text" class="form-control form-control-sm" required id="lastName"
                                           value="${user.lastName}">
                                    <small id="emailHelp" class="form-text text-muted">Фамилия подающего заявку в
                                        тех.поддержку
                                    </small>
                                </div>
                                <div class="form-group">
                                    <label for="cabinet">Кабинет</label>
                                    <input type="text" class="form-control form-control-sm" required id="cabinet"
                                           value="${user.cabinetNumber}">
                                    <small id="cabinetHelp" class="form-text text-muted">Укажите номер кабинета</small>
                                </div>
                                <div class="form-group">
                                    <label for="comment">Комментарий</label>
                                    <textarea class="form-control" id="comment" cols="10"></textarea>
                                    <small id="commentHelp" class="form-text text-muted">Опишите, что случилось</small>
                                </div>
                                <div class="form-check">
                                    <input type="checkbox" class="form-check-input" id="urgently" value="true">
                                    <label class="form-check-label" for="urgently">СРОЧНО!!!</label>
                                </div>
                                <input type="hidden" id="type" value="COMPUTER"/>
                                <sec:authorize access="isAuthenticated()">
                                    <input type="hidden" id="user" value="${user.id}"/>
                                </sec:authorize>
                                <button type="submit" class="btn btn-primary">подать заявку</button>
                            </form>
                        </div>
                        <div class="col">
                            <h2>Поданные заявки:</h2>
                            <div class="table">
                                <table class="table table-striped">
                                    <thead class="thead-dark">
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Дата</th>
                                        <th scope="col">Фамилия</th>
                                        <th scope="col">Кабинет</th>
                                        <th scope="col">Статус</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="sup" items="${supports}">
                                        <tr>
                                            <td></td>
                                            <td><fmt:formatDate value="${sup.date}" pattern="dd-MM-yy HH:mm"/></td>
                                            <td>${sup.lastName}</td>
                                            <td>${sup.cabinet}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${sup.supportStatus == 'PENDING'}">
                                                        <span style="color:red">в ожидании</span>
                                                    </c:when>
                                                    <c:when test="${sup.supportStatus == 'PROCESSING'}">
                                                        <span style="color:orange">выполняется</span>
                                                    </c:when>
                                                    <c:when test="${sup.supportStatus == 'FULFILLED'}">
                                                        <span style="color:green">выполнено</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span style="color:black">ошибка</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="computer" style="display: none">
                    <h3>Последние ремонты комьпьютеров:</h3>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Дата поступления</th>
                            <th scope="clo">Дата выполнения</th>
                            <th scope="col">Модель</th>
                            <th scope="col">Номер</th>
                            <th scope="col">Кому</th>
                            <th scope="col">Описание работы</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="computer" items="${computers}">
                            <c:if test="${computer.receivedRepair.equipment.type eq 'COMPUTER'}">
                                <tr>
                                    <th scope="row">${computer.id}</th>
                                    <td><fmt:formatDate value="${computer.receivedRepair.date}"
                                                        pattern="dd-MM-yy HH:mm"/></td>
                                    <td><fmt:formatDate value="${computer.date}" pattern="dd-MM-yy HH:mm"/></td>
                                    <td>${computer.receivedRepair.equipment.brand} ${computer.receivedRepair.equipment.model}</td>
                                    <td>${computer.receivedRepair.equipment.code}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${empty computer.receivedRepair.whose}">
                                                ${computer.receivedRepair.support.lastName} ${computer.receivedRepair.support.cabinet}
                                            </c:when>
                                            <c:otherwise>
                                                ${computer.receivedRepair.whose}
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${computer.descriptionWork}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div id="notebook" style="display:none">
                    <h3>Последние ремонты ноутбуков:</h3>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Дата поступления</th>
                            <th scope="clo">Дата выполнения</th>
                            <th scope="col">Модель</th>
                            <th scope="col">Номер</th>
                            <th scope="col">Кому</th>
                            <th scope="col">Описание работы</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="computer" items="${computers}">
                            <c:if test="${computer.receivedRepair.equipment.type eq 'NOTEBOOK'}">
                                <tr>
                                    <th scope="row">${computer.id}</th>
                                    <td><fmt:formatDate value="${computer.receivedRepair.date}"
                                                        pattern="dd-MM-yy HH:mm"/></td>
                                    <td><fmt:formatDate value="${computer.date}" pattern="dd-MM-yy HH:mm"/></td>
                                    <td>${computer.receivedRepair.equipment.brand} ${computer.receivedRepair.equipment.model}</td>
                                    <td>${computer.receivedRepair.equipment.code}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${empty computer.receivedRepair.whose}">
                                                ${computer.receivedRepair.support.lastName} ${computer.receivedRepair.support.cabinet}
                                            </c:when>
                                            <c:otherwise>
                                                ${computer.receivedRepair.whose}
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>${computer.descriptionWork}</td>
                                </tr>
                            </c:if>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</t:pagesConteiner>
<script src="/resources/js/computers.js"></script>
