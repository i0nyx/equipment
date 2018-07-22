package by.onyx.web.controller.nav;

import by.onyx.common.data.*;
import by.onyx.common.pojo.SupportType;
import by.onyx.common.pojo.profile.User;
import by.onyx.common.util.UserUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
@AllArgsConstructor
public class NavigationController {

    private CartridgeRepairData cartridgeRepairData;
    private SupportData supportData;
    private ComputerRepairData computerRepairData;
    private PrinterRepairData printerRepairData;
    private OrderData orderData;
    private UserData userData;

    @GetMapping
    public String home(ModelMap modelMap) {
        User user = UserUtils.getActiveUserFromRepository(userData);
        if (user != null) {
            user.setPassword(null);
            modelMap.put("user", user);
        }
        return "index";
    }

    @GetMapping(value = "/profile")
    public String profilePage(ModelMap modelMap) {
        User user = UserUtils.getActiveUserFromRepository(userData);
        if (user != null) {
            user.setPassword(null);
            modelMap.put("user", user);
            modelMap.put("userSupports", supportData.getAllByUser(user));
            return "profile/profile";
        } else {
            return "login/login";
        }
    }

    @GetMapping(value = "/cartridges_and_printers")
    public String printerPage(ModelMap modelMap) {
        User user = UserUtils.getActiveUserFromRepository(userData);
        if (user != null) {
            user.setPassword(null);
            modelMap.put("user", user);
        }
        modelMap.put("cartridges", cartridgeRepairData.get());
        modelMap.put("printers", printerRepairData.getAll());
        modelMap.put("supports", supportData.getAllBySupportType(SupportType.PRINTER));
        return "cartridges_and_printers";
    }

    @GetMapping(value = "/computers")
    public String computersPage(ModelMap modelMap) {
        User user = UserUtils.getActiveUserFromRepository(userData);
        if (user != null) {
            user.setPassword(null);
            modelMap.put("user", user);
        }
        modelMap.put("computers", computerRepairData.getAll());
        modelMap.put("supports", supportData.getAllBySupportType(SupportType.COMPUTER));
        return "computers";
    }

    @GetMapping(value = "/order")
    public String orderPage(ModelMap modelMap) {
        modelMap.put("orders", orderData.allByIsStateFalse());
        modelMap.put("allOrders", orderData.getAllOrders());
        return "order";
    }

}
