package com.supersong.graduation.security;

import com.alibaba.fastjson.JSON;
import com.supersong.graduation.utils.Msg;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MsgAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        Msg msg = Msg.fail().addMessage("Need Authorities!");
        msg.setCode(403);
        httpServletResponse.setStatus(403);
        httpServletResponse.getWriter().write(JSON.toJSONString(msg));
    }
}
