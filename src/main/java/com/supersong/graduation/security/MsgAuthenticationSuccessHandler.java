package com.supersong.graduation.security;

import com.alibaba.fastjson.JSON;
import com.supersong.graduation.utils.Msg;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MsgAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        String token = (String) authentication.getPrincipal();

        Msg msg = Msg.success().addMessage("成功");
        msg.addData("token",token);
        httpServletResponse.getWriter().write(JSON.toJSONString(msg));
    }
}

