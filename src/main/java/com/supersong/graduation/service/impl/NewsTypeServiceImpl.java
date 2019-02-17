package com.supersong.graduation.service.impl;

import com.supersong.graduation.bean.NewsType;
import com.supersong.graduation.dao.NewsTypeDao;
import com.supersong.graduation.service.NewsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class NewsTypeServiceImpl implements NewsTypeService {

    @Autowired
    private NewsTypeDao newsTypeDao;

    @Override
    public int add(NewsType newsType) {
        newsType.setId(UUID.randomUUID().toString());
        newsType.setCreateTime(new Date());
        try {
            if (newsTypeDao.checkName(newsType.getTypeName(),null)!=0){
                return 0;
            }
            newsTypeDao.add(newsType);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
        return 1;
    }

    @Override
    public int delete(String id) {
        try {
            newsTypeDao.delete(id);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
        return 1;
    }

    @Override
    public int update(NewsType newsType) {
        try {
            if (newsTypeDao.checkName(newsType.getTypeName(),newsType.getId())!=0){
                return 0;
            }
            newsTypeDao.update(newsType);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
        return 1;
    }

    @Override
    public List<NewsType> getAll() {
        return newsTypeDao.getAll();
    }

    @Override
    public List<NewsType> query(String query) {
        return newsTypeDao.query(query);
    }

    @Override
    public int checkName(String typeName, String id) {
        return newsTypeDao.checkName(typeName,id);
    }
}
