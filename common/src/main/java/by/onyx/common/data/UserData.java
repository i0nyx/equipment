package by.onyx.common.data;


import by.onyx.common.pojo.profile.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserData {

    User save(User user);

    User changeUserProfile(User user);

    boolean changeUserAvatar(User user, MultipartFile multipartFile);

    List<User> getAllUser();

    User getById(int id);

    User getUserByPhoneNumber(String number);

}
