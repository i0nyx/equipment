package by.onyx.common.service;

import by.onyx.common.data.UserData;
import by.onyx.common.pojo.profile.Role;
import by.onyx.common.pojo.profile.User;
import by.onyx.common.util.StringUtilExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class CustomUserDetailsService implements UserDetailsService{

    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserData userData;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        if(StringUtilExt.isNoBlank(username)){
            User user = userData.getUserByPhoneNumber(username);
            if(user != null){
                String encodedPassword = user.getPassword();
                userDetails = new org.springframework.security.core.userdetails.User(user.getPhoneNumber(), encodedPassword, getAuthorities(user));
            }

            /*User user = userData.getUserByPhoneNumber(username);
            Set<GrantedAuthority> roles = new HashSet<>();
            roles.add(new SimpleGrantedAuthority(Role.USER.name()));
            userDetails = new org.springframework.security.core.userdetails.User(user.getPhoneNumber(),
                                                                                            user.getPassword(),
                                                                                            roles);*/
        }
        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found with this username: " + username);
        }
        return userDetails;
    }

    public static List<GrantedAuthority> getAuthorities(User user) {
        log.debug("authorities guest:"+user);
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        authList.add(new SimpleGrantedAuthority("ROLE_" + Role.USER.name()));
        return authList;
    }
}
