package by.onyx.web.controller.admin.rest;

import by.onyx.common.data.UserData;
import by.onyx.common.pojo.profile.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static by.onyx.web.constants.ConstantUrlAdminMapping.CONST_URL_ADMIN_REST_PROFILE;

@RestController
@RequestMapping(value = CONST_URL_ADMIN_REST_PROFILE)
@AllArgsConstructor
public class AdminRestProfile {

    private UserData userData;

    @PostMapping(value = "/get-user-by-id")
    public User getUser(@RequestBody int id) {
        User result = null;
        if (id != 0) {
            result = userData.getById(id);
        }
        return result;
    }

    @PostMapping(value = "/save-user-data")
    public boolean saveUserData(@RequestBody User user) {
        User result = null;
        boolean valid = true;
        if (user != null) {
            result = userData.getById(user.getId());
        } else {
            valid = false;
        }
        if (result != null) {
            result.setFirstName(user.getFirstName());
            result.setLastName(user.getLastName());
            result.setPosition(user.getPosition());
            result.setCabinetNumber(user.getCabinetNumber());
            result.setPhoneNumber(user.getPhoneNumber());

            result = userData.save(result);
            if (result == null) {
                valid = false;
            }
        }
        return valid;
    }


}
