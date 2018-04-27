package by.onyx.common.util;

import by.onyx.common.data.UserData;
import by.onyx.common.pojo.profile.User;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {

    final static Logger log = Logger.getLogger(UserUtils.class);

    public static String getActiveUserName() {
        String userName = "";
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            if(context != null){
                Authentication authentication = context.getAuthentication();
                if(authentication != null){
                    Object principal = authentication.getPrincipal();
                    if (principal instanceof org.springframework.security.core.userdetails.User) {
                        org.springframework.security.core.userdetails.User activeUser = (org.springframework.security.core.userdetails.User) principal;
                        userName = activeUser.getUsername();
                    } else if(principal instanceof String){
                        userName = (String) principal;
                    }
                }
            }
        } catch (Exception e) {
            log.debug("Can't retrieve user", e);
        }
        return userName;
    }

    public static User getActiveUserFromRepository(UserData userData) {
        User user = null;
        String activeUser = getActiveUserName();
        if(activeUser != null){
            user = userData.getUserByPhoneNumber(activeUser);
        }
        return user;
    }
}
