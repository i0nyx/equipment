package by.onyx.common.data.impl;

import by.onyx.common.data.UserData;
import by.onyx.common.pojo.profile.User;
import by.onyx.common.repositories.UserRepository;
import by.onyx.common.service.file.FileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

import static by.onyx.common.constants.Constants.AVATAR_PATH;
import static by.onyx.common.constants.Constants.DEFAULT_AVATAR;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class UserDataImpl implements UserData {

    private UserRepository repositories;
    private PasswordEncoder passwordEncoder;
    private FileService fileService;

    @Override
    public User save(User user) {
        User result = null;
        if (user != null) {
            if (user.getAvatar() == null) {
                user.setAvatar(DEFAULT_AVATAR);
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            try {
                result = repositories.saveAndFlush(user);
                log.info("save new user success " + result);
            } catch (Exception e) {
                log.error("cant save new user " + e);
            }
        }
        return result;
    }

    @Override
    public User changeUserProfile(User user) {
        User result = null;
        if (user != null) {
            try {
                result = repositories.save(user);
                repositories.flush();
                log.info("change user profile success");
            } catch (Exception e) {
                log.error("can't change user profile " + e);
            }
        }
        return result;
    }

    @Override
    public boolean changeUserAvatar(User user, MultipartFile multipartFile) {
        boolean valid = true;
        if (user != null && multipartFile != null) {
            String avatarName = multipartFile.getOriginalFilename();
            String path = fileService.createFolder(AVATAR_PATH);
            File file = new File(path, avatarName);
            try {
                multipartFile.transferTo(file);
            } catch (Exception e) {
                valid = false;
                log.warn("no upload image " + e);
            }

            user.setAvatar(avatarName);
            try {
                repositories.saveAndFlush(user);
            } catch (Exception e) {
                valid = false;
                log.warn("can't save user avatar " + e);
            }
        } else {
            valid = false;
        }
        return valid;
    }

    @Override
    public List<User> getAllUser() {
        return repositories.findAll();
    }

    @Override
    public User getById(int id) {
        return repositories.findOne(id);
    }

    @Override
    public User getUserByPhoneNumber(String number) {
        User result = null;
        try {
            result = repositories.findByPhoneNumber(number);
        } catch (Exception e) {
            log.error("cant get user by phoneNumber" + e);
        }
        return result;
    }


}
