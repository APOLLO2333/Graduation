package com.supersong.graduation.controller;

import com.supersong.graduation.bean.Site;
import com.supersong.graduation.service.SiteService;
import com.supersong.graduation.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/site")
public class SiteController {

    @Autowired
    private SiteService siteService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Msg add(@RequestBody Site site) {
        if (null == site.getName() || "".equals(site.getName())) {
            return Msg.fail();
        }
        if (null == site.getRemark() || "".equals(site.getRemark())) {
            return Msg.fail();
        }
        if (siteService.add(site) == 1) {
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public Msg update(@RequestBody Site site) {
        if (null == site.getId() || "".equals(site.getId())) {
            return Msg.fail();
        }
        if (siteService.update(site) == 1) {
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg delete(String id) {
        if (siteService.delete(id) == 1) {
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public Msg getAll() {
        List<Site> list = siteService.getAll();
        return Msg.success().addData("list", list);
    }
}
