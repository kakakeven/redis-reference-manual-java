package com.lintrip.springboot.redis.repository;

import com.alibaba.fastjson.JSON;
import com.lintrip.springboot.redis.domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookRepository {

    private static final Logger logger = LoggerFactory.getLogger(BookRepository.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    public Book getBook(Long id) {
        String bookKey = genCacheKey(id);
        String response = stringRedisTemplate.opsForValue().get(bookKey);
        return JSON.parseObject(response,Book.class);
    }

    public Long save(Book book) {
        Long id = book.getId();
        if (null == id) {
            id = getNextId();
            book.setId(id);
            String key = genCacheKey(id);
            String value = JSON.toJSONString(book);
            logger.info("新增图书-id:{},name:{}",id,book.getName());
            stringRedisTemplate.opsForValue().set(key,value);
        } else {
            String key = genCacheKey(book.getId());
            String value = JSON.toJSONString(book);
            stringRedisTemplate.opsForValue().set(key,value);
            logger.info("编辑图书-id:{},name:{}",book.getId(),book.getName());
        }
        return id;
    }

    private String genCacheKey(Long id) {
        return "book:{id}".replace("{id}",String.valueOf(id));
    }

    private Long getNextId() {
        Long key = redisTemplate.opsForValue().increment("book:id");
        return key;
    }
}
