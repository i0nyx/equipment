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
import java.util.Optional;

import static by.onyx.common.constants.Constants.AVATAR_PATH;
import static by.onyx.common.constants.Constants.DEFAULT_AVATAR;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class UserDataImpl implements UserData {
    private final UserRepository repositories;
    private final PasswordEncoder passwordEncoder;
    private final FileService fileService;

    @Override
    public User save(final User user) {
        Optional.ofNullable(user).orElseThrow(() -> new IllegalArgumentException("Error: user should not be null!"));
        Optional.ofNullable(user.getAvatar()).ifPresent(u -> user.setAvatar(DEFAULT_AVATAR));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repositories.saveAndFlush(user);
    }

    @Override
    public User changeUserProfile(final User user) {
        Optional.ofNullable(user).orElseThrow(() -> new IllegalArgumentException("Error: user should not be null!"));
        return repositories.saveAndFlush(user);
    }

    @Override
    public boolean changeUserAvatar(final User user, final MultipartFile multipartFile) {
        boolean valid = true;
        Optional.ofNullable(user).orElseThrow(() -> new IllegalArgumentException("Error: user should not be null!"));
        Optional.ofNullable(multipartFile).orElseThrow(() -> new IllegalArgumentException("Error: multipartFile should not be null!"));
        String avatarName = multipartFile.getOriginalFilename();
        File file = new File(fileService.createFolder(AVATAR_PATH), avatarName);
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
            log.warn("can't save avatar for user id - {}. {}", user.getId(), e);
        }
        return valid;
    }

    @Override
    public List<User> getAllUser() {
        return repositories.findAll();
    }

    @Override
    public User getById(final int id) {
        return repositories.findOne(id);
    }

    @Override
    public User getUserByPhoneNumber(final String number) {
        return repositories.findByPhoneNumber(number);
    }
}
