package by.onyx.web.config;

import by.onyx.common.pojo.profile.Role;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_" + Role.SUPER_ADMIN.name())) {
            response.sendRedirect("/admin");
            return;
        }

        SavedRequest savedReq = new HttpSessionRequestCache().getRequest(request, response);
        if (savedReq != null) {
            response.sendRedirect(savedReq.getRedirectUrl());
            return;
        } else if (roles.contains("ROLE_" + Role.USER.name())) {
            response.sendRedirect("/profile");
            return;
        }

        response.sendRedirect("/");
        return;
    }
}
