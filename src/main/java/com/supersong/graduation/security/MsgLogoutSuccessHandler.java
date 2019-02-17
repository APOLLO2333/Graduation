package com.supersong.graduation.security;

import com.alibaba.fastjson.JSON;
import com.supersong.graduation.utils.Msg;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MsgLogoutSuccessHandler implements LogoutSuccessHandler {



    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        Msg msg = Msg.success().addMessage("Logout Success!");


        httpServletResponse.getWriter().write(JSON.toJSONString(msg));
    }
}
