package by.onyx.web.controller.admin.nav;

import by.onyx.common.data.*;
import by.onyx.common.pojo.EquipmentType;
import by.onyx.common.pojo.ReceivedRepair;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import static by.onyx.common.pojo.Equipment.equipmentTypeHashMap;
import static by.onyx.common.pojo.SupportStatus.PENDING;
import static by.onyx.web.constants.ConstantUrlAdminMapping.CONST_URL_ADMIN;
import static by.onyx.web.constants.ConstantUrlAdminMapping.CONST_URL_ADMIN_VIEW;

@Controller
@RequestMapping(value = CONST_URL_ADMIN)
@AllArgsConstructor
public class AdminNavigationController {

    private CartridgeRepairData cartridgeRepairData;
    private ReceivedRepairData receivedRepairData;
    private EquipmentData equipmentData;
    private SupportData supportData;
    private OrderData orderData;
    private PrinterRepairData printerRepairData;
    private ComputerRepairData computerRepairData;
    private UserData userData;

    @GetMapping
    public String adminHome() {
        return "admin/index";
    }

    @GetMapping(value = "/order")
    public String orderView(ModelMap modelMap) {
        modelMap.put("orders", orderData.allByIsStateFalse());
        modelMap.put("allOrders", orderData.getAllOrders());
        return CONST_URL_ADMIN_VIEW + "adminOrderView";
    }

    @GetMapping(value = "/application")
    public String applicationView(ModelMap modelMap) {
        modelMap.put("allApplications", supportData.getAll());
        modelMap.put("applications", supportData.getAllByStatus(PENDING));
        return CONST_URL_ADMIN_VIEW + "adminApplication";
    }

    @GetMapping(value = "/equipment")
    public String equipmentView(ModelMap modelMap) {
        modelMap.put("equipmentType", equipmentTypeHashMap);
        modelMap.put("equipments", equipmentData.getAll());
        return CONST_URL_ADMIN_VIEW + "adminEquipment";
    }

    @GetMapping(value = "/repair-cartridge")
    public String repairCartridgeView(ModelMap modelMap) {
        modelMap.put("enumType", cartridgeRepairData.getTypeWork());
        modelMap.put("receivedRepairs", receivedRepairData.getByEquipmentTypeAndState(EquipmentType.CARTRIDGE, false));
        modelMap.put("cartridges", cartridgeRepairData.get());
        return CONST_URL_ADMIN_VIEW + "adminCartridgeRepairView";
    }

    @GetMapping(value = "/received-repair")
    public String receivedRepairView(ModelMap modelMap) {
        modelMap.put("lists", receivedRepairData.getByState(false));
        modelMap.put("applications", supportData.getAllByStatus(PENDING));
        return CONST_URL_ADMIN_VIEW + "adminReceivedRepairView";
    }

    @GetMapping(value = "/computer-repair")
    public String repairComputerView(ModelMap modelMap) {
        List<ReceivedRepair> list = new ArrayList<>();
        list.addAll(receivedRepairData.getByEquipmentTypeAndState(EquipmentType.COMPUTER, false));
        list.addAll(receivedRepairData.getByEquipmentTypeAndState(EquipmentType.NOTEBOOK, false));
        list.addAll(receivedRepairData.getByEquipmentTypeAndState(EquipmentType.ACCESSORIES, false));
        modelMap.put("receivedRepairs", list);
        modelMap.put("computers", computerRepairData.getAll());

        return CONST_URL_ADMIN_VIEW + "adminComputerRepairView";
    }

    @GetMapping(value = "/printer-repair")
    public String repairPrinterView(ModelMap modelMap) {
        List<ReceivedRepair> list = new ArrayList<>();
        list.addAll(receivedRepairData.getByEquipmentTypeAndState(EquipmentType.PRINTER, false));
        modelMap.put("receivedRepairs", list);
        modelMap.put("printers", printerRepairData.getAll());
        return CONST_URL_ADMIN_VIEW + "/adminPrinterRepairView";
    }

    @GetMapping(value = "/import-export")
    public String importExportView() {
        return CONST_URL_ADMIN_VIEW + "/adminImportExportView";
    }

    @GetMapping(value = "/users")
    public String usersView(ModelMap modelMap) {
        modelMap.put("users", userData.getAllUser());
        return CONST_URL_ADMIN_VIEW + "/adminUsersView";
    }

}
