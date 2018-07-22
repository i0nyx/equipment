<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:adminConteiner>
    <nav>
        <div class="nav nav-tabs" id="nav-tab" role="tablist">
            <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab"
               aria-controls="nav-home" aria-selected="true" style="color:black">В ремонте</a>
            <a class="nav-item nav-link" id="nav-form-tab" data-toggle="tab" href="#nav-form" role="tab"
               aria-controls="nav-form" aria-selected="false" style="color:black">Добавить</a>
        </div>
    </nav>
    <div class="tab-content" id="nav-tabContent">
        <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
            <h3>Сейчас в ремонте</h3>
            <table class="table table-striped">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Оборудование</th>
                    <th scope="col">модель/марка</th>
                    <th scope="col">дата поступления</th>
                    <th scope="col">номер</th>
                    <th scope="col">заказчик</th>
                    <th scope="col">коммент</th>
                    <th scope="col">статус</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${lists}" var="equip">
                    <tr>
                        <td scope="row">${equip.id}</td>
                        <td>${equip.equipment.name}</td>
                        <td>${equip.equipment.brand} ${equip.equipment.model}</td>
                        <td><fmt:formatDate value="${equip.date}" pattern="dd-MM-yyyy HH:mm"/></td>
                        <td>${equip.equipment.code}</td>
                        <td>
                            <c:choose>
                                <c:when test="${empty equip.whose}">
                                    ${equip.support.lastName} (${equip.support.cabinet})
                                </c:when>
                                <c:otherwise>
                                    ${equip.whose}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${equip.comment}</td>
                        <td>
                            <c:choose>
                                <c:when test="${equip.state == 'true'}">
                                    <span style="color: green;">Выполнено</span>
                                </c:when>
                                <c:when test="${equip.state == 'false'}">
                                    <span style="color: red;">Не выполнено</span>
                                </c:when>
                                <c:otherwise>
                                    ОШИБКА!!!
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="tab-pane fade show" id="nav-form" role="tabpanel" aria-labelledby="nav-form-tab">
            <h2>Добавить</h2>
            <form class="form-horizontal" id="formReceivedRepair">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="applications">Заявки:</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="applications">
                            <option value="0">выбрать</option>
                            <c:forEach items="${applications}" var="app">
                                <option value="${app.id}">
                                    <fmt:formatDate value="${app.date}" pattern="dd-MM-yy HH:mm"/> -
                                        ${app.lastName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="whose">Заказчик:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="whose" placeholder="Enter whose or cabinet number"
                               required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="number">Номер/ИН :</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="number" placeholder="Enter number" required>
                        <label class="control-label" id="number_error"
                               style="display: none; color:red; font-size:10px;">
                            Оборудования с таким номером нет в базе.
                            Проверьте номер или добавьте оборудование в базу
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="date">Дата:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="date" placeholder="yyyy-mm-dd hh:mm">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="comment">Комментарий:</label>
                    <div class="col-sm-10">
                        <textarea class="form-control" rows="4" id="comment" placeholder="Enter comment"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" id="saveReceivedRepair" class="btn btn-default">сохранить</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</t:adminConteiner>
<script src="/resources/admin/js/adminReceivedRepair.js"></script>