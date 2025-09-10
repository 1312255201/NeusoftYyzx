package cn.gugufish.yyzx.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * IP地址工具类
 * 提供获取客户端真实IP地址的工具方法
 * 支持各种代理环境下的IP获取
 * 
 * @author GuguFish
 */
@Slf4j
public class IpUtils {
    
    /**
     * 获取客户端真实IP地址
     * 按优先级顺序检查各种可能包含真实IP的请求头
     * 
     * @param request HTTP请求对象
     * @return 客户端的真实IP地址
     */
    public static String getRealClientIp(HttpServletRequest request) {
        String ip = null;
        
        // 1. 检查 X-Forwarded-For 头（最常用）
        ip = request.getHeader("X-Forwarded-For");
        if (isValidIp(ip)) {
            // X-Forwarded-For 可能包含多个IP，取第一个
            if (ip.contains(",")) {
                ip = ip.split(",")[0].trim();
            }
            return ip;
        }
        
        // 2. 检查 X-Real-IP 头（Nginx 常用）
        ip = request.getHeader("X-Real-IP");
        if (isValidIp(ip)) {
            return ip;
        }
        
        // 3. 检查 Proxy-Client-IP 头
        ip = request.getHeader("Proxy-Client-IP");
        if (isValidIp(ip)) {
            return ip;
        }
        
        // 4. 检查 WL-Proxy-Client-IP 头（WebLogic）
        ip = request.getHeader("WL-Proxy-Client-IP");
        if (isValidIp(ip)) {
            return ip;
        }
        
        // 5. 检查 HTTP_CLIENT_IP 头
        ip = request.getHeader("HTTP_CLIENT_IP");
        if (isValidIp(ip)) {
            return ip;
        }
        
        // 6. 检查 HTTP_X_FORWARDED_FOR 头
        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (isValidIp(ip)) {
            return ip;
        }
        
        // 7. 最后回退到直接获取（适用于没有代理的情况）
        ip = request.getRemoteAddr();
        
        log.debug("获取到的客户端IP: {}", ip);
        return ip;
    }
    
    /**
     * 验证IP地址是否有效
     * 检查IP是否为空、未知或本地地址
     * 
     * @param ip 待验证的IP地址
     * @return true表示IP有效，false表示无效
     */
    private static boolean isValidIp(String ip) {
        return ip != null 
                && !ip.isEmpty() 
                && !"unknown".equalsIgnoreCase(ip)
                && !"0:0:0:0:0:0:0:1".equals(ip)  // IPv6 本地地址
                && !"127.0.0.1".equals(ip);      // IPv4 本地地址
    }
} 