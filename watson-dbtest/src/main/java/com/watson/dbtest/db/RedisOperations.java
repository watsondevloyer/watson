package com.watson.dbtest.db;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.*;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.util.Slowlog;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/232:09 PM
 */
@Slf4j
public class RedisOperations  {

    private JedisSentinelPool jedisSentinelPool;
    public RedisOperations(JedisSentinelPool jedisSentinelPool) {
        this.jedisSentinelPool=jedisSentinelPool;
    }

    public String get(int dbIndex, final String key){
        return execute(dbIndex,new CacheCallback<String>() {

            public String doCallback(Redis redis) {
                return  redis.get(key);
            }
        });
    }

    public long append(int dbIndex, final String key, final String value){
        return execute(dbIndex,new CacheCallback<Long>() {
            public Long doCallback(Redis redis) {
                return redis.append(key,value);
            }
        });
    }

    public long del(int dbIndex, final String ... keys){
        return execute(dbIndex,new CacheCallback<Long>() {

            public Long doCallback(Redis redis) {
                return redis.del(keys);
            }
        });
    }

    public String set(int dbIndex, final String key, final String value){
        return execute(dbIndex,new CacheCallback<String>() {

            public String doCallback(Redis redis) {
                return redis.set(key,value);
            }
        });
    }


    public  <T> T execute(Integer dbIndex, CacheCallback<T> action) {
        Jedis jedis = jedisSentinelPool.getResource();
        try {
            if (dbIndex != null) {
                jedis.select(dbIndex);
            }
            return action.doCallback(new Redis(jedis));
        } catch (JedisConnectionException e) {
            log.error( e.getMessage());
            jedisSentinelPool.returnBrokenResource(jedis);
            throw e;
        } catch (Exception e) {
            log.error( e.getMessage());
            throw new RuntimeException(e);
        } finally {
            if (jedis != null && dbIndex != null) jedis.select(dbIndex);
            jedisSentinelPool.returnResource(jedis);
        }
    }

    public  class Redis {
        private Jedis jedis;

        public Redis(Jedis jedis) {
            this.jedis = jedis;
        }

        public Long append(String key, String value) {
            return jedis.append(key, value);
        }

        public String asking() {
            return jedis.asking();
        }

        public Long bitcount(String key, long start, long end) {
            return jedis.bitcount(key, start, end);
        }

        public Long bitcount(String key) {
            return jedis.bitcount(key);
        }

        public Long bitop(BitOP op, String destKey, String... srcKeys) {
            return jedis.bitop(op, destKey, srcKeys);
        }

        public List<String> blpop(int arg0, String... arg1) {
            return jedis.blpop(arg0, arg1);
        }

        public List<String> blpop(String... args) {
            return jedis.blpop(args);
        }

        public List<String> blpop(String arg) {
            return jedis.blpop(arg);
        }

        public List<String> brpop(int arg0, String... arg1) {
            return jedis.brpop(arg0, arg1);
        }

        public List<String> brpop(String... args) {
            return jedis.brpop(args);
        }

        public List<String> brpop(String arg) {
            return jedis.brpop(arg);
        }

        public String brpoplpush(String source, String destination, int timeout) {
            return jedis.brpoplpush(source, destination, timeout);
        }

        public Long decr(String key) {
            return jedis.decr(key);
        }

        public Long decrBy(String key, long integer) {
            return jedis.decrBy(key, integer);
        }

        public Long del(String... keys) {
            return jedis.del(keys);
        }

        public Long del(String key) {
            return jedis.del(key);
        }

        public String echo(String string) {
            return jedis.echo(string);
        }

        public Object eval(String script, int keyCount, String... params) {
            return jedis.eval(script, keyCount, params);
        }

        public Object eval(String script, List<String> keys, List<String> args) {
            return jedis.eval(script, keys, args);
        }

        public Object eval(String script) {
            return jedis.eval(script);
        }

        public Object evalsha(String sha1, int keyCount, String... params) {
            return jedis.evalsha(sha1, keyCount, params);
        }

        public Object evalsha(String sha1, List<String> keys, List<String> args) {
            return jedis.evalsha(sha1, keys, args);
        }

        public Object evalsha(String script) {
            return jedis.evalsha(script);
        }

        public Boolean exists(String key) {
            return jedis.exists(key);
        }

        public Long expire(String key, int seconds) {
            return jedis.expire(key, seconds);
        }

        public Long expireAt(String key, long unixTime) {
            return jedis.expireAt(key, unixTime);
        }

        public String get(String key) {
            return jedis.get(key);
        }

        public String getSet(String key, String value) {
            return jedis.getSet(key, value);
        }

        public Boolean getbit(String key, long offset) {
            return jedis.getbit(key, offset);
        }

        public String getrange(String key, long startOffset, long endOffset) {
            return jedis.getrange(key, startOffset, endOffset);
        }

