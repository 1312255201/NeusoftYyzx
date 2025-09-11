package cn.gugufish.yyzx.filter;

import cn.gugufish.yyzx.utils.Const;
import cn.gugufish.yyzx.utils.RedisTokenBlacklistUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Set;

/**
 * JWT认证过滤器
 * 用于解析和验证JWT token，提取用户身份信息并设置到请求属性中
 * 
 * 主要功能：
 * - JWT token解析：从请求头中获取token并解析
 * - 用户身份提取：从token中提取用户ID等身份信息
 * - 请求属性设置：将用户信息设置到request属性中，供后续组件使用
 * - 静态资源放行：对静态资源和API文档等路径进行放行
 * 
 * 执行顺序：此过滤器应在RequestLogFilter之前执行，确保日志记录时能获取到用户信息
 * 
 * @author GuguFish
 */
@Slf4j
@Order(1)
@WebFilter(urlPatterns = "/*", filterName = "jwtAuthenticationFilter")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * JWT密钥，用于解析JWT token
     */
    @Resource
    private SecretKey jwtSecretKey;
    
    /**
     * Redis Token黑名单工具类
     */
    @Resource
    private RedisTokenBlacklistUtil redisTokenBlacklistUtil;

    /**
     * 需要忽略JWT验证的URL前缀集合
     * 包含静态资源、API文档、登录接口等不需要JWT验证的路径
     */
    private final Set<String> ignores = Set.of(
            "/swagger-ui", 
            "/v3/api-docs", 
            "/images",
            "/user/login",
            "/favicon.ico"
    );

    /**
     * 核心过滤方法
     * 对每个HTTP请求进行JWT token解析和用户身份验证
     * 
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     * @param filterChain 过滤器链，用于继续执行后续过滤器
     * @throws ServletException Servlet处理异常
     * @throws IOException IO操作异常
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 放行预检请求
        if ("OPTIONS".equals(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        // 检查是否为需要忽略JWT验证的URL
        if (this.isIgnoreUrl(request.getServletPath())) {
            filterChain.doFilter(request, response);
            return;
        }

        // 解析JWT token并设置用户信息
        boolean success = this.parseAndSetUserInfo(request);
        if (!success) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        // 继续执行后续过滤器
        filterChain.doFilter(request, response);
    }

    /**
     * 判断当前请求URL是否需要忽略JWT验证
     * 
     * @param url 请求路径
     * @return true表示需要忽略，false表示需要验证
     */
    private boolean isIgnoreUrl(String url) {
        for (String ignore : ignores) {
            if (url.startsWith(ignore)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解析JWT token并设置用户信息到request属性中
     * 这样后续的过滤器和控制器都能获取到用户身份信息
     * 
     * @param request HTTP请求对象
     */
    private boolean parseAndSetUserInfo(HttpServletRequest request) {
        try {
            // 获取token
            String token = request.getHeader("token");
            if (token != null && !token.isEmpty()) {
                // 检查token是否在黑名单中
                if (redisTokenBlacklistUtil.isInBlacklist(token)) {
                    log.debug("Token已在黑名单中，拒绝访问");
                    return false;
                }
                
                // 校验token的有效性
                JwtParser parser = Jwts.parserBuilder().setSigningKey(jwtSecretKey).build();
                Jws<Claims> claimsJws = parser.parseClaimsJws(token);
                
                // 从token中获取用户ID并设置到request属性中
                String userId = claimsJws.getBody().get("userId").toString();
                String username = claimsJws.getBody().get("userName").toString();
                
                if (userId != null) {
                    request.setAttribute(Const.ATTR_USER_ID, Integer.valueOf(userId));
                }
                if (username != null) {
                    request.setAttribute("username", username);
                }
                
                log.debug("JWT解析成功，用户ID: {}, 用户名: {}", userId, username);
                return true;
            }
        } catch (Exception e) {
            // JWT解析失败时记录调试日志，但不阻断请求处理
            log.debug("JWT token解析失败: {}", e.getMessage());
            return false;
        }
        return false;
    }
}