package by.onyx.common.data;

import by.onyx.common.pojo.profile.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserData {
    User save(final User user);
    User changeUserProfile(final User user);
    boolean changeUserAvatar(final User user, final MultipartFile multipartFile);
    List<User> getAllUser();
    User getById(final int id);
    User getUserByPhoneNumber(final String number);
}
