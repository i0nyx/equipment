<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<t:adminConteiner>
<h7>::Заказы::</h7>
<nav>
    <div class="nav nav-tabs" id="nav-tab" role="tablist" >
        <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true" style="color:black">В обработке</a>
        <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false" style="color:black">История</a>
        <a class="nav-item nav-link" id="nav-form-order-tab" data-toggle="tab" href="#nav-form-order" role="tab" aria-controls="nav-order" aria-selected="true" style="color:black">Оформить</a>
    </div>
</nav>
<div class="tab-content" id="nav-tabContent">
    <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
        <h3>Заказы в обработке</h3>
        <table class="table table-striped">
            <thead class="thead-dark">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Дата заказа</th>
                <th scope="col">Кому</th>
                <th scope="col">Описание заказа</th>
                <th scope="col">Статус заказа</th>
                <th scope="col">Действие</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td>${order.id}</td>
                    <td ><fmt:formatDate value="${order.startDate}" pattern="dd-MM-yy HH:mm" /></td>
                    <td >${order.whom}</td>
                    <td >${order.description}</td>
                    <td>
                        <c:choose>
                            <c:when test="${order.state == false}">
                                <span style="color:red">не выполнен</span>
                            </c:when>
                        </c:choose>
                    </td>
                    <td><button type="button" class="order_success btn-success" value="${order.id}">Выполнен</button> </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
        <h3>История заказов</h3>
        <table class="table table-striped">
            <thead class="thead-dark">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Дата заказа</th>
                <th scope="col">Дата исполнения</th>
                <th scope="col">Кому</th>
                <th scope="col">Описание заказа</th>
                <th scope="col">Статус заказа</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="allOrder" items="${allOrders}">
                <tr>
                    <td>${allOrder.id}</td>
                    <td ><fmt:formatDate value="${allOrder.startDate}" pattern="dd-MM-yy HH:mm" /></td>
                    <td ><fmt:formatDate value="${allOrder.endDate}" pattern="dd-MM-yy HH:mm" /></td>
                    <td >${allOrder.whom}</td>
                    <td >${allOrder.description}</td>
                    <td ><c:choose>
                        <c:when test="${allOrder.state == false}">
                            <span style="color:red">не выполнен</span>
                        </c:when>
                        <c:when test="${allOrder.state == true}">
                            <span style="color:green">выполнен</span>
                        </c:when>
                    </c:choose>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="tab-pane fade" id="nav-form-order" role="tabpanel" aria-labelledby="nav-form-order-tab">
        <h3>Оформить заказ</h3>
        <form id="order_form">
            <div class="form-group">
                <label for="whom">Для кого</label>
                <input type="text" class="form-control" id="whom" placeholder="Enter Last Name">
                <small class="form-text text-muted">Можно оставить поле пустым или укажите фамилию для кого заказывается запчасть.</small>
            </div>
            <div class="form-group">
                <label for="description">Описание</label>
                <textarea class="form-control" id="description" cols="10"></textarea>
                <small class="form-text text-muted">что собираетесь заказать</small>
            </div>
            <button type="submit" class="btn btn-primary">подать заявку</button>
        </form>
    </div>
</div>
</t:adminConteiner>
<script src="/resources/admin/js/adminOrder.js"></script>