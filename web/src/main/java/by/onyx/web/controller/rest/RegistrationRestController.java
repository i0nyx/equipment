package by.onyx.web.controller.rest;

import by.onyx.common.data.UserData;
import by.onyx.common.pojo.profile.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/rest/registration/")
@AllArgsConstructor
public class RegistrationRestController {

    private UserData userData;

    @PostMapping(value = "save")
    public void save(@RequestBody User user) {
        if (user != null) {
            user.setDateRegistration(new Date());
            userData.save(user);
        }
    }

    @PostMapping(value = "changePhoneNumber")
    public boolean changePhoneNumber(@RequestBody String number) {
        boolean valid = false;
        User user = userData.getUserByPhoneNumber(number);
        if (user == null) {
            valid = true;
        }
        return valid;
    }
}
