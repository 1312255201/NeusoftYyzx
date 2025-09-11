package cn.gugufish.yyzx.utils;

public final class Const {
    public final static int ORDER_CORS = -102;
    public final static String ATTR_USER_ID = "userId";
    
    // Redis黑名单相关常量
    public final static String REDIS_TOKEN_BLACKLIST_PREFIX = "token:blacklist:";
    public final static long JWT_EXPIRATION_TIME = 24 * 60 * 60 * 1000; // 24小时，与JWT过期时间保持一致
}