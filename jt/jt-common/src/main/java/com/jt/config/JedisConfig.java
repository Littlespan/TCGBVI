package com.jt.config;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.*;
import redis.clients.jedis.util.ShardInfo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/13 14:06
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@Configuration
@PropertySource("classpath:/properties/redis.properties")
public class JedisConfig {
//    @Value("${redis.host}")
//    private String host;
//    @Value("${redis.port}")
//    private Integer port;
//    @Bean
//    public Jedis jedis(){
//        return new Jedis(host,port);
//    }
//    @Value("${redis.nodes}")
//    private String nodes;
//
//    @Bean
//    public ShardedJedis shardedJedis(){
//        List<JedisShardInfo> shardInfos = new ArrayList<>();
//        nodes = nodes.trim();
//        String[] nodeArray = nodes.split(",");
//        for (String node:nodeArray) {
//            String host = node.split(":")[0];
//            int port = Integer.parseInt(node.split(":")[1]);
//            shardInfos.add(new JedisShardInfo(host,port));
//        }
//
//        //准备分片对象
//        return new ShardedJedis(shardInfos);
//    }

//    @Value("${redis.masterName}")
//    private String masterName;
//    @Value("${redis.sentinels}")
//    private String sentinels;
//
//    @Bean
//    public JedisSentinelPool jedisSentinel(){
//        Set<String> sentinel = new HashSet<>();
//        sentinel.add(sentinels);
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxTotal(100);
//        jedisPoolConfig.setMaxIdle(40);
//        jedisPoolConfig.setMaxWaitMillis(20);
//        return new JedisSentinelPool(masterName, sentinel,jedisPoolConfig);
//    }

    @Value("${redis.cluster}")
    private String nodes;

    @Bean
    public JedisCluster jedisCluster(){
        String[] node = nodes.split(",");
        Set<HostAndPort> hostAndPorts = new HashSet<>();
        for (String hap:node) {
            String host = hap.split(":")[0];
            int port = Integer.parseInt(hap.split(":")[1]);
            hostAndPorts.add(new HostAndPort(host,port));
        }
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        jedisPoolConfig.setMaxTotal(100);
//        jedisPoolConfig.setMaxIdle(40);
//        jedisPoolConfig.setMaxWaitMillis(20);
        return new JedisCluster(hostAndPorts);
    }
}
