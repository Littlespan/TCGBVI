package com.jt.test;


import org.apache.catalina.Host;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.*;
import redis.clients.jedis.params.SetParams;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/12 17:14
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
public class TestRedis {
    @Test
    public void Test01() throws InterruptedException {
        String host = "192.168.126.129";
        Integer port = 6379;
        Jedis jedis = new Jedis(host, port);
        jedis.set("cgb2006", "amd yes");
        String c = jedis.get("cgb2006");
        System.out.println(c);
        jedis.del("cgb2006");
//        if(jedis.exists("cgb2006")){
//            如果不存在  则赋值 setnx代替if else
//            jedis.setnx("cgb2006", "inter no");
//            设置k-v 并赋予超时时间 setex
//            jedis.setex("cgb2006", 60, "inter no");
//        set里三个参数 可以既实现 不存在赋值 又实现赋予超时时间
        jedis.set("cgb2006", "inter no", new SetParams().nx().ex(60));
//            jedis.set("cgb2006","inter no");
//            jedis.expire("cgb2006", 60);
        Thread.sleep(3000);
        System.out.println(jedis.ttl("cgb2006"));
        System.out.println(jedis.mget("cgb2006"));
        jedis.del("cgb2006");
//        }else{
    }


    @Test
    public void testShards(){
        List<JedisShardInfo> shardInfos = new ArrayList<>();
        shardInfos.add(new JedisShardInfo("192.168.126.129",6379));
        shardInfos.add(new JedisShardInfo("192.168.126.129",6380));
        shardInfos.add(new JedisShardInfo("192.168.126.129",6381));
        //准备分片对象
        ShardedJedis shardedJedis = new ShardedJedis(shardInfos);
        shardedJedis.set("shards", "redis分片测试");
        System.out.println(shardedJedis.get("shards"));
    }

    @Test
    public void testSentinel(){
        Set<String> sentinel = new HashSet<>();
        //传递哨兵的配置信息
        sentinel.add("192.168.126.129:26379");
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("mymaster", sentinel);
        Jedis jedis = jedisSentinelPool.getResource();
        jedis.set("sentinel", "哨兵测试");
        System.out.println(jedis.get("sentinel"));
    }

    @Test
    public void testCluster(){
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.126.129",7000));
        nodes.add(new HostAndPort("192.168.126.129",7001));
        nodes.add(new HostAndPort("192.168.126.129",7002));
        nodes.add(new HostAndPort("192.168.126.129",7003));
        nodes.add(new HostAndPort("192.168.126.129",7004));
        nodes.add(new HostAndPort("192.168.126.129",7005));
        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisCluster.set("cluster","测试redis集群");
        System.out.println(jedisCluster.get("cluster"));
    }
}
