package cn.gugufish.yyzx.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Redis Token黑名单工具类
 * 用于管理JWT token的黑名单，实现用户退出登录功能
 * 
 * @author GuguFish
 */
@Slf4j
@Component
public class RedisTokenBlacklistUtil {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    
    @Resource
    private SecretKey jwtSecretKey;

    /**
     * 将token添加到黑名单
     * 
     * @param token JWT token
     * @return 是否添加成功
     */
    public boolean addToBlacklist(String token) {
        try {
            // 解析token获取过期时间
            JwtParser parser = Jwts.parserBuilder().setSigningKey(jwtSecretKey).build();
            Jws<Claims> claimsJws = parser.parseClaimsJws(token);
            Date expiration = claimsJws.getBody().getExpiration();
            
            // 计算剩余过期时间
            long currentTime = System.currentTimeMillis();
            long expirationTime = expiration.getTime();
            
            if (expirationTime <= currentTime) {
                // token已过期，无需添加到黑名单
                log.debug("Token已过期，无需添加到黑名单");
                return true;
            }
            
            // 计算剩余时间（秒）
            long remainingTime = (expirationTime - currentTime) / 1000;
            
            // 将token添加到Redis黑名单，设置过期时间为token的剩余有效时间
            String blacklistKey = Const.REDIS_TOKEN_BLACKLIST_PREFIX + token;
            stringRedisTemplate.opsForValue().set(blacklistKey, "1", remainingTime, TimeUnit.SECONDS);
            
            log.info("Token已添加到黑名单，剩余过期时间: {} 秒", remainingTime);
            return true;
            
        } catch (Exception e) {
            log.error("添加token到黑名单失败: {}", e.getMessage());
            return false;
        }
    }

    /**
     * 检查token是否在黑名单中
     * 
     * @param token JWT token
     * @return 是否在黑名单中
     */
    public boolean isInBlacklist(String token) {
        try {
            String blacklistKey = Const.REDIS_TOKEN_BLACKLIST_PREFIX + token;
            return Boolean.TRUE.equals(stringRedisTemplate.hasKey(blacklistKey));
        } catch (Exception e) {
            log.error("检查token黑名单状态失败: {}", e.getMessage());
            return false;
        }
    }

    /**
     * 从黑名单中移除token（一般不需要主动调用，Redis会自动过期）
     * 
     * @param token JWT token
     * @return 是否移除成功
     */
    public boolean removeFromBlacklist(String token) {
        try {
            String blacklistKey = Const.REDIS_TOKEN_BLACKLIST_PREFIX + token;
            return Boolean.TRUE.equals(stringRedisTemplate.delete(blacklistKey));
        } catch (Exception e) {
            log.error("从黑名单移除token失败: {}", e.getMessage());
            return false;
        }
    }
}