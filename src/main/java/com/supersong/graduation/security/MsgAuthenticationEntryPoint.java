package com.supersong.graduation.security;

import com.alibaba.fastjson.JSON;
import com.supersong.graduation.utils.Msg;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MsgAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        Msg msg = Msg.fail().addMessage("Need Authorities!");
        httpServletResponse.setStatus(401);
        httpServletResponse.getWriter().write(JSON.toJSONString(msg));
    }
}
