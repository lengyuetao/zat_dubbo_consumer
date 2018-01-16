package com.tao.dubbo.utils;



import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Protocol;
import redis.clients.util.Pool;

import java.util.List;

/**
 * Created by DELL on 2017/9/22.
 */
public class RedisUtils{

    private static Logger logger=Logger.getLogger(RedisUtils.class);

    private  static JedisPool jedisPool=new JedisPool();

    public static Jedis getResource(){
        Jedis jedis=null;
        try{
            jedis=jedisPool.getResource();

            logger.info("******获取jedis对象");
        }catch (Exception e){
            if(jedis!=null){
                jedis.close();
            }
        }
        return jedis;
    }


    public static String set(String key,String value,int exprieTime){
        Jedis jedis=null;
        String res=null;
        try{
            jedis=getResource();

             res = jedis.set(key, value);

            if(0!=exprieTime){
                jedis.expire(key,exprieTime);
            }
        }catch (Exception e){
            logger.info("添加失败"+e);
        }finally {
            jedis.close();
        }
        return res;
    }

    public static String get(String key){

        Jedis jedis=null;
        String value=null;
        try{
            jedis=getResource();

            if(jedis.exists(key)){
                value=jedis.get(key);
            }

            logger.info("value值："+value);
        }catch (Exception e){
            logger.info("添加失败"+e);
        }finally {
            jedis.close();
        }
        return value;

    }
}
