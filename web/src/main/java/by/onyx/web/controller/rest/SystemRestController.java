package by.onyx.web.controller.rest;

import by.onyx.common.data.SupportData;
import by.onyx.common.data.UserData;
import by.onyx.common.pojo.Support;
import by.onyx.common.pojo.SupportStatus;
import by.onyx.common.pojo.SupportType;
import by.onyx.common.pojo.profile.User;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/")
@AllArgsConstructor
public class SystemRestController {

    private SupportData supportData;
    private UserData userData;

    @PostMapping(value = "send")
    public boolean sendSupportForm(@RequestBody String data) {
        Support result;
        User user = null;
        boolean valid = true;

        JSONObject json = new JSONObject(data);
        String lastName = json.getString("lastName");
        String cabinet = json.getString("cabinet");
        String comment = json.getString("comment");
        boolean urgently = json.getBoolean("urgently");
        String type = json.getString("supportType");
        Integer userId;
        if (!json.isNull("user")) {
            userId = json.getInt("user");
            user = userData.getById(userId);
        }

        Support support = new Support();
        support.setLastName(lastName);
        support.setCabinet(cabinet);
        support.setComment(comment);
        support.setUrgently(urgently);
        support.setSupportType(SupportType.valueOf(type.toUpperCase()));
        support.setSupportStatus(SupportStatus.PENDING);
        support.setDate(new Date());
        support.setUser(user);

        result = supportData.save(support);

        if (result == null) {
            valid = false;
        }
        return valid;
    }

    @PostMapping(value = "notifications")
    public int getCountNotifications() {
        int count;
        List<Support> list = supportData.getAllByStatus(SupportStatus.PENDING);
        count = list.size();
        return count;
    }
}
