<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<t:pagesConteiner>
    <nav>
        <div id="menu">
            <ul id="main">
                <li><a href="/">Главная</a></li>
                <li id="support_link">Мои заявки</li>
                <li id="profile_link">Профиль</li>
                <div id="marker"></div>
            </ul>
        </div>
    </nav>
    <div class="container">
        <div class="row">
            <div class="col-sm-2" style="text-align: center; width:200px;">
                <div style="background: white; width:250px; margin-top:65px;">
                    <div>${user.firstName} ${user.lastName}</div>
                    <div>
                        <c:choose>
                            <c:when test="${user.avatar == 'no_user_image.png'}">
                                <img src="/resources/images/avatar/${user.avatar}" width="200"/>
                            </c:when>
                            <c:otherwise>
                                <img src="/control/avatar/${user.avatar}" width="200"/>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div>${user.position}</div>
                    <br/>
                </div>
                <div style="width:250px;">
                    <p>
                        <button type="button" class="btn btn-success btn-sm" id="change-foto-link">Изменить фото
                        </button>
                    </p>
                    <p>
                        <button type="button" class="btn btn-success btn-sm" id="change-profile-link">Изменить данные
                        </button>
                    </p>
                    <p>
                        <button type="button" class="btn btn-success btn-sm" id="change-password-link">Изменить пароль
                        </button>
                    </p>
                    <p>
                        <button type="button" class="btn btn-danger btn-md" id="logout-link">ВЫЙТИ</button>
                    </p>
                </div>
            </div>
            <div class="col-sm-10">
                <div id="profile_view" style="margin-left: 100px;">
                    <h3>Данные профиля</h3>
                    <div style="font-size:18px; margin-left: 40px;">
                        Имя: ${user.firstName}<br/>
                        Фамилия: ${user.lastName}<br/>
                        Должность: ${user.position}<br/>
                        Номер кабинета: ${user.cabinetNumber}<br/>
                        Телефон (логин): ${user.phoneNumber}<br/>
                        Дата регистрации: <fmt:formatDate value="${user.dateRegistration}" pattern="dd-MM-yyyy H:m"/>
                        <br/>
                    </div>
                </div>
                <div id="support_view" style="display:none;margin-left: 100px;">
                    <h3>Поданные заявки</h3>
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Дата</th>
                            <th scope="col">Срочность</th>
                            <th scope="col">Комментарий</th>
                            <th scope="col">Статус</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${userSupports}" var="support">
                            <tr>
                                <td>${support.id}</td>
                                <td><fmt:formatDate value="${support.date}" pattern="dd-MM-yyyy HH:mm"/></td>
                                <td><c:choose>
                                    <c:when test="${support.urgently == 'true'}">
                                        <span style="color:red">срочно</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span>не срочно</span>
                                    </c:otherwise>
                                </c:choose></td>
                                <td>${support.comment}</td>
                                <td><c:choose>
                                    <c:when test="${support.supportStatus == 'PENDING'}">
                                        <span style="color:red">в ожидании</span>
                                    </c:when>
                                    <c:when test="${support.supportStatus == 'PROCESSING'}">
                                        <span style="color:orange">выполняется</span>
                                    </c:when>
                                    <c:when test="${support.supportStatus == 'FULFILLED'}">
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
                <div id="change_profile_view" style="display: none; margin-left: 100px;">
                    <h3>Данные профиля</h3>
                    <div style="font-size:18px; margin-left: 40px;">
                        <div id="changeError" style="display: none; color:red">Ошибка! Проверьте, что бы все поля были
                            заполнены.
                        </div>
                        <form id="changeProfileForm">
                            <input type="hidden" class="userId" value="${user.id}"/>
                            <div class="form-group">
                                <label for="firstName">Имя:</label>
                                <input type="text" class="form-control" id="firstName" value="${user.firstName}">
                                    <%--<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>--%>
                            </div>
                            <div class="form-group">
                                <label for="lastName">Фамилия:</label>
                                <input type="text" class="form-control" id="lastName" value="${user.lastName}">
                            </div>
                            <div class="form-group">
                                <label for="position">Должность:</label>
                                <input type="text" class="form-control" id="position" value="${user.position}">
                            </div>
                            <div class="form-group">
                                <label for="cabinetNumber">Номер кабинета:</label>
                                <input type="text" class="form-control" id="cabinetNumber"
                                       value="${user.cabinetNumber}">
                            </div>
                            <div class="form-group">
                                <label for="phoneNumber">Телефон (логин):</label>
                                <input type="text" class="form-control" id="phoneNumber" value="${user.phoneNumber}">
                            </div>
                            <input type="submit" class="btn btn-success" value="сохранить"/>
                        </form>
                    </div>
                </div>
                <div id="change_password_view" style="display: none; margin-left: 100px">
                    <h3>Смена пароля</h3>
                    <div style="font-size:18px; margin-left: 40px;">
                        <div id="changePasswordError" style="display: none; color:red">Ошибка! Заполните форму еще раз и
                            будьте внимательны.
                        </div>
                        <form id="changePasswordForm">
                            <div class="form-group">
                                <label for="password">Текущий пароль:</label>
                                <input type="password" class="form-control" id="password">
                                <small class="form-text text-muted" id="passError">Введите текущий пароль.</small>
                            </div>
                            <div class="form-group">
                                <label for="newPass">Новый пароль:</label>
                                <input type="password" class="form-control" id="newPass">
                                <small class="form-text text-muted">Введите новый пароль.</small>
                            </div>
                            <div class="form-group">
                                <label for="newPassAgain">Повторите новый пароль:</label>
                                <input type="password" class="form-control" id="newPassAgain">
                                <small class="form-text text-muted" id="newPassError" style="display: none; color:red;">
                                    Пароли не совпадают.
                                </small>
                            </div>
                            <input type="hidden" class="uerId" value="${user.id}"/>
                            <input type="submit" class="btn btn-success" value="сохранить"/>
                        </form>
                    </div>

                </div>
                <div id="change_foto_view" style="display: none; margin-left: 100px;">
                    <h3>Фото</h3>
                    <div style="margin-left: 40px;">
                        <form enctype="multipart/form-data" id="changeFotoForm" method="post">
                            <div class="form-group">
                                <label for="file">Выберите фото:</label>
                                <input type="file" name="file" class="form-control-file" multiple id="file"
                                       accept="image/jpeg, image/png">
                                <small class="form-text text-muted">Размер фото не более 1мб, формат: .jpg, .jpeg,
                                    .png
                                </small>
                            </div>
                            <button type="submit" class="btn btn-success" value="${user.id}">Сохранить</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</t:pagesConteiner>
<script src="/resources/js/profile.js"></script>