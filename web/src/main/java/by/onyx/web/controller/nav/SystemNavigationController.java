package by.onyx.web.controller.nav;

import by.onyx.common.data.UserData;
import by.onyx.common.pojo.profile.User;
import by.onyx.common.util.UserUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/")
@AllArgsConstructor
public class SystemNavigationController {

    private UserData userData;

    @GetMapping(value = "/login")
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
        if (user == null) {
            return "login/login";
        } else {
            return "system/403";
        }
    }

    @GetMapping(value = "/registration")
    public String registerPage() {
        User user = UserUtils.getActiveUserFromRepository(userData);
        if (user == null) {
            return "registration/registration";
        } else {
            return "system/403";
        }
    }

    @GetMapping(value = "403")
    public String loginPage(ModelMap modelMap,
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