        public Long hdel(String key, String... fields) {
            return jedis.hdel(key, fields);
        }

        public Boolean hexists(String key, String field) {
            return jedis.hexists(key, field);
        }

        public String hget(String key, String field) {
            return jedis.hget(key, field);
        }

        public Map<String, String> hgetAll(String key) {
            return jedis.hgetAll(key);
        }

        public Long hincrBy(String key, String field, long value) {
            return jedis.hincrBy(key, field, value);
        }

        public Double hincrByFloat(String key, String field, double increment) {
            return jedis.hincrByFloat(key, field, increment);
        }

        public Set<String> hkeys(String key) {
            return jedis.hkeys(key);
        }

        public Long hlen(String key) {
            return jedis.hlen(key);
        }

        public List<String> hmget(String key, String... fields) {
            return jedis.hmget(key, fields);
        }

        public String hmset(String key, Map<String, String> hash) {
            return jedis.hmset(key, hash);
        }



        public Long hset(String key, String field, String value) {
            return jedis.hset(key, field, value);
        }

        public Long hsetnx(String key, String field, String value) {
            return jedis.hsetnx(key, field, value);
        }

        public List<String> hvals(String key) {
            return jedis.hvals(key);
        }

        public Long incr(String key) {
            return jedis.incr(key);
        }

        public Long incrBy(String key, long integer) {
            return jedis.incrBy(key, integer);
        }

        public Double incrByFloat(String key, double increment) {
            return jedis.incrByFloat(key, increment);
        }

        public Set<String> keys(String pattern) {
            return jedis.keys(pattern);
        }

        public String lindex(String key, long index) {
            return jedis.lindex(key, index);
        }



        public Long llen(String key) {
            return jedis.llen(key);
        }

        public String lpop(String key) {
            return jedis.lpop(key);
        }

        public Long lpush(String key, String... strings) {
            return jedis.lpush(key, strings);
        }

        public Long lpushx(String key, String... string) {
            return jedis.lpushx(key, string);
        }

        public List<String> lrange(String key, long start, long end) {
            return jedis.lrange(key, start, end);
        }

        public Long lrem(String key, long count, String value) {
            return jedis.lrem(key, count, value);
        }

        public String lset(String key, long index, String value) {
            return jedis.lset(key, index, value);
        }

        public String ltrim(String key, long start, long end) {
            return jedis.ltrim(key, start, end);
        }

        public List<String> mget(String... keys) {
            return jedis.mget(keys);
        }

        public String migrate(String host, int port, String key, int destinationDb, int timeout) {
            return jedis.migrate(host, port, key, destinationDb, timeout);
        }

        public Long move(String key, int dbIndex) {
            return jedis.move(key, dbIndex);
        }

        public String mset(String... keysvalues) {
            return jedis.mset(keysvalues);
        }

        public Long msetnx(String... keysvalues) {
            return jedis.msetnx(keysvalues);
        }

        public String objectEncoding(String string) {
            return jedis.objectEncoding(string);
        }

        public Long objectIdletime(String string) {
            return jedis.objectIdletime(string);
        }

        public Long objectRefcount(String string) {
            return jedis.objectRefcount(string);
        }

        public Long persist(String key) {
            return jedis.persist(key);
        }

        public Long pexpire(String key, int milliseconds) {
            return jedis.pexpire(key, milliseconds);
        }

        public Long pexpireAt(String key, long millisecondsTimestamp) {
            return jedis.pexpireAt(key, millisecondsTimestamp);
        }

        public String psetex(String key, int milliseconds, String value) {
            return jedis.psetex(key, milliseconds, value);
        }

        public void psubscribe(JedisPubSub jedisPubSub, String... patterns) {

            jedis.psubscribe(jedisPubSub, patterns);
        }

        public Long pttl(String key) {
            return jedis.pttl(key);
        }

        public Long publish(String channel, String message) {
            return jedis.publish(channel, message);
        }

        public List<String> pubsubChannels(String pattern) {
            return jedis.pubsubChannels(pattern);
        }

        public Long pubsubNumPat() {
            return jedis.pubsubNumPat();
        }

        public Map<String, String> pubsubNumSub(String... channels) {
            return jedis.pubsubNumSub(channels);
        }

        public String randomKey() {
            return jedis.randomKey();
        }

        public String rename(String oldkey, String newkey) {
            return jedis.rename(oldkey, newkey);
        }

        public Long renamenx(String oldkey, String newkey) {
            return jedis.renamenx(oldkey, newkey);
        }

        public String restore(String key, int ttl, byte[] serializedValue) {
            return jedis.restore(key, ttl, serializedValue);
        }

        public String rpop(String key) {
            return jedis.rpop(key);
        }

        public String rpoplpush(String srckey, String dstkey) {
            return jedis.rpoplpush(srckey, dstkey);
        }

