package com.supersong.graduation.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supersong.graduation.bean.WebLog;
import com.supersong.graduation.service.WebLogService;
import com.supersong.graduation.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/webLog")
public class WebLogController {

    @Autowired
    private WebLogService webLogService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public Msg getAll(int pageSize, int pageNum, @RequestParam(required = false) Long startTime, @RequestParam(required = false) Long endTime) {
        PageHelper.startPage(pageNum, pageSize);
        List<WebLog> list = webLogService.getAll(startTime, endTime);
        PageInfo<WebLog> pageInfo = new PageInfo<>(list);
        return Msg.success().addData("pageInfo", pageInfo);
    }
}
