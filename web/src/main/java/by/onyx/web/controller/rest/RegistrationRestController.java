package by.onyx.web.controller.rest;

import by.onyx.common.data.UserData;
import by.onyx.common.pojo.profile.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/rest/registration/")
public class RegistrationRestController {

    @Autowired
    private UserData userData;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public void save(@RequestBody User user){
        if(user != null){
            user.setDateRegistration(new Date());
            userData.save(user);
        }
    }

    @RequestMapping(value = "changePhoneNumber", method = RequestMethod.POST)
    public boolean changePhoneNumber(@RequestBody String number){
        boolean valid = false;
        User user = userData.getUserByPhoneNumber(number);
        if(user == null){
            valid = true;
        }
        return valid;
    }
}
