package com.ig.Utils;

import com.ig.Utils.UrlPatternUtils;
import com.ig.model.SecurityConfigUSER_ROLE;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public class CheckROLE_URL {
    public static boolean isSecurityPage(HttpServletRequest request) {
        String urlPattern = UrlPatternUtils.getUrlPattern(request);
        Set<String> roles = SecurityConfigUSER_ROLE.getAllAppRoles();
        for (String role : roles) {
            List<String> urlPatterns = SecurityConfigUSER_ROLE.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
    public static boolean hasPermission(HttpServletRequest request) {
        String urlPattern = UrlPatternUtils.getUrlPattern(request);
        Set<String> allRoles = SecurityConfigUSER_ROLE.getAllAppRoles();
        for (String role : allRoles) {
            if (!request.isUserInRole(role)) {
                continue;
            }
            List<String> urlPatterns = SecurityConfigUSER_ROLE.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
}