        public Long rpush(String key, String... strings) {
            return jedis.rpush(key, strings);
        }

        public Long rpushx(String key, String... string) {
            return jedis.rpushx(key, string);
        }

        public Long sadd(String key, String... members) {
            return jedis.sadd(key, members);
        }

        public Long scard(String key) {
            return jedis.scard(key);
        }

        public List<Boolean> scriptExists(String... arg0) {
            return jedis.scriptExists(arg0);
        }

        public Boolean scriptExists(String sha1) {
            return jedis.scriptExists(sha1);
        }

        public String scriptLoad(String script) {
            return jedis.scriptLoad(script);
        }

        public Set<String> sdiff(String... keys) {
            return jedis.sdiff(keys);
        }

        public Long sdiffstore(String dstkey, String... keys) {
            return jedis.sdiffstore(dstkey, keys);
        }

        public String sentinelFailover(String masterName) {
            return jedis.sentinelFailover(masterName);
        }

        public List<String> sentinelGetMasterAddrByName(String masterName) {
            return jedis.sentinelGetMasterAddrByName(masterName);
        }

        public List<Map<String, String>> sentinelMasters() {
            return jedis.sentinelMasters();
        }

        public String sentinelMonitor(String masterName, String ip, int port, int quorum) {
            return jedis.sentinelMonitor(masterName, ip, port, quorum);
        }

        public String sentinelRemove(String masterName) {
            return jedis.sentinelRemove(masterName);
        }

        public Long sentinelReset(String pattern) {
            return jedis.sentinelReset(pattern);
        }

        public String sentinelSet(String arg0, Map<String, String> arg1) {
            return jedis.sentinelSet(arg0, arg1);
        }

        public List<Map<String, String>> sentinelSlaves(String arg0) {
            return jedis.sentinelSlaves(arg0);
        }

        public String set(String key, String value, String nxxx, String expx, int time) {
            return jedis.set(key, value, nxxx, expx, time);
        }

        public String set(String key, String value, String nxxx, String expx, long time) {
            return jedis.set(key, value, nxxx, expx, time);
        }

        public String set(String key, String value, String nxxx) {
            return jedis.set(key, value, nxxx);
        }

        public String set(String key, String value) {
            return jedis.set(key, value);
        }

        public Boolean setbit(String key, long offset, boolean value) {
            return jedis.setbit(key, offset, value);
        }

        public Boolean setbit(String key, long offset, String value) {
            return jedis.setbit(key, offset, value);
        }

        public String setex(String key, int seconds, String value) {
            return jedis.setex(key, seconds, value);
        }

        public Long setnx(String key, String value) {
            return jedis.setnx(key, value);
        }

        public Long setrange(String key, long offset, String value) {
            return jedis.setrange(key, offset, value);
        }

        public Set<String> sinter(String... keys) {
            return jedis.sinter(keys);
        }

        public Long sinterstore(String dstkey, String... keys) {
            return jedis.sinterstore(dstkey, keys);
        }

        public Boolean sismember(String key, String member) {
            return jedis.sismember(key, member);
        }

        public List<Slowlog> slowlogGet() {
            return jedis.slowlogGet();
        }

        public List<Slowlog> slowlogGet(long entries) {
            return jedis.slowlogGet(entries);
        }

        public Set<String> smembers(String key) {
            return jedis.smembers(key);
        }

        public Long smove(String srckey, String dstkey, String member) {
            return jedis.smove(srckey, dstkey, member);
        }

        public Long sort(String key, SortingParams sortingParameters, String dstkey) {
            return jedis.sort(key, sortingParameters, dstkey);
        }

        public List<String> sort(String key, SortingParams sortingParameters) {
            return jedis.sort(key, sortingParameters);
        }

        public Long sort(String key, String dstkey) {
            return jedis.sort(key, dstkey);
        }

        public List<String> sort(String key) {
            return jedis.sort(key);
        }

        public String spop(String key) {
            return jedis.spop(key);
        }

        public List<String> srandmember(String key, int count) {
            return jedis.srandmember(key, count);
        }

        public String srandmember(String key) {
            return jedis.srandmember(key);
        }

        public Long srem(String key, String... members) {
            return jedis.srem(key, members);
        }

        public ScanResult<String> sscan(String arg0, String arg1, ScanParams arg2) {
            return jedis.sscan(arg0, arg1, arg2);
        }

        public ScanResult<String> sscan(String key, String cursor) {
            return jedis.sscan(key, cursor);
        }

        public Long strlen(String key) {
            return jedis.strlen(key);
        }

        public void subscribe(JedisPubSub jedisPubSub, String... channels) {

            jedis.subscribe(jedisPubSub, channels);
        }

        public String substr(String key, int start, int end) {
            return jedis.substr(key, start, end);
        }

