package by.onyx.web.controller.nav;

import by.onyx.common.data.*;
import by.onyx.common.pojo.Support;
import by.onyx.common.pojo.profile.User;
import by.onyx.common.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/")
public class NavigationController {

    @Autowired
    private CartridgeRepairData cartridgeRepairData;
    @Autowired
    private SupportData supportData;
    @Autowired
    private ComputerRepairData computerRepairData;
    @Autowired
    private PrinterRepairData printerRepairData;
    @Autowired
    private OrderData orderData;
    @Autowired
    private UserData userData;

    @RequestMapping(method = RequestMethod.GET)
    public String home(ModelMap modelMap){
        User user = UserUtils.getActiveUserFromRepository(userData);
        if(user != null){
            user.setPassword(null);
            modelMap.put("user", user);
        }
        return "index";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profilePage(ModelMap modelMap){
        User user = UserUtils.getActiveUserFromRepository(userData);
        if(user != null){
            user.setPassword(null);
            modelMap.put("user", user);
            modelMap.put("userSupports", supportData.getAllByUser(user));
            return "profile/profile";
        }else {
            return "login/login";
        }
    }

    @RequestMapping(value = "/cartridges_and_printers", method = RequestMethod.GET)
    public String printerPage(ModelMap modelMap){
        User user = UserUtils.getActiveUserFromRepository(userData);
        if(user != null){
            user.setPassword(null);
            modelMap.put("user", user);
        }
        modelMap.put("cartridges", cartridgeRepairData.get());
        modelMap.put("printers", printerRepairData.getAll());
        modelMap.put("supports", supportData.getAllBySupportType(Support.SupportType.PRINTER));
        return "cartridges_and_printers";
    }

    @RequestMapping(value = "/computers", method = RequestMethod.GET)
    public String computersPage(ModelMap modelMap){
        User user = UserUtils.getActiveUserFromRepository(userData);
        if(user != null){
            user.setPassword(null);
            modelMap.put("user", user);
        }
        modelMap.put("computers", computerRepairData.getAll());
        modelMap.put("supports", supportData.getAllBySupportType(Support.SupportType.COMPUTER));
        return "computers";
    }

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String orderPage(ModelMap modelMap){
        modelMap.put("orders", orderData.allByIsStateFalse());
        modelMap.put("allOrders", orderData.getAllOrders());
        return "order";
    }

}
