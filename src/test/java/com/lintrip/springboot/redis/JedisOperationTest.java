package com.lintrip.springboot.redis;

import org.junit.Assert;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis 功能测试
 */
public class JedisOperationTest {

    @Test
    public void testSetAndGet() {
        Jedis jedis = new Jedis("localhost", 6379);
        String value = "bcd";
        String setResp = jedis.set("name", "bcd");
        System.out.println(setResp);
        String getResp = jedis.get("name");
        Assert.assertEquals(value, getResp);
        String pong = jedis.ping();
        Assert.assertEquals("PONG", pong);
        jedis.close();
    }

    @Test
    public void testJedisPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(10);
        poolConfig.setMaxTotal(20);
        JedisPool jedisPool = new JedisPool(poolConfig, "localhost", 6379);
        Jedis jedis = jedisPool.getResource();
        System.out.println(jedis.ping());
    }
}
