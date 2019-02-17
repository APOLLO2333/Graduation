package com.supersong.graduation;

import com.supersong.graduation.bean.Menu;
import com.supersong.graduation.dao.MenuDao;
import com.supersong.graduation.dao.UserDao;
import com.supersong.graduation.service.MenuService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class GraduationApplicationTests {

    @Autowired
    UserDao userDao;
    @Autowired
    MenuService menuService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
    }
    @Test
    public void checkName(){
        System.out.println(menuService.checkName("123","67f9469a-9999-4bc4-9a2f-dbf80ab07c63"));
    }

//    @Test
//    public void checkredis(){
//        redisTemplate.opsForValue().set("1","song");
//        System.out.println(redisTemplate.opsForValue().get("1"));
//    }

}
