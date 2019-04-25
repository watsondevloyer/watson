package com.watson.dbtest.db;

import com.watson.dbtest.annotation.Redis;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/235:52 PM
 */
@Redis(id="90Redis",masterName = "mymaster",passwd = "redis",sentinelNodes = "10.10.111.94:6378;10.10.111.95:6378;10.10.111.96:6378")
public class RedisTest {
}
