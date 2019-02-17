package com.supersong.graduation.service.impl;


import com.supersong.graduation.bean.BlogRoll;
import com.supersong.graduation.dao.BlogRollDao;
import com.supersong.graduation.service.BlogRollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class BlogRollServiceImpl implements BlogRollService {

    @Autowired
    private BlogRollDao blogRollDao;

    @Override
    public int addList(List<BlogRoll> list) {
        try {
            blogRollDao.deleteAll();
            if (null == list ||list.size() == 0){
                return 1;
            }
            blogRollDao.addList(list);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return 0;
        }
        return 1;
    }


    @Override
    public List<BlogRoll> getAll() {
        return blogRollDao.getAll();
    }
}
