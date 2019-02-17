package com.supersong.graduation.service;

import com.supersong.graduation.bean.RepairOrder;

import java.util.List;

public interface RepairOrderService {
    int add(RepairOrder repairOrder);

    int delete(String id);

    int update(RepairOrder repairOrder);

    List<RepairOrder> getRepairOrderByStatus(int status);
}
