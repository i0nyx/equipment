package by.onyx.web.controller.admin.nav;

import by.onyx.common.data.*;
import by.onyx.common.pojo.Equipment;
import by.onyx.common.pojo.ReceivedRepair;
import by.onyx.common.pojo.Support;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.ArrayList;
import java.util.List;

import static by.onyx.common.pojo.Equipment.equipmentTypeHashMap;
import static by.onyx.common.pojo.Support.SupportStatus.PENDING;
import static by.onyx.web.constants.ConstantUrlAdminMapping.CONST_URL_ADMIN;
import static by.onyx.web.constants.ConstantUrlAdminMapping.CONST_URL_ADMIN_VIEW;

@Controller
@RequestMapping(value = CONST_URL_ADMIN)
public class AdminNavigationController {

    @Autowired
    private CartridgeRepairData cartridgeRepairData;
    @Autowired
    private ReceivedRepairData receivedRepairData;
    @Autowired
    private EquipmentData equipmentData;
    @Autowired
    private SupportData supportData;
    @Autowired
    private OrderData orderData;
    @Autowired
    private PrinterRepairData printerRepairData;
    @Autowired
    private ComputerRepairData computerRepairData;
    @Autowired
    private UserData userData;

    @RequestMapping(method = RequestMethod.GET)
    public String adminHome(){
        return "admin/index";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String orderView(ModelMap modelMap){
        modelMap.put("orders", orderData.allByIsStateFalse());
        modelMap.put("allOrders", orderData.getAllOrders());
        return CONST_URL_ADMIN_VIEW + "adminOrderView";
    }

    @RequestMapping(value = "/application", method = RequestMethod.GET)
    public String applicationView(ModelMap modelMap){
        modelMap.put("allApplications", supportData.getAll());
        modelMap.put("applications", supportData.getAllByStatus(PENDING));
        return CONST_URL_ADMIN_VIEW + "adminApplication";
    }

    @RequestMapping(value = "/equipment", method = RequestMethod.GET)
    public String equipmentView(ModelMap modelMap){
        modelMap.put("equipmentType", equipmentTypeHashMap);
        modelMap.put("equipments", equipmentData.getAll());
        return CONST_URL_ADMIN_VIEW + "adminEquipment";
    }

    @RequestMapping(value = "/repair-cartridge", method = RequestMethod.GET)
    public String repairCartridgeView(ModelMap modelMap){
        modelMap.put("enumType", cartridgeRepairData.getTypeWork());
        modelMap.put("receivedRepairs", receivedRepairData.getByEquipmentTypeAndState(Equipment.EquipmentType.CARTRIDGE, false));
        modelMap.put("cartridges", cartridgeRepairData.get());
        return CONST_URL_ADMIN_VIEW +"adminCartridgeRepairView";
    }

    @RequestMapping(value = "/received-repair", method = RequestMethod.GET)
    public String receivedRepairView(ModelMap modelMap){
        modelMap.put("lists", receivedRepairData.getByState(false));
        modelMap.put("applications", supportData.getAllByStatus(PENDING));
        return CONST_URL_ADMIN_VIEW + "adminReceivedRepairView";
    }

    @RequestMapping(value = "/computer-repair", method = RequestMethod.GET)
    public String repairComputerView(ModelMap modelMap){
        List<ReceivedRepair> list = new ArrayList<ReceivedRepair>();
        list.addAll(receivedRepairData.getByEquipmentTypeAndState(Equipment.EquipmentType.COMPUTER, false));
        list.addAll(receivedRepairData.getByEquipmentTypeAndState(Equipment.EquipmentType.NOTEBOOK, false));
        list.addAll(receivedRepairData.getByEquipmentTypeAndState(Equipment.EquipmentType.ACCESSORIES, false));
        modelMap.put("receivedRepairs", list);
        modelMap.put("computers", computerRepairData.getAll());

        return CONST_URL_ADMIN_VIEW + "adminComputerRepairView";
    }

    @RequestMapping(value = "/printer-repair", method = RequestMethod.GET)
    public String repairPrinterView(ModelMap modelMap){
        List<ReceivedRepair> list = new ArrayList<ReceivedRepair>();
        list.addAll(receivedRepairData.getByEquipmentTypeAndState(Equipment.EquipmentType.PRINTER, false));
        modelMap.put("receivedRepairs", list);
        modelMap.put("printers", printerRepairData.getAll());
        return CONST_URL_ADMIN_VIEW + "/adminPrinterRepairView";
    }

    @RequestMapping(value = "/import-export", method = RequestMethod.GET)
    public String importExportView(){
        return CONST_URL_ADMIN_VIEW + "/adminImportExportView";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String usersView(ModelMap modelMap){
        modelMap.put("users", userData.getAllUser());
        return CONST_URL_ADMIN_VIEW + "/adminUsersView";
    }

}
