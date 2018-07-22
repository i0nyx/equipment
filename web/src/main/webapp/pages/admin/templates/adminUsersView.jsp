<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:adminConteiner>
    <div class="container">
        <div class="row">
            <div class="col">
                <h3>Пользователи</h3>
                <table class="table table-striped">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col"></th>
                        <th scope="col">Имя/Фамилия</th>
                        <th scope="col">Должность</th>
                        <th scope="col">Телефон/Логин</th>
                        <th scope="col">Кабинет</th>
                        <th scope="col">Дата регистрации</th>
                        <th scope="col">Действие</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${users}">
                        <tr class="row-${user.id}">
                            <td>${user.id}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${user.avatar == 'no_user_image.png'}">
                                        <img src="/resources/images/avatar/${user.avatar}" width='50'/>
                                    </c:when>
                                    <c:otherwise>
                                        <img src="/control/avatar/${user.avatar}" width='50'/>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td class="userName">${user.firstName} ${user.lastName}</td>
                            <td class="position">${user.position}</td>
                            <td class="phone">${user.phoneNumber}</td>
                            <td class="cabinet">${user.cabinetNumber}</td>
                            <td><fmt:formatDate value="${user.dateRegistration}" pattern="dd-MM-yy HH:mm"/></td>
                            <td class="act">
                                <button type="submit" class="btn btn-success btn-sm edit" attr="${user.id}">
                                    редактировать
                                </button>
                                <button type="submit" class="btn btn-danger btn-sm delete" attr="${user.id}">удалить
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!-- HTML-код модального окна -->
    <div id="editModalBox" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Заголовок модального окна -->
                <div class="modal-header">
                    <h5 class="modal-title">Редактирование пользователя <span id="userName"></span></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <!-- Основное содержимое модального окна -->
                <nav>
                    <div class="nav nav-tabs" id="nav-tab" role="tablist">
                        <a class="nav-item nav-link active" id="nav-profile-tab" data-toggle="tab" href="#nav-profile"
                           role="tab" aria-controls="nav-profile" aria-selected="true" style="color:black">Профиль</a>
                        <a class="nav-item nav-link" id="nav-password-tab" data-toggle="tab" href="#nav-password"
                           role="tab" aria-controls="nav-password" aria-selected="false" style="color:black">Пароль</a>
                        <a class="nav-item nav-link" id="nav-avatar-tab" data-toggle="tab" href="#nav-avatar" role="tab"
                           aria-controls="nav-avatar" aria-selected="false" style="color:black">Аватар</a>
                    </div>
                </nav>
                <div class="modal-body">
                    <div class="tab-content" id="nav-tabContent">
                        <div class="tab-pane fade show active" id="nav-profile" role="tabpanel"
                             aria-labelledby="nav-profile-tab">
                            <form id="changeProfileModalForm">
                                <div class="form-group">
                                    <label for="firstName">Имя:</label>
                                    <input type="text" class="form-control" id="firstName" required>
                                        <%--<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>--%>
                                </div>
                                <div class="form-group">
                                    <label for="lastName">Фамилия:</label>
                                    <input type="text" class="form-control" id="lastName" required>
                                </div>
                                <div class="form-group">
                                    <label for="position">Должность:</label>
                                    <input type="text" class="form-control" id="position" required>
                                </div>
                                <div class="form-group">
                                    <label for="cabinetNumber">Номер кабинета:</label>
                                    <input type="text" class="form-control" id="cabinetNumber" required>
                                </div>
                                <div class="form-group">
                                    <label for="phoneNumber">Телефон (логин):</label>
                                    <input type="text" class="form-control" id="phoneNumber" required>
                                </div>
                                <input type="submit" class="btn btn-success" value="сохранить"/>
                            </form>
                        </div>
                        <div class="tab-pane fade show active" id="nav-password" role="tabpanel"
                             aria-labelledby="nav-password-tab"></div>
                        <div class="tab-pane fade show active" id="nav-avatar" role="tabpanel"
                             aria-labelledby="nav-avatar-tab"></div>
                    </div>
                </div>
                <!-- Футер модального окна -->
                    <%--<div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                        &lt;%&ndash;<button type="button" class="btn btn-primary">Сохранить изменения</button>&ndash;%&gt;
                    </div>--%>
            </div>
        </div>
    </div>

</t:adminConteiner>
<script src="/resources/admin/js/adminUsers.js"></script>