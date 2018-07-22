package by.onyx.common.service;

import by.onyx.common.data.UserData;
import by.onyx.common.pojo.profile.Role;
import by.onyx.common.pojo.profile.User;
import by.onyx.common.util.StringUtilExt;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserData userData;

    public static List<GrantedAuthority> getAuthorities(User user) {
        log.debug("authorities guest:" + user);
        List<GrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority("ROLE_" + Role.USER.name()));
        return authList;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        if (StringUtilExt.isNoBlank(username)) {
            User user = userData.getUserByPhoneNumber(username);
            if (user != null) {
                String encodedPassword = user.getPassword();
                userDetails = new org.springframework.security.core.userdetails.User(user.getPhoneNumber(), encodedPassword, getAuthorities(user));
            }
        }
        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found with this username: " + username);
        }
        return userDetails;
    }
}