        public Set<String> sunion(String... keys) {
            return jedis.sunion(keys);
        }

        public Long sunionstore(String dstkey, String... keys) {
            return jedis.sunionstore(dstkey, keys);
        }

        public Long ttl(String key) {
            return jedis.ttl(key);
        }

        public String type(String key) {
            return jedis.type(key);
        }

        public String watch(String... keys) {
            return jedis.watch(keys);
        }

        public Long zadd(String key, double score, String member) {
            return jedis.zadd(key, score, member);
        }

        public Long zadd(String key, Map<String, Double> scoreMembers) {
            return jedis.zadd(key, scoreMembers);
        }

        public Long zcard(String key) {
            return jedis.zcard(key);
        }

        public Long zcount(String key, double min, double max) {
            return jedis.zcount(key, min, max);
        }

        public Long zcount(String key, String min, String max) {
            return jedis.zcount(key, min, max);
        }

        public Double zincrby(String key, double score, String member) {
            return jedis.zincrby(key, score, member);
        }

        public Long zinterstore(String dstkey, String... sets) {
            return jedis.zinterstore(dstkey, sets);
        }

        public Long zinterstore(String dstkey, ZParams params, String... sets) {
            return jedis.zinterstore(dstkey, params, sets);
        }

        public Set<String> zrange(String key, long start, long end) {
            return jedis.zrange(key, start, end);
        }

        public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
            return jedis.zrangeByScore(key, min, max, offset, count);
        }

        public Set<String> zrangeByScore(String key, double min, double max) {
            return jedis.zrangeByScore(key, min, max);
        }

