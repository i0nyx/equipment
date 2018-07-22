<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:adminConteiner>
    <nav>
        <div class="nav nav-tabs" id="nav-tab" role="tablist">
            <a class="nav-item nav-link active" id="nav-form-tab" data-toggle="tab" href="#nav-form" role="tab"
               aria-controls="nav-form" aria-selected="true" style="color:black">Добавить</a>
            <a class="nav-item nav-link" id="nav-history-tab" data-toggle="tab" href="#nav-history" role="tab"
               aria-controls="nav-history" aria-selected="false" style="color:black">История</a>
        </div>
    </nav>
    <div class="tab-content" id="nav-tabContent">
        <div class="tab-pane fade show active" id="nav-form" role="tabpanel" aria-labelledby="nav-form-tab">
            <h3>Ремонт картриджей</h3>
            <form class="form-horizontal" id="formCartridgeRepair">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="received">В ожидании:</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="received">
                            <option value="0">выбрать</option>
                            <c:forEach var="received" items="${receivedRepairs}">
                                <option value="${received.id}">
                                    <fmt:formatDate value="${received.date}" pattern="dd-MM-yy HH:mm"/>
                                    &nbsp;-&nbsp;(
                                    <c:choose>
                                        <c:when test="${empty received.whose}">
                                            ${received.support.lastName}&nbsp;&nbsp;${received.support.cabinet}
                                        </c:when>
                                        <c:otherwise>
                                            ${received.whose}
                                        </c:otherwise>
                                    </c:choose>
                                    - ${received.equipment.code})
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="type">Тип работы:</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="type">
                            <c:forEach var="type" items="${enumType}">
                                <option value="${type.key}">${type.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="model">Марка/модель:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="model" disabled>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="whom">Кому:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="whom" disabled>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="part">запчасть:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="part" placeholder="Enter part" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="number">Номер картриджа:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="number" disabled>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="who">Кто выполнил:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="who" placeholder="Enter who">
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
                        <input type="hidden" id="id">
                        <button type="submit" id="saveCartridgeRepair" class="btn btn-default">сохранить</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="tab-pane fade" id="nav-history" role="tabpanel" aria-labelledby="nav-history-tab">
            <h3>История заправок картриджей</h3>
            <div class="table">
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
                            <td>${cartridge.id}</td>
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
</t:adminConteiner>
<script src="/resources/admin/js/adminCartridgeRepair.js" charset="utf-8"></script>