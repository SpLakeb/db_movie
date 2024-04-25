package com.cinema.service.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @author hzk
 * @date 2022/2/12
 **/
@Service
public class Schedule {

    @Resource
    private RedisTemplate redisTemplate;

    @Scheduled(cron = "0/30 * * * * ?")
    public void keepRedisClient(){
        System.out.println("保持连接");
        redisTemplate.getConnectionFactory().getConnection().ping();
    }
}
