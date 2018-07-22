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
                <li id="cartridge_link">Картриджи</li>
                <li id="printer_link">Принтеры</li>
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
                            <h2>Подать заявку:</h2>
                            <form id="support_form">
                                <div class="form-group">
                                    <label for="lastName">Фамилия</label>
                                    <input type="text" class="form-control form-control-sm" required id="lastName"
                                           aria-describedby="emailHelp" value="${user.lastName}">
                                    <small id="emailHelp" class="form-text text-muted">Ваша фамилия</small>
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
                                <input type="hidden" id="type" value="PRINTER"/>
                                <sec:authorize access="isAuthenticated()">
                                    <input type="hidden" id="user" value="${user.id}"/>
                                </sec:authorize>
                                <button type="submit" class="btn btn-primary">подать заявку</button>
                            </form>
                        </div>
                        <div class="col">
                            <h3>Поданные заявки:</h3>
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
                <div id="printer" style="display: none;">
                    <h3>Последние ремонты принтеров:</h3>
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
                        <c:forEach var="printer" items="${printers}">
                            <tr>
                                <th scope="row">${printer.id}</th>
                                <td><fmt:formatDate value="${printer.receivedRepair.date}"
                                                    pattern="dd-MM-yy HH:mm"/></td>
                                <td><fmt:formatDate value="${printer.date}" pattern="dd-MM-yy HH:mm"/></td>
                                <td>${printer.receivedRepair.equipment.brand} ${printer.receivedRepair.equipment.model}</td>
                                <td>${printer.receivedRepair.equipment.code}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty printer.receivedRepair.whose}">
                                            ${printer.receivedRepair.support.lastName} ${printer.receivedRepair.support.cabinet}
                                        </c:when>
                                        <c:otherwise>
                                            ${printer.receivedRepair.whose}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${printer.descriptionWork}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div id="cartridge" style="display: none;">
                    <h3>Последние заправки и ремонт картриджей:</h3>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Дата</th>
                            <th scope="col">Модель</th>
                            <th scope="col">Номер</th>
                            <th scope="col">Тип работы</th>
                            <th scope="col">Запчасть</th>
                            <th scope="col">Кому</th>
                                <%--<th scope="col">Исполнитель</th>
                                <th scope="col">Комментарий</th>--%>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="cartridge" items="${cartridges}">
                            <tr>
                                <th scope="row">${cartridge.id}</th>
                                <td><fmt:formatDate value="${cartridge.date}" pattern="dd-MM-yy HH:mm"/></td>
                                <td>${cartridge.receivedRepair.equipment.brand} ${cartridge.receivedRepair.equipment.model}</td>
                                <td>${cartridge.receivedRepair.equipment.code}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${cartridge.typeWork eq 'SUBSTITUTION'}">
                                            замена
                                        </c:when>
                                        <c:when test="${cartridge.typeWork eq 'FUELING'}">
                                            заправка
                                        </c:when>
                                        <c:when test="${cartridge.typeWork eq 'SUBSTITUTION_FUELING'}">
                                            замена и заправка
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>${cartridge.part}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${empty cartridge.receivedRepair.whose}">
                                            ${cartridge.receivedRepair.support.lastName} ${cartridge.receivedRepair.support.cabinet}
                                        </c:when>
                                        <c:otherwise>
                                            ${cartridge.receivedRepair.whose}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                    <%--<td >${cartridge.who}</td>
                                    <td >${cartridge.comments}</td>--%>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</t:pagesConteiner>
<script src="/resources/js/cartridge_and_printers.js"></script>
