package com.lintrip.springboot.redis.repository;

import com.alibaba.fastjson.JSON;
import com.lintrip.springboot.redis.domain.Event;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TodoRepository {

    private static final Logger logger = LoggerFactory.getLogger(TodoRepository.class);

    @Autowired
    private RedisTemplate redisTemplate;

    public List<Event> getUserToDoList(Long userId) {
        String userToDoListKey = genToDoListKey(userId);
        List<Event> eventList = new ArrayList<>();
        List<String> responseList = redisTemplate.opsForList().range(userToDoListKey, 0, -1);
        if (CollectionUtils.isNotEmpty(responseList)) {
            for (String resp:responseList) {
                Event event = JSON.parseObject(resp, Event.class);
                eventList.add(event);
            }
        }
        return eventList;
    }


    public List<Event> getUserDoneList(Long userId) {
        String userDoneListKey = genDoneListKey(userId);
        List<Event> eventList = new ArrayList<>();
        List<String> responseList = redisTemplate.opsForList().range(userDoneListKey, 0, -1);
        if (CollectionUtils.isNotEmpty(responseList)) {
            for (String resp:responseList) {
                Event event = JSON.parseObject(resp, Event.class);
                eventList.add(event);
            }
        }
        return eventList;
    }

    private String genToDoListKey(Long userId) {
        return "user:{userId}.todoList.key".replace("{userId}", String.valueOf(userId));
    }

    private String genDoneListKey(Long userId) {
        return "user:{userId}.doneList.key".replace("{userId}", String.valueOf(userId));
    }

    public void save(Long userId, Event event) {
        String userToDoListKey = genToDoListKey(userId);
        String value = JSON.toJSONString(event);
        redisTemplate.opsForList().leftPush(userToDoListKey,value);
    }
}
