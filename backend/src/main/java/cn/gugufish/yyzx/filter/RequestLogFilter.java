package cn.gugufish.yyzx.filter;

import cn.gugufish.yyzx.pojo.User;
import cn.gugufish.yyzx.service.UserService;
import cn.gugufish.yyzx.utils.Const;
import cn.gugufish.yyzx.utils.IpUtils;
import cn.gugufish.yyzx.utils.SnowflakeIdGenerator;
import com.alibaba.fastjson2.JSONObject;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Set;

/**
 * 请求日志过滤器
 * 用于记录和监控系统中所有HTTP请求的详细信息，提供完整的访问审计功能
 * 
 * 主要功能：
 * - 请求信息记录：记录请求URL、方法、参数、IP地址等基础信息
 * - 用户身份追踪：记录已认证用户的用户名、ID、角色等身份信息
 * - 响应结果监控：记录响应状态、内容和处理耗时
 * - 唯一请求标识：为每个请求生成唯一ID，便于问题追踪
 * - 智能过滤：忽略静态资源和API文档等无需记录的请求
 * @author GuguFish
 */
@Slf4j
@Order(2)
@WebFilter(urlPatterns = "/*", filterName = "requestLogFilter")
public class RequestLogFilter extends OncePerRequestFilter {

    /**
     * 雪花算法ID生成器
     * 用于为每个请求生成唯一的追踪ID，便于日志关联和问题排查
     */
    @Resource
    SnowflakeIdGenerator generator;

    /**
     * 需要忽略日志记录的URL前缀集合
     * 包含静态资源、API文档等不需要记录访问日志的路径
     * - /swagger-ui: Swagger UI界面资源
     * - /v3/api-docs: OpenAPI文档接口
     * - /images: 图片静态资源
     */
    private final Set<String> ignores = Set.of("/swagger-ui", "/v3/api-docs" , "/images");

    /**
     * 核心过滤方法
     * 对每个HTTP请求进行日志记录处理，包括请求开始和结束的完整生命周期
     * 
     * @param request HTTP请求对象
     * @param response HTTP响应对象
     * @param filterChain 过滤器链，用于继续执行后续过滤器
     * @throws ServletException Servlet处理异常
     * @throws IOException IO操作异常
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 检查是否为需要忽略的URL
        if(this.isIgnoreUrl(request.getServletPath())) {
            filterChain.doFilter(request, response);
        } else {
            long startTime = System.currentTimeMillis();
            
            ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
            ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
            
            try {
                // 执行过滤器链
                filterChain.doFilter(requestWrapper, responseWrapper);
            } finally {
                // 记录请求信息（在过滤器链执行后，此时可以读取到完整的请求体数据）
                this.logRequest(requestWrapper, responseWrapper, startTime);
                
                // 确保响应内容被写回到原始响应中
                responseWrapper.copyBodyToResponse();
            }
        }
    }

    /**
     * 判断当前请求URL是否需要忽略日志记录
     * 用于过滤掉静态资源、API文档等不需要记录的请求
     * 
     * @param url 请求路径
     * @return true表示需要忽略，false表示需要记录
     */
    private boolean isIgnoreUrl(String url){
        for (String ignore : ignores) {
            if(url.startsWith(ignore)) return true;
        }
        return false;
    }

    /**
     * 记录完整的请求信息
     * 包含请求的详细信息和响应结果，在过滤器链执行后调用以确保能读取到完整的请求体数据
     * 
     * @param requestWrapper 请求包装器
     * @param responseWrapper 响应包装器
     * @param startTime 请求开始时间戳
     */
    public void logRequest(ContentCachingRequestWrapper requestWrapper, ContentCachingResponseWrapper responseWrapper, long startTime){
        // 生成唯一请求ID并存入MDC上下文
        long reqId = generator.nextId();
        MDC.put("reqId", String.valueOf(reqId));
        
        // 计算请求处理耗时
        long time = System.currentTimeMillis() - startTime;
        int status = responseWrapper.getStatus();
        
        // 提取并格式化请求参数
        JSONObject params = new JSONObject();
        
        // 获取URL查询参数
        requestWrapper.getParameterMap().forEach((k, v) -> params.put(k, v.length > 0 ? v[0] : null));
        
        // 获取POST请求体数据
        String requestBody = this.getRequestBody(requestWrapper);
        if (requestBody != null && !requestBody.trim().isEmpty()) {
            params.put("requestBody", requestBody);
        }
        
        Integer userId = (Integer)requestWrapper.getAttribute(Const.ATTR_USER_ID);
        
        // 构建完整日志信息
        StringBuilder logBuilder = new StringBuilder();
        logBuilder.append("请求处理 | ");
        logBuilder.append("URL: \"").append(requestWrapper.getServletPath()).append("\" (").append(requestWrapper.getMethod()).append(") | ");
        logBuilder.append("IP: ").append(IpUtils.getRealClientIp(requestWrapper)).append(" | ");
        
        if(userId != null) {
            logBuilder.append("用户ID: ").append(userId).append(" | ");
        } else {
            logBuilder.append("身份: 未验证 | ");
        }
        
        logBuilder.append("参数: ").append(params).append(" | ");
        logBuilder.append("耗时: ").append(time).append("ms | ");
        logBuilder.append("状态码: ").append(status);
        
        // 根据响应状态决定日志级别
        if(status != 200) {
            logBuilder.append(" | 响应: 错误状态");
            log.warn(logBuilder.toString());
        } else {
            String content = new String(responseWrapper.getContentAsByteArray());
            logBuilder.append(" | 响应: ").append(content);
            log.info(logBuilder.toString());
        }
        
        // 清理MDC上下文
        MDC.clear();
        //TODO : 整体代码修复添加新IP工具类， 用来适配部署服务器后Nginx反代导致IP错误的问题，本工具类没有测试临时添加TODO
    }
    
    /**
     * 获取POST请求体内容
     * 从ContentCachingRequestWrapper中读取缓存的请求体数据
     * 
     * @param request 请求包装器
     * @return 请求体字符串，如果无内容则返回null
     */
    private String getRequestBody(ContentCachingRequestWrapper request) {
        byte[] content = request.getContentAsByteArray();
        if (content.length > 0) {
            return new String(content, StandardCharsets.UTF_8);
        }
        return null;
    }
}
