package com.jt.aop;

import com.jt.annotation.CacheFind;
import com.jt.util.ObjectMapperUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.ShardedJedis;

import java.util.Arrays;

/**
 * @author 作者：hyh
 * @version v.1.0 创建时间：2020/10/13 16:52
 * @email 邮箱：15205698133@163.com
 * @description 描述：
 */
@Component
@Aspect
public class CacheAop {

    @Autowired
//    private Jedis jedis;//单台
//    private ShardedJedis jedis;//分片
//    private JedisSentinelPool jedisSentinelPool; //哨兵
    private JedisCluster jedisCluster;

//    @Pointcut("bean(itemCatServiceImpl)")
//    @Pointcut("within(com.jt.service..*)")
    @Pointcut("@annotation(com.jt.annotation.CacheFind)")
    public void pointCut(){
    //定义切入点表达式  只为了占位
    }

    //定义前置通知，与切入点表达式进行绑定，注意：绑定的是方法



    @Before("pointCut()")
    //pointcut()  表示切入点表达式的引用 适用于多个通知通用切入点的情况
    //直接写  适用于单个通知切入点的情况
//    @Before("bean(itemCatServiceImpl)")
    public void before(){
    }

//    @Around("bean(itemCatServiceImpl)")
    @Around("@annotation(cacheFind)")
    public Object around(ProceedingJoinPoint joinPoint,CacheFind cacheFind){
//        Jedis jedis = jedisSentinelPool.getResource();
//        String methodName = joinPoint.getSignature().getName();
//        String className = joinPoint.getSignature().getDeclaringTypeName();
//        Class<?> aClass = joinPoint.getTarget().getClass();
        long start = System.currentTimeMillis();
        Object result = null;
        try {
            //获得方法参数
            Object[] args = joinPoint.getArgs();
            //动态获得方法的返回值类型  首先获得方法对象
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            String key = cacheFind.preKey() + "::" +Arrays.toString(args);
            if(jedisCluster.exists(key)){
                String s = jedisCluster.get(key);
                result = ObjectMapperUtil.toObj(s, methodSignature.getReturnType());
            }else{
                result = joinPoint.proceed();
                String json = ObjectMapperUtil.toJson(result);
                if(cacheFind.seconds()>0){
                    jedisCluster.setex(key,cacheFind.seconds(), json);
                }else{
                    jedisCluster.set(key, json);
                }
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        long end = System.currentTimeMillis();
//        System.out.println("path = " + className + "." + methodName);
//        System.out.println("目标对象类型:" + aClass);
//        System.out.println("参数：" + Arrays.toString(joinPoint.getArgs()));
        System.out.println("执行时间：" + (end-start) );
//        jedis.close();
        return result;

    }

//    @AfterReturning("bean(itemCatServiceImpl)")
//    @AfterReturning("pointCut()")
//    public void afterReturning(){
//        System.out.println("我是后置通知");
//    }

//    @After("bean(itemCatServiceImpl)")
//    @After("pointCut()")
//    public void after(){
//        System.out.println("我是最终通知");
//    }
}
