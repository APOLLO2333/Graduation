package com.supersong.graduation.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supersong.graduation.bean.RepairOrder;
import com.supersong.graduation.service.RepairOrderService;
import com.supersong.graduation.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/repairOrder")
public class RepairOrderController {

    @Autowired
    private RepairOrderService repairOrderService;


    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Msg add(@RequestBody RepairOrder repairOrder){
        if (null == repairOrder.getName() || "".equals(repairOrder.getName())){
            return Msg.fail();
        }
        if (null == repairOrder.getRemark() || "".equals(repairOrder.getRemark())){
            return Msg.fail();
        }
        if (null == repairOrder.getSiteId() || "".equals(repairOrder.getSiteId())){
            return Msg.fail();
        }
        if (null == repairOrder.getUserId() || "".equals(repairOrder.getUserId())){
            return Msg.fail();
        }
        if (repairOrderService.add(repairOrder) == 1){
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg delete(String id){
        if (repairOrderService.delete(id) == 1){
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public Msg update(@RequestBody RepairOrder repairOrder){
        if (null == repairOrder.getId() || "".equals(repairOrder.getUserId())){
            return Msg.fail();
        }
        if (repairOrderService.update(repairOrder) == 1){
            return Msg.success();
        }
        return Msg.fail();
    }

    @RequestMapping(value = "/getRepairOrderByStatus",method = RequestMethod.GET)
    @ResponseBody
    public Msg getRepairOrderByStatus(int status,int pageNum,int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<RepairOrder> list = repairOrderService.getRepairOrderByStatus(status);
        PageInfo<RepairOrder> pageInfo = new PageInfo<>(list);
        return Msg.success().addData("pageInfo",pageInfo);
    }
}
