<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" media="screen" href="/resources/css/registration.css">

<t:loginContainer>
    <div class="main-w3layouts wrapper">
        <div class="main-agileinfo">
            <span id="regHead">Регистрация</span>
            <div class="agileits-top">
                <form id="registration_form">
                    <label for="firstName">Имя*:</label>
                    <input class="text" type="text" id="firstName" required>
                    <label for="lastName">Фамилия*:</label>
                    <input class="text" type="text" id="lastName" required>
                    <label for="position">Должность*:</label>
                    <input class="text" type="text" id="position" required>
                    <label for="phoneNumber">Номер вашего мобильного телефона*:</label>
                    <input class="text tel" type="tel" id="phoneNumber" required>
                    <label for="cabinetNumber">Номер кабинета и корпус:</label>
                    <input class="text" type="text" id="cabinetNumber">
                    <label for="password">Придумайте пароль*:</label>
                    <input class="text" type="password" id="password" required>
                    <label for="confirmPassword">Повторите ваш пароль*:</label>
                    <input class="text" type="password" id="confirmPassword" required>
                    <input type="submit" id="register" value="Зарегистрироваться">
                </form>
                <p>Уже есть аккаунт? <a href="/login"> Войти</a></p>
            </div>
        </div>
    </div>

</t:loginContainer>
<script src="/resources/js/register.js"></script>
