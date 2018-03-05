package com.odlare.api.multitenant;

import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TenantInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authorization = request.getHeader("Authorization");

        String[] auths = authorization.split(" ");

        if (auths[0].equalsIgnoreCase("bearer")) {
            Jwt jwt = JwtHelper.decode(auths[1]);
            JSONObject jsonObject = new JSONObject(jwt.getClaims());
            TenantContext.setCurrentTenant((String) jsonObject.get("tenant"));
        } else {

            String refreshToken = null;

            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("refreshToken")) {
                    refreshToken = cookie.getValue();
                }
            }

            if (auths[0].equalsIgnoreCase("basic") && refreshToken != null) {
                Jwt jwt = JwtHelper.decode(refreshToken);
                JSONObject jsonObject = new JSONObject(jwt.getClaims());
                TenantContext.setCurrentTenant((String) jsonObject.get("tenant"));
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        TenantContext.clear();
    }
}
