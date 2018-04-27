package by.onyx.web.controller.rest;

import by.onyx.common.data.UserData;
import by.onyx.common.pojo.profile.User;
import by.onyx.common.util.UserUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping(value = "/rest/profile/")
public class ProfileRestController {

    @Autowired
    private UserData userData;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "change", method = RequestMethod.POST)
    public boolean changeProfile(@RequestBody User user){
        boolean valid = true;
        User result = null;
        if(user != null){
            result = userData.getById(user.getId());
            if(result != null){
                if(!user.getFirstName().isEmpty()){
                    result.setFirstName(user.getFirstName());
                }
                if(!user.getLastName().isEmpty()){
                    result.setLastName(user.getLastName());
                }
                if(!user.getPosition().isEmpty()){
                    result.setPosition(user.getPosition());
                }
                if(!user.getCabinetNumber().isEmpty()){
                    result.setCabinetNumber(user.getCabinetNumber());
                }
                if(!user.getPhoneNumber().isEmpty()){
                    result.setPhoneNumber(user.getPhoneNumber());
                }
            }else{
                valid = false;
            }
        }
        if(valid){
            result = userData.changeUserProfile(result);
            if(result == null){
                valid = false;
            }
        }
        return valid;
    }

    @RequestMapping(value = "change-password", method = RequestMethod.POST)
    public boolean changeUserPassword(@RequestBody String data){
        boolean valid = true;
        String oldPass = null;
        String newPass = null;
        String newPassAgain;
        int id = 0;
        if(data != null){
            JSONObject json = new JSONObject(data);
            oldPass = json.getString("oldPass");
            newPass = json.getString("newPass");
            newPassAgain = json.getString("newPassAgain");
            id = json.getInt("userId");
            if(!newPass.equals(newPassAgain)){
                valid = false;
            }
        }else{
            valid = false;
        }
        User user = userData.getById(id);
        if(user != null && valid){
            valid = passwordEncoder.matches(oldPass, user.getPassword());
            if(valid){
                user.setPassword(newPass);
                userData.save(user);
            }
        }else{
            valid = false;
        }
        return valid;
    }

    @RequestMapping(value = "change-avatar", method = RequestMethod.POST)
    public @ResponseBody boolean changeAvatar(MultipartHttpServletRequest request){
        boolean valid = true;
        User user = UserUtils.getActiveUserFromRepository(userData);
        if(user == null){
            valid = false;
        }
        MultipartFile multipartFile = request.getFile("file");
        if(multipartFile != null && valid){
            valid = userData.changeUserAvatar(user, multipartFile);
        }else{
            valid = false;
        }
        return valid;
    }

}
