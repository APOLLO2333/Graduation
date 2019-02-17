package com.supersong.graduation.service.impl;

import com.supersong.graduation.bean.DiscussFilter;
import com.supersong.graduation.bean.NewsDiscuss;
import com.supersong.graduation.dao.NewsDiscussDao;
import com.supersong.graduation.service.NewsDiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class NewsDiscussServiceImpl implements NewsDiscussService {

    @Autowired
    private NewsDiscussDao newsDiscussDao;

    @Override
    public List<NewsDiscuss> getDiscussByNewsId(String id) {
        return newsDiscussDao.getDiscussByNewsId(id);
    }

    @Override
    public int deleteNewsDiscuss(String id) {
        try {
            newsDiscussDao.deleteNewsDiscuss(id);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
        return 1;
    }

    @Override
    public int addNewsDiscuss(NewsDiscuss newsDiscuss) {
        try {
            newsDiscuss.setId(UUID.randomUUID().toString());
            newsDiscuss.setCreateTime(new Date());
            newsDiscussDao.addNewsDiscuss(newsDiscuss);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
        return 1;
    }

    @Override
    public int addDiscussFilter(DiscussFilter discussFilter) {
        try {
            discussFilter.setCreateTime(new Date());
            discussFilter.setId(UUID.randomUUID().toString());
            newsDiscussDao.addDiscussFilter(discussFilter);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
        return 1;
    }

    @Override
    public DiscussFilter getDiscussFilter() {
        return newsDiscussDao.getDiscussFilter().get(0);
    }
}
