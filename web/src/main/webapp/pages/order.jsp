<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:pagesConteiner>
    <nav>
        <div id="menu">
            <ul id="main">
                <li><a href="/">Главная</a></li>
                <li id="orderLink">Заявки</li>
                <li id="historyOrderLink">История</li>
                <div id="marker"></div>
            </ul>
        </div>
    </nav>
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div id="orderView">
                    <h3>Новые заказы</h3>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Дата заказа</th>
                            <th scope="col">Кому</th>
                            <th scope="col">Описание заказа</th>
                            <th scope="col">Статус заказа</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="order" items="${orders}">
                            <tr>
                                <th scope="row">${order.id}</th>
                                <td><fmt:formatDate value="${order.startDate}" pattern="dd-MM-yy HH:mm"/></td>
                                <td>${order.whom}</td>
                                <td>${order.description}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${order.state == false}">
                                            <span style="color:red">не выполнен</span>
                                        </c:when>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div id="historyOrderView" style="display: none;">
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
                                <td><fmt:formatDate value="${allOrder.startDate}" pattern="dd-MM-yy HH:mm"/></td>
                                <td><fmt:formatDate value="${allOrder.endDate}" pattern="dd-MM-yy HH:mm"/></td>
                                <td>${allOrder.whom}</td>
                                <td>${allOrder.description}</td>
                                <td><c:choose>
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
            </div>
        </div>
    </div>
</t:pagesConteiner>
<script src="/resources/js/order.js"></script>