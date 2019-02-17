package com.supersong.graduation.service.impl;

import com.supersong.graduation.bean.SystemSetting;
import com.supersong.graduation.dao.SystemSettingDao;
import com.supersong.graduation.service.SystemSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;


@Service
@Transactional(rollbackFor = Exception.class)
public class SystemSettingServiceImpl implements SystemSettingService {

    @Autowired
    private SystemSettingDao systemSettingDao;

    @Override
    public int add(SystemSetting systemSetting) {
        systemSetting.setCreateTime(new Date());
        try {
            systemSettingDao.add(systemSetting);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
        return 1;
    }

    @Override
    public SystemSetting get() {
        return systemSettingDao.get().get(0);
    }
}
