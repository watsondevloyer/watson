package com.watson.dbtest.constant;

/**
 * @author guochang.xie
 * @Description: TODO
 * @date 2019/4/235:28 PM
 */
public class Constant {

    /**
     * redis相关配置
     */
    public final static int CONNTIMEOUT=4000;
    public final static int MAXTOTAL=20;
    public final static int MINIDLE=1;
    public final static int MAXIDLE=5;
    public final static long MAXWAITMILLIS=3000;
    public final static boolean BLOCKWHENEXHAUSTED=true;
    public final static boolean TESTONBORROW=true;
    public final static boolean TESTONRETURN=false;
    public final static boolean TESTWHILEIDLE=true;
    public final static long MINEVICTABLEIDLETIMEMILLIS=60000;
    public final static long TIMEBETWEENEVICTIONRUNSMILLIS=30000;
    public final static int NUMTESTSPEREVICTIONRUN=-1;
    public final static long SOFTMINEVICTABLEIDLETIMEMILLIS=-1;
    public final static boolean LIFO=false;
    public final static int DBINDEX=0;
}
