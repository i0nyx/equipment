package by.onyx.web.controller.nav;

import by.onyx.common.data.UserData;
import by.onyx.common.pojo.profile.User;
import by.onyx.common.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/")
public class SystemNavigationController {

    @Autowired
    private UserData userData;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            ModelMap modelMap) {

        if (error != null) {
            modelMap.addAttribute("error", "error");
        }
        if (logout != null) {
            modelMap.addAttribute("msg", "logout");
        }
        User user = UserUtils.getActiveUserFromRepository(userData);
        if(user == null) {
            return "login/login";
        }else{
            return "system/403";
        }
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registerPage(){
        User user = UserUtils.getActiveUserFromRepository(userData);
        if(user == null) {
            return "registration/registration";
        }else{
            return "system/403";
        }
    }

    @RequestMapping(value = "403", method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request, ModelMap modelMap,
                            @RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout) {

        if (error != null) {
            modelMap.addAttribute("error", "error");
        }
        if (logout != null) {
            modelMap.addAttribute("msg", "logout");
        }
        return "system/403";
    }
}
