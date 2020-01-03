package com.lintrip.springboot.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/cache/get.action")
    public String getValue(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    @RequestMapping("/cache/set.action")
    public String setValue(String key,String value) {
        stringRedisTemplate.opsForValue().set(key,value);
        return "Success";
    }
}
