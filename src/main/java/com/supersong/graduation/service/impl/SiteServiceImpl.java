package com.supersong.graduation.service.impl;

import com.supersong.graduation.bean.Site;
import com.supersong.graduation.dao.SiteDao;
import com.supersong.graduation.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class SiteServiceImpl implements SiteService {

    @Autowired
    private SiteDao siteDao;

    @Override
    public int add(Site site) {
        try {
            site.setId(UUID.randomUUID().toString());
            if (site.getParentId() == null || "".equals(site.getParentId())) {
                site.setParentId(site.getId());
            }
            site.setCreateTime(new Date());
            siteDao.add(site);
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
            siteDao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }

        return 1;
    }

    @Override
    public int update(Site site) {
        if (site.getId() == null || "".equals(site.getId())) {
            return 0;
        }
        try {
            siteDao.update(site);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return 0;
        }
        return 1;
    }

    @Override
    public List<Site> getAll() {
        List<Site> list = siteDao.getAll();
        List<Site> result = new ArrayList<>();
        helper(list, result, null);
        return result;
    }

    private void helper(List<Site> list, List<Site> result, String parentId) {
        for (int i = 0; i < list.size(); i++) {
            if (parentId == null) {
                parentId = list.get(i).getId();
            }
            if (list.get(i).getParentId().equals(parentId)) {
                result.add(list.get(i));
                result.remove(i);
                i--;
                helper(list, list.get(i).getChild(), list.get(i).getId());
            }
        }
    }
}