        public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
            return jedis.zrangeByScore(key, min, max, offset, count);
        }

        public Set<String> zrangeByScore(String key, String min, String max) {
            return jedis.zrangeByScore(key, min, max);
        }

        public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
            return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        }

        public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
            return jedis.zrangeByScoreWithScores(key, min, max);
        }

        public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
            return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        }

        public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
            return jedis.zrangeByScoreWithScores(key, min, max);
        }

        public Set<Tuple> zrangeWithScores(String key, long start, long end) {
            return jedis.zrangeWithScores(key, start, end);
        }

        public Long zrank(String key, String member) {
            return jedis.zrank(key, member);
        }

        public Long zrem(String key, String... members) {
            return jedis.zrem(key, members);
        }

        public Long zremrangeByRank(String key, long start, long end) {
            return jedis.zremrangeByRank(key, start, end);
        }

        public Long zremrangeByScore(String key, double start, double end) {
            return jedis.zremrangeByScore(key, start, end);
        }

        public Long zremrangeByScore(String key, String start, String end) {
            return jedis.zremrangeByScore(key, start, end);
        }

        public Set<String> zrevrange(String key, long start, long end) {
            return jedis.zrevrange(key, start, end);
        }

        public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
            return jedis.zrevrangeByScore(key, max, min, offset, count);
        }

        public Set<String> zrevrangeByScore(String key, double max, double min) {
            return jedis.zrevrangeByScore(key, max, min);
        }

        public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
            return jedis.zrevrangeByScore(key, max, min, offset, count);
        }

        public Set<String> zrevrangeByScore(String key, String max, String min) {
            return jedis.zrevrangeByScore(key, max, min);
        }

        public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
            return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
        }

        public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
            return jedis.zrevrangeByScoreWithScores(key, max, min);
        }

        public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
            return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
        }

        public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
            return jedis.zrevrangeByScoreWithScores(key, max, min);
        }

        public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
            return jedis.zrevrangeWithScores(key, start, end);
        }

        public Long zrevrank(String key, String member) {
            return jedis.zrevrank(key, member);
        }

        public ScanResult<Tuple> zscan(String key, String cursor, ScanParams params) {
            return jedis.zscan(key, cursor, params);
        }

        public ScanResult<Tuple> zscan(String key, String cursor) {
            return jedis.zscan(key, cursor);
        }

        public Double zscore(String key, String member) {
            return jedis.zscore(key, member);
        }

        public Long zunionstore(String dstkey, String... sets) {
            return jedis.zunionstore(dstkey, sets);
        }

        public Long zunionstore(String dstkey, ZParams params, String... sets) {
            return jedis.zunionstore(dstkey, params, sets);
        }

        public Long append(byte[] key, byte[] value) {
            return jedis.append(key, value);
        }

        public String auth(String password) {
            return jedis.auth(password);
        }

        public String bgrewriteaof() {
            return jedis.bgrewriteaof();
        }

        public String bgsave() {
            return jedis.bgsave();
        }

        public Long bitcount(byte[] key, long start, long end) {
            return jedis.bitcount(key, start, end);
        }

        public Long bitcount(byte[] key) {
            return jedis.bitcount(key);
        }

        public Long bitop(BitOP op, byte[] destKey, byte[]... srcKeys) {
            return jedis.bitop(op, destKey, srcKeys);
        }

        public List<byte[]> blpop(byte[]... args) {
            return jedis.blpop(args);
        }

        public List<byte[]> blpop(byte[] arg) {
            return jedis.blpop(arg);
        }

        public List<byte[]> blpop(int arg0, byte[]... arg1) {
            return jedis.blpop(arg0, arg1);
        }

        public List<byte[]> brpop(byte[]... args) {
            return jedis.brpop(args);
        }

        public List<byte[]> brpop(byte[] arg) {
            return jedis.brpop(arg);
        }

        public List<byte[]> brpop(int arg0, byte[]... arg1) {
            return jedis.brpop(arg0, arg1);
        }

        public byte[] brpoplpush(byte[] source, byte[] destination, int timeout) {
            return jedis.brpoplpush(source, destination, timeout);
        }

        public String clientGetname() {
            return jedis.clientGetname();
        }

        public String clientKill(byte[] client) {
            return jedis.clientKill(client);
        }

        public String clientList() {
            return jedis.clientList();
        }

        public String clientSetname(byte[] name) {
            return jedis.clientSetname(name);
        }

        public List<byte[]> configGet(byte[] pattern) {
            return jedis.configGet(pattern);
        }

        public String configResetStat() {
            return jedis.configResetStat();
        }

        public byte[] configSet(byte[] parameter, byte[] value) {
            return jedis.configSet(parameter, value);
        }

        public Long decr(byte[] key) {
            return jedis.decr(key);
        }

        public Long decrBy(byte[] key, long integer) {
            return jedis.decrBy(key, integer);
        }

        public Long del(byte[]... keys) {
            return jedis.del(keys);
        }

        public Long del(byte[] key) {
            return jedis.del(key);
        }

        public void disconnect() {

            jedis.disconnect();
        }

        public byte[] dump(byte[] key) {
            return jedis.dump(key);
        }

        public byte[] echo(byte[] string) {
            return jedis.echo(string);
        }

        public Object eval(byte[] script, byte[] keyCount, byte[]... params) {
            return jedis.eval(script, keyCount, params);
        }

        public Object eval(byte[] script, int keyCount, byte[]... params) {
            return jedis.eval(script, keyCount, params);
        }

        public Object eval(byte[] script, List<byte[]> keys, List<byte[]> args) {
            return jedis.eval(script, keys, args);
        }

        public Object eval(byte[] script) {
            return jedis.eval(script);
        }

        public Object evalsha(byte[] sha1, int keyCount, byte[]... params) {
            return jedis.evalsha(sha1, keyCount, params);
        }

        public Object evalsha(byte[] arg0, List<byte[]> arg1, List<byte[]> arg2) {
            return jedis.evalsha(arg0, arg1, arg2);
        }

        public Object evalsha(byte[] sha1) {
            return jedis.evalsha(sha1);
        }

        public Boolean exists(byte[] key) {
            return jedis.exists(key);
        }

        public Long expire(byte[] key, int seconds) {
            return jedis.expire(key, seconds);
        }

        public Long expireAt(byte[] key, long unixTime) {
            return jedis.expireAt(key, unixTime);
        }

        public byte[] get(byte[] key) {
            return jedis.get(key);
        }

        public byte[] getSet(byte[] key, byte[] value) {
            return jedis.getSet(key, value);
        }

        public Boolean getbit(byte[] key, long offset) {
            return jedis.getbit(key, offset);
        }

        public byte[] getrange(byte[] key, long startOffset, long endOffset) {
            return jedis.getrange(key, startOffset, endOffset);
        }

        public Long hdel(byte[] key, byte[]... fields) {
            return jedis.hdel(key, fields);
        }

        public Boolean hexists(byte[] key, byte[] field) {
            return jedis.hexists(key, field);
        }

        public byte[] hget(byte[] key, byte[] field) {
            return jedis.hget(key, field);
        }

        public Map<byte[], byte[]> hgetAll(byte[] key) {
            return jedis.hgetAll(key);
        }

        public Long hincrBy(byte[] key, byte[] field, long value) {
            return jedis.hincrBy(key, field, value);
        }

        public Double hincrByFloat(byte[] key, byte[] field, double increment) {
            return jedis.hincrByFloat(key, field, increment);
        }

        public Set<byte[]> hkeys(byte[] key) {
            return jedis.hkeys(key);
        }

        public Long hlen(byte[] key) {
            return jedis.hlen(key);
        }

        public List<byte[]> hmget(byte[] key, byte[]... fields) {
            return jedis.hmget(key, fields);
        }

        public String hmset(byte[] key, Map<byte[], byte[]> hash) {
            return jedis.hmset(key, hash);
        }

        public Long hset(byte[] key, byte[] field, byte[] value) {
            return jedis.hset(key, field, value);
        }

        public Long hsetnx(byte[] key, byte[] field, byte[] value) {
            return jedis.hsetnx(key, field, value);
        }

        public List<byte[]> hvals(byte[] key) {
            return jedis.hvals(key);
        }

        public Long incr(byte[] key) {
            return jedis.incr(key);
        }

        public Long incrBy(byte[] key, long integer) {
            return jedis.incrBy(key, integer);
        }

        public Double incrByFloat(byte[] key, double increment) {
            return jedis.incrByFloat(key, increment);
        }

        public String info() {
            return jedis.info();
        }

        public String info(String section) {
            return jedis.info(section);
        }

        public boolean isConnected() {
            return jedis.isConnected();
        }

        public Set<byte[]> keys(byte[] pattern) {
            return jedis.keys(pattern);
        }

        public Long lastsave() {
            return jedis.lastsave();
        }

        public byte[] lindex(byte[] key, long index) {
            return jedis.lindex(key, index);
        }

        public Long linsert(byte[] key, BinaryClient.LIST_POSITION where, byte[] pivot, byte[] value) {
            return jedis.linsert(key, where, pivot, value);
        }

        public Long llen(byte[] key) {
            return jedis.llen(key);
        }

        public byte[] lpop(byte[] key) {
            return jedis.lpop(key);
        }

        public Long lpush(byte[] key, byte[]... strings) {
            return jedis.lpush(key, strings);
        }

        public Long lpushx(byte[] key, byte[]... string) {
            return jedis.lpushx(key, string);
        }

        public List<byte[]> lrange(byte[] key, long start, long end) {
            return jedis.lrange(key, start, end);
        }

        public Long lrem(byte[] key, long count, byte[] value) {
            return jedis.lrem(key, count, value);
        }

        public String lset(byte[] key, long index, byte[] value) {
            return jedis.lset(key, index, value);
        }

        public String ltrim(byte[] key, long start, long end) {
            return jedis.ltrim(key, start, end);
        }

        public List<byte[]> mget(byte[]... keys) {
            return jedis.mget(keys);
        }

        public String migrate(byte[] host, int port, byte[] key, int destinationDb, int timeout) {
            return jedis.migrate(host, port, key, destinationDb, timeout);
        }

        public Long move(byte[] key, int dbIndex) {
            return jedis.move(key, dbIndex);
        }

        public String mset(byte[]... keysvalues) {
            return jedis.mset(keysvalues);
        }

        public Long msetnx(byte[]... keysvalues) {
            return jedis.msetnx(keysvalues);
        }

        public Transaction multi() {
            return jedis.multi();
        }

        public byte[] objectEncoding(byte[] key) {
            return jedis.objectEncoding(key);
        }

        public Long objectIdletime(byte[] key) {
            return jedis.objectIdletime(key);
        }

        public Long objectRefcount(byte[] key) {
            return jedis.objectRefcount(key);
        }

        public Long persist(byte[] key) {
            return jedis.persist(key);
        }

        public Long pexpire(byte[] key, int milliseconds) {
            return jedis.pexpire(key, milliseconds);
        }

        public Long pexpireAt(byte[] key, long millisecondsTimestamp) {
            return jedis.pexpireAt(key, millisecondsTimestamp);
        }

        public String ping() {
            return jedis.ping();
        }

        public Pipeline pipelined() {
            return jedis.pipelined();
        }

        public String psetex(byte[] key, int milliseconds, byte[] value) {
            return jedis.psetex(key, milliseconds, value);
        }

        public void psubscribe(BinaryJedisPubSub jedisPubSub, byte[]... patterns) {

            jedis.psubscribe(jedisPubSub, patterns);
        }

        public Long pttl(byte[] key) {
            return jedis.pttl(key);
        }

        public Long publish(byte[] channel, byte[] message) {
            return jedis.publish(channel, message);
        }

        public byte[] randomBinaryKey() {
            return jedis.randomBinaryKey();
        }

        public String rename(byte[] oldkey, byte[] newkey) {
            return jedis.rename(oldkey, newkey);
        }

        public Long renamenx(byte[] oldkey, byte[] newkey) {
            return jedis.renamenx(oldkey, newkey);
        }

        public void resetState() {
            jedis.resetState();
        }

        public String restore(byte[] key, int ttl, byte[] serializedValue) {
            return jedis.restore(key, ttl, serializedValue);
        }

        public byte[] rpop(byte[] key) {
            return jedis.rpop(key);
        }

        public byte[] rpoplpush(byte[] srckey, byte[] dstkey) {
            return jedis.rpoplpush(srckey, dstkey);
        }

        public Long rpush(byte[] key, byte[]... strings) {
            return jedis.rpush(key, strings);
        }

        public Long rpushx(byte[] key, byte[]... string) {
            return jedis.rpushx(key, string);
        }

        public Long sadd(byte[] key, byte[]... members) {
            return jedis.sadd(key, members);
        }

        public String save() {
            return jedis.save();
        }

        public Long scard(byte[] key) {
            return jedis.scard(key);
        }

        public List<Long> scriptExists(byte[]... sha1) {
            return jedis.scriptExists(sha1);
        }

        public String scriptFlush() {
            return jedis.scriptFlush();
        }

        public String scriptKill() {
            return jedis.scriptKill();
        }

        public byte[] scriptLoad(byte[] script) {
            return jedis.scriptLoad(script);
        }

        public Set<byte[]> sdiff(byte[]... keys) {
            return jedis.sdiff(keys);
        }

        public Long sdiffstore(byte[] dstkey, byte[]... keys) {
            return jedis.sdiffstore(dstkey, keys);
        }

        public String set(byte[] key, byte[] value, byte[] nxxx, byte[] expx, int time) {
            return jedis.set(key, value, nxxx, expx, time);
        }

        public String set(byte[] key, byte[] value, byte[] nxxx, byte[] expx, long time) {
            return jedis.set(key, value, nxxx, expx, time);
        }

        public String set(byte[] key, byte[] value, byte[] nxxx) {
            return jedis.set(key, value, nxxx);
        }

        public String set(byte[] key, byte[] value) {
            return jedis.set(key, value);
        }

        public Boolean setbit(byte[] key, long offset, boolean value) {
            return jedis.setbit(key, offset, value);
        }

        public Boolean setbit(byte[] key, long offset, byte[] value) {
            return jedis.setbit(key, offset, value);
        }

        public String setex(byte[] key, int seconds, byte[] value) {
            return jedis.setex(key, seconds, value);
        }

        public Long setnx(byte[] key, byte[] value) {
            return jedis.setnx(key, value);
        }

        public Long setrange(byte[] key, long offset, byte[] value) {
            return jedis.setrange(key, offset, value);
        }

        public Set<byte[]> sinter(byte[]... keys) {
            return jedis.sinter(keys);
        }

        public Long sinterstore(byte[] dstkey, byte[]... keys) {
            return jedis.sinterstore(dstkey, keys);
        }

        public Boolean sismember(byte[] key, byte[] member) {
            return jedis.sismember(key, member);
        }

        public String slaveof(String host, int port) {
            return jedis.slaveof(host, port);
        }

        public String slaveofNoOne() {
            return jedis.slaveofNoOne();
        }

        public List<byte[]> slowlogGetBinary() {
            return jedis.slowlogGetBinary();
        }

        public List<byte[]> slowlogGetBinary(long entries) {
            return jedis.slowlogGetBinary(entries);
        }

        public Long slowlogLen() {
            return jedis.slowlogLen();
        }

        public String slowlogReset() {
            return jedis.slowlogReset();
        }

        public Set<byte[]> smembers(byte[] key) {
            return jedis.smembers(key);
        }

        public Long smove(byte[] srckey, byte[] dstkey, byte[] member) {
            return jedis.smove(srckey, dstkey, member);
        }

        public Long sort(byte[] key, byte[] dstkey) {
            return jedis.sort(key, dstkey);
        }

        public Long sort(byte[] key, SortingParams sortingParameters, byte[] dstkey) {
            return jedis.sort(key, sortingParameters, dstkey);
        }

        public List<byte[]> sort(byte[] key, SortingParams sortingParameters) {
            return jedis.sort(key, sortingParameters);
        }

        public List<byte[]> sort(byte[] key) {
            return jedis.sort(key);
        }

        public byte[] spop(byte[] key) {
            return jedis.spop(key);
        }

        public List<byte[]> srandmember(byte[] key, int count) {
            return jedis.srandmember(key, count);
        }

        public byte[] srandmember(byte[] key) {
            return jedis.srandmember(key);
        }

        public Long srem(byte[] key, byte[]... member) {
            return jedis.srem(key, member);
        }

        public Long strlen(byte[] key) {
            return jedis.strlen(key);
        }

        public void subscribe(BinaryJedisPubSub jedisPubSub, byte[]... channels) {

            jedis.subscribe(jedisPubSub, channels);
        }

        public byte[] substr(byte[] key, int start, int end) {
            return jedis.substr(key, start, end);
        }

        public Set<byte[]> sunion(byte[]... keys) {
            return jedis.sunion(keys);
        }

        public Long sunionstore(byte[] dstkey, byte[]... keys) {
            return jedis.sunionstore(dstkey, keys);
        }

        public List<String> time() {
            return jedis.time();
        }

        public Long ttl(byte[] key) {
            return jedis.ttl(key);
        }

        public String type(byte[] key) {
            return jedis.type(key);
        }

        public String unwatch() {
            return jedis.unwatch();
        }

        public String watch(byte[]... keys) {
            return jedis.watch(keys);
        }

        public Long zadd(byte[] key, double score, byte[] member) {
            return jedis.zadd(key, score, member);
        }

        public Long zadd(byte[] key, Map<byte[], Double> scoreMembers) {
            return jedis.zadd(key, scoreMembers);
        }

        public Long zcard(byte[] key) {
            return jedis.zcard(key);
        }

        public Long zcount(byte[] key, byte[] min, byte[] max) {
            return jedis.zcount(key, min, max);
        }

        public Long zcount(byte[] key, double min, double max) {
            return jedis.zcount(key, min, max);
        }

        public Double zincrby(byte[] key, double score, byte[] member) {
            return jedis.zincrby(key, score, member);
        }

        public Long zinterstore(byte[] dstkey, byte[]... sets) {
            return jedis.zinterstore(dstkey, sets);
        }

        public Long zinterstore(byte[] dstkey, ZParams params, byte[]... sets) {
            return jedis.zinterstore(dstkey, params, sets);
        }

        public Set<byte[]> zrange(byte[] key, long start, long end) {
            return jedis.zrange(key, start, end);
        }

        public Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max, int offset, int count) {
            return jedis.zrangeByScore(key, min, max, offset, count);
        }

        public Set<byte[]> zrangeByScore(byte[] key, byte[] min, byte[] max) {
            return jedis.zrangeByScore(key, min, max);
        }

        public Set<byte[]> zrangeByScore(byte[] key, double min, double max, int offset, int count) {
            return jedis.zrangeByScore(key, min, max, offset, count);
        }

        public Set<byte[]> zrangeByScore(byte[] key, double min, double max) {
            return jedis.zrangeByScore(key, min, max);
        }

        public Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max, int offset, int count) {
            return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        }

        public Set<Tuple> zrangeByScoreWithScores(byte[] key, byte[] min, byte[] max) {
            return jedis.zrangeByScoreWithScores(key, min, max);
        }

        public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max, int offset, int count) {
            return jedis.zrangeByScoreWithScores(key, min, max, offset, count);
        }

        public Set<Tuple> zrangeByScoreWithScores(byte[] key, double min, double max) {
            return jedis.zrangeByScoreWithScores(key, min, max);
        }

        public Set<Tuple> zrangeWithScores(byte[] key, long start, long end) {
            return jedis.zrangeWithScores(key, start, end);
        }

        public Long zrank(byte[] key, byte[] member) {
            return jedis.zrank(key, member);
        }

        public Long zrem(byte[] key, byte[]... members) {
            return jedis.zrem(key, members);
        }

        public Long zremrangeByRank(byte[] key, long start, long end) {
            return jedis.zremrangeByRank(key, start, end);
        }

        public Long zremrangeByScore(byte[] key, byte[] start, byte[] end) {
            return jedis.zremrangeByScore(key, start, end);
        }

        public Long zremrangeByScore(byte[] key, double start, double end) {
            return jedis.zremrangeByScore(key, start, end);
        }

        public Set<byte[]> zrevrange(byte[] key, long start, long end) {
            return jedis.zrevrange(key, start, end);
        }

        public Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min, int offset, int count) {
            return jedis.zrevrangeByScore(key, max, min, offset, count);
        }

        public Set<byte[]> zrevrangeByScore(byte[] key, byte[] max, byte[] min) {
            return jedis.zrevrangeByScore(key, max, min);
        }

        public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min, int offset, int count) {
            return jedis.zrevrangeByScore(key, max, min, offset, count);
        }

        public Set<byte[]> zrevrangeByScore(byte[] key, double max, double min) {
            return jedis.zrevrangeByScore(key, max, min);
        }

        public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min, int offset, int count) {
            return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
        }

        public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, byte[] max, byte[] min) {
            return jedis.zrevrangeByScoreWithScores(key, max, min);
        }

        public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min, int offset, int count) {
            return jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
        }

        public Set<Tuple> zrevrangeByScoreWithScores(byte[] key, double max, double min) {
            return jedis.zrevrangeByScoreWithScores(key, max, min);
        }

        public Set<Tuple> zrevrangeWithScores(byte[] key, long start, long end) {
            return jedis.zrevrangeWithScores(key, start, end);
        }

        public Long zrevrank(byte[] key, byte[] member) {
            return jedis.zrevrank(key, member);
        }

        public Double zscore(byte[] key, byte[] member) {
            return jedis.zscore(key, member);
        }

        public Long zunionstore(byte[] dstkey, byte[]... sets) {
            return jedis.zunionstore(dstkey, sets);
        }

        public Long zunionstore(byte[] dstkey, ZParams params, byte[]... sets) {
            return jedis.zunionstore(dstkey, params, sets);
        }

    }

    public static interface CacheCallback<T> {
        T doCallback(Redis redis);
    }
}
