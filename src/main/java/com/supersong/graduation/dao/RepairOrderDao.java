package com.supersong.graduation.dao;

import com.supersong.graduation.bean.RepairOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RepairOrderDao {
    void add(RepairOrder repairOrder);

    void delete(String id);

    void update(RepairOrder repairOrder);

    List<RepairOrder> getRepairOrderByStatus(int status);
}
