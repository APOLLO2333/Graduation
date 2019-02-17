package com.supersong.graduation.service.impl;

import com.supersong.graduation.bean.NewTurn;
import com.supersong.graduation.dao.NewsTurnDao;
import com.supersong.graduation.service.NewsTurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class NewsTurnServiceImpl implements NewsTurnService {

    @Autowired
    private NewsTurnDao newTurnDao;

    @Override
    public int add(NewTurn newTurn) {
        newTurn.setId(UUID.randomUUID().toString());
        newTurn.setStatus(0);
        try {
            if (newTurnDao.checkName(null,newTurn.getNewsTitle())!=0){
                return 0;
            }
            newTurnDao.add(newTurn);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
        return 1;
    }

    @Override
    public int addList(List<NewTurn> list) {
        try {
            newTurnDao.deleteAll();
            if(null == list || list.size() == 0){
                return 1;
            }
            int index = 0;
            for (NewTurn newTurn :list){
                newTurn.setId(UUID.randomUUID().toString());
                newTurn.setRank(index++);
            }
            newTurnDao.addList(list);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
        return 1;
    }

    @Override
    public int update(NewTurn newTurn) {
        try {
            if (newTurnDao.checkName(newTurn.getId(),newTurn.getNewsTitle())!=0){
                return 0;
            }
            newTurnDao.update(newTurn);
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
            newTurnDao.delete(id);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
        return 1;
    }

    @Override
    public List<NewTurn> getAll() {
        return newTurnDao.getAll();
    }

    @Override
    public int checkName(String id, String newsTitle) {
        return newTurnDao.checkName(id,newsTitle);
    }
}
