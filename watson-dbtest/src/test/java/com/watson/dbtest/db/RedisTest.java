package com.watson.dbtest.db;

import com.watson.dbtest.annotation.Redis;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/235:52 PM
 */
@Redis(id="90Redis",masterName = "xxx",passwd = "xxx",sentinelNodes = "10.10.xx.94:3;10.10.1x1.33:6378;10.10.xx.96:6378")
public class RedisTest {
}
