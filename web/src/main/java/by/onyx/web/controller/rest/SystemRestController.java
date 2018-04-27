package by.onyx.web.controller.rest;

import by.onyx.common.pojo.Support;
import by.onyx.common.data.SupportData;
import by.onyx.common.data.UserData;
import by.onyx.common.pojo.profile.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/")
public class SystemRestController{

    @Autowired
    private SupportData supportData;
    @Autowired
    private UserData userData;


    @RequestMapping(value = "send", method = RequestMethod.POST)
    public boolean sendSupportForm(@RequestBody String data){
        Support result;
        User user = null;
        boolean valid = true;

        JSONObject json = new JSONObject(data);
        String lastName = json.getString("lastName");
        String cabinet = json.getString("cabinet").toString();
        String comment = json.getString("comment");
        boolean urgently = json.getBoolean("urgently");
        String type = json.getString("supportType");
        Integer userId;
        if(!json.isNull("user")){
            userId = json.getInt("user");
            user = userData.getById(userId);
        }

        Support support = new Support();
        support.setLastName(lastName);
        support.setCabinet(cabinet);
        support.setComment(comment);
        support.setUrgently(urgently);
        support.setSupportType(Support.SupportType.valueOf(type.toUpperCase()));
        support.setSupportStatus(Support.SupportStatus.PENDING);
        support.setDate(new Date());
        support.setUser(user);

        result = supportData.save(support);

        if(result == null){
            valid = false;
        }
        return valid;
    }
    @RequestMapping(value = "notifications", method = RequestMethod.POST)
    public int getCountNotifications(){
        int count;
        List<Support> list = supportData.getAllByStatus(Support.SupportStatus.PENDING);
        count = list.size();
        return count;
    }
}
