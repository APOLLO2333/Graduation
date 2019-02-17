package com.supersong.graduation.controller;

import com.supersong.graduation.bean.SystemSetting;
import com.supersong.graduation.service.SystemSettingService;
import com.supersong.graduation.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/systemSetting")
public class SysSettingController {

    @Autowired
    private SystemSettingService systemSettingService;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Msg add(@RequestBody SystemSetting systemSetting) {
        if (null == systemSetting.getLogin()) {
            systemSetting.setLogin(1);
        }
        if (null == systemSetting.getRegister()) {
            systemSetting.setRegister(1);
        }
        if (null == systemSetting.getOpen()) {
            systemSetting.setOpen(1);
        }

        if (systemSettingService.add(systemSetting) == 1) {
            return Msg.success();
        }
        return Msg.fail();
    }


    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public Msg get() {
        return Msg.success().addData("SystemSetting", systemSettingService.get());
    }
}
