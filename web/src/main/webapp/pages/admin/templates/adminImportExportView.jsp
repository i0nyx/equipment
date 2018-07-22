<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:adminConteiner>
    <h3>Импорт Экспорт данных</h3>

    <div class="container">
        <div class="row">
            <div class="col">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col"></th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>Оборудование</td>
                        <td>
                            <form id="importEquipment" method="post" enctype="multipart/form-data">
                                <input type="file" name="file" id="equipmentFile" multiple
                                       accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet">
                                <button class="import-equipment-button btn btn-primary btn-xs">Импорт</button>
                            </form>
                            <div style="display:none;" id="msgSuccess"></div>
                        </td>
                        <td>
                            <button class="export-equipment-button btn btn-primary btn-xs" value="download-equipment">
                                Экспорт
                            </button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</t:adminConteiner>
<script src="/resources/admin/js/adminImportExport.js"></script>