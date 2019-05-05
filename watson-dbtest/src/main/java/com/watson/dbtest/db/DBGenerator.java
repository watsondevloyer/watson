package com.watson.dbtest.db;

import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.watson.dbtest.annotation.Mongo;
import com.watson.dbtest.annotation.Mysql;
import com.watson.dbtest.annotation.Oracle;
import com.watson.dbtest.annotation.Redis;
import com.watson.dbtest.constant.Constant;
import com.watson.dbtest.utils.ClassPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.*;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/233:41 PM
 */
@Slf4j
public class DBGenerator {

    private Map<String,MySqlOperations> mySqlOperationsMap =new HashMap<String,MySqlOperations>();
    private Map<String,OracleOperations> oracleOperationsMap=new HashMap<String, OracleOperations>();
    private Map<String,RedisOperations> redisOperationsMap=new HashMap<String, RedisOperations>();
    private Map<String,MongoOperations> mongoOperationsMap=new HashMap<String, MongoOperations>();
    private DBGenerator(){}

    public static DBGenerator getInstance(){
        return DBGeneratorInner.dbGenerator;
    }
    private static class DBGeneratorInner{
        private final static DBGenerator dbGenerator=new DBGenerator();
    }

    public MySqlOperations getMysqlOperation(String id){
        if(mySqlOperationsMap.get(id)!=null){
            return mySqlOperationsMap.get(id);
        }else {
            Set<Class<?>> classesContainAnnotation = ClassPool.getClassesContainAnnotation(Mysql.class);
            for(Class<?> clazz:classesContainAnnotation){
                MySqlOperations mySqlOperations =null;
                Mysql annotation = clazz.getAnnotation(Mysql.class);
                String driver=annotation.driver();
                if(StringUtils.isBlank(driver)){
                    mySqlOperations = new MySqlOperations(annotation.username(), annotation.password(), annotation.url());
                }else {
                    mySqlOperations=new MySqlOperations(annotation.username(),annotation.password(),annotation.url(),annotation.driver());
                }
                mySqlOperationsMap.put(annotation.id(),mySqlOperations);
            }
        }
        return mySqlOperationsMap.get(id);
    }


    public OracleOperations getOracleOperation(String id){
        if(oracleOperationsMap.get(id)!=null){
            return oracleOperationsMap.get(id);
        }else {
            Set<Class<?>> classesContainAnnotation = ClassPool.getClassesContainAnnotation(Oracle.class);
            for(Class<?> clazz:classesContainAnnotation){
                Oracle annotation = clazz.getAnnotation(Oracle.class);
                OracleOperations oracleOperations = new OracleOperations(annotation.username(), annotation.password(), annotation.url());
                oracleOperationsMap.put(annotation.id(),oracleOperations);
            }
        }
        return oracleOperationsMap.get(id);
    }

    public RedisOperations getRedisOperations(String id){
        if(redisOperationsMap.get(id)!=null){
            return redisOperationsMap.get(id);
        }else {
            Set<Class<?>> classesContainAnnotation = ClassPool.getClassesContainAnnotation(Redis.class);
            for(Class<?> clazz:classesContainAnnotation){
                Redis annotation = clazz.getAnnotation(Redis.class);
                JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                jedisPoolConfig.setMaxTotal(Constant.MAXTOTAL);
                jedisPoolConfig.setMinIdle(Constant.MINIDLE);
                jedisPoolConfig.setMaxIdle(Constant.MAXIDLE);
                jedisPoolConfig.setMaxWaitMillis(Constant.MAXWAITMILLIS);
                jedisPoolConfig.setBlockWhenExhausted(Constant.BLOCKWHENEXHAUSTED);
                jedisPoolConfig.setTestOnBorrow(Constant.TESTONBORROW);
                jedisPoolConfig.setTestOnReturn(Constant.TESTONRETURN);
                jedisPoolConfig.setTestWhileIdle(Constant.TESTWHILEIDLE);
                jedisPoolConfig.setMinEvictableIdleTimeMillis(Constant.MINEVICTABLEIDLETIMEMILLIS);
                jedisPoolConfig.setTimeBetweenEvictionRunsMillis(Constant.TIMEBETWEENEVICTIONRUNSMILLIS);
                jedisPoolConfig.setNumTestsPerEvictionRun(Constant.NUMTESTSPEREVICTIONRUN);
                jedisPoolConfig.setSoftMinEvictableIdleTimeMillis(Constant.SOFTMINEVICTABLEIDLETIMEMILLIS);
                jedisPoolConfig.setLifo(Constant.LIFO);
                Set<String> sentinels = new HashSet<String>();
                sentinels.addAll(Arrays.asList(annotation.sentinelNodes().split(annotation.separator())));
                JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(annotation.masterName(), sentinels, jedisPoolConfig, Constant.CONNTIMEOUT, annotation.passwd(),Constant.DBINDEX);
                RedisOperations redisOperations = new RedisOperations(jedisSentinelPool);
                redisOperationsMap.put(annotation.id(),redisOperations);
            }
        }
        return redisOperationsMap.get(id);
    }

    public MongoOperations getMongoOperations(String id){
        if (mongoOperationsMap.get(id)!=null){
            return mongoOperationsMap.get(id);
        }else {
            Set<Class<?>> classesContainAnnotation = ClassPool.getClassesContainAnnotation(Mongo.class);
            for(Class<?> clazz:classesContainAnnotation){
                Mongo annotation = clazz.getAnnotation(Mongo.class);
                MongoOperations mongoOperations=null;

                if(StringUtils.isNotBlank(annotation.userName())||StringUtils.isNotBlank(annotation.passWord())||StringUtils.isNotBlank(annotation.dataBaseName())){
                    ServerAddress serverAddress=new ServerAddress(annotation.address(),annotation.port());
                    MongoCredential credential = MongoCredential.createScramSha1Credential(annotation.userName(), annotation.dataBaseName(), annotation.passWord().toCharArray());
                    List<MongoCredential> credentials = new ArrayList<MongoCredential>();
                    credentials.add(credential);
                    mongoOperations=new MongoOperations(serverAddress,credentials);

                }else {
                    mongoOperations=new MongoOperations(annotation.address(),annotation.port());
                }
                mongoOperationsMap.put(annotation.id(),mongoOperations);

            }
        }
        return mongoOperationsMap.get(id);
    }
}
