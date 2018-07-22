<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:adminConteiner>
    <h7>::Оборудование::</h7>
    <nav>
        <div class="nav nav-tabs" id="nav-tab" role="tablist">
            <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab"
               aria-controls="nav-home" aria-selected="true" style="color:black">Оборудование</a>
            <a class="nav-item nav-link" id="nav-form-tab" data-toggle="tab" href="#nav-form" role="tab"
               aria-controls="nav-form" aria-selected="false" style="color:black">Добавить</a>
        </div>
    </nav>
    <div class="tab-content" id="nav-tabContent">
        <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
            <h3>Оборудование</h3>
            <table class="table table-striped">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Название</th>
                    <th scope="col">Марка</th>
                    <th scope="col">Модель</th>
                    <th scope="col">Кабинет</th>
                    <th scope="col">Номер</th>
                    <th scope="col">Действие</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="equipment" items="${equipments}">
                    <tr>
                        <td>${equipment.id}</td>
                        <td>${equipment.name}</td>
                        <td>${equipment.brand}</td>
                        <td>${equipment.model}</td>
                        <td>${equipment.cabinet}</td>
                        <td>${equipment.code}</td>
                        <td>
                            <button type="button" class="equipment_delete btn-danger" value="${equipment.id}">Удалить
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="tab-pane fade" id="nav-form" role="tabpanel" aria-labelledby="nav-form-tab">
            <h3>Добавить</h3>
            <form class="form-horizontal" id="formEquipment">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="typeEquipment">Тип:</label>
                    <div class="col-sm-10">
                        <select class="form-control" id="typeEquipment">
                            <option>выбрать</option>
                            <c:forEach var="type" items="${equipmentType}">
                                <option value="${type.key}" equipmentName="${type.value}">${type.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="brand">Марка :</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="brand" placeholder="example (HP)" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="model">Модель :</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="model" placeholder="example (P1005)">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="cabinet">Кабинет :</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="cabinet" placeholder="номер кабинета" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="code">Номер :</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="code" value="УОНГАК"
                               placeholder="Enter number (уонгак000001)" required>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" id="saveEquipment" class="btn btn-default">сохранить</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</t:adminConteiner>
<script src="/resources/admin/js/adminEquipment.js"></script>