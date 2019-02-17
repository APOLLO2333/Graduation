package com.supersong.graduation.service.impl;

import com.supersong.graduation.bean.RepairOrder;
import com.supersong.graduation.dao.RepairOrderDao;
import com.supersong.graduation.service.RepairOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class RepairOrderServiceImpl implements RepairOrderService {

    @Autowired
    private RepairOrderDao repairOrderDao;

    @Override
    public int add(RepairOrder repairOrder) {
        repairOrder.setId(UUID.randomUUID().toString());
        repairOrder.setCreateTime(new Date());
        repairOrder.setUpdateTime(new Date());
        repairOrder.setStatus(0);
        try {
            repairOrderDao.add(repairOrder);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
        return 1;
    }

    @Override
    public int delete(String id) {
        try {
            repairOrderDao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
        return 1;
    }

    @Override
    public int update(RepairOrder repairOrder) {
        repairOrder.setUpdateTime(new Date());
        try {
            repairOrderDao.update(repairOrder);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
        return 1;
    }

    @Override
    public List<RepairOrder> getRepairOrderByStatus(int status) {
        return repairOrderDao.getRepairOrderByStatus(status);
    }
}
