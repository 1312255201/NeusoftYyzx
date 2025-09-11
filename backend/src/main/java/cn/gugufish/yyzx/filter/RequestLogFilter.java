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
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
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
    private final Set<String> ignores = Set.of("/swagger-ui", "/v3/api-docs");

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
            // 记录请求开始时间，用于计算处理耗时
            long startTime = System.currentTimeMillis();
            // 记录请求开始信息
            this.logRequestStart(request);
            
            // 使用ContentCachingResponseWrapper包装响应，以便读取响应内容
            ContentCachingResponseWrapper wrapper = new ContentCachingResponseWrapper(response);
            filterChain.doFilter(request, wrapper);
            
            // 记录请求结束信息
            this.logRequestEnd(wrapper, startTime);
            // 将缓存的响应内容写回客户端
            wrapper.copyBodyToResponse();
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
     * 记录请求结束时的日志信息
     * 包含请求处理耗时、响应状态码和响应内容
     * 
     * @param wrapper 响应包装器，用于读取响应内容
     * @param startTime 请求开始时间戳
     */
    public void logRequestEnd(ContentCachingResponseWrapper wrapper, long startTime){
        // 计算请求处理耗时
        long time = System.currentTimeMillis() - startTime;
        int status = wrapper.getStatus();
        
        // 根据响应状态决定记录内容
        String content = status != 200 ? 
                status + " 错误" : new String(wrapper.getContentAsByteArray());

        log.info("请求处理耗时: {}ms | 响应结果: {}", time, content);
    }

    /**
     * 记录请求开始时的详细日志信息
     * 包含请求的完整信息：URL、方法、IP地址、用户身份、角色权限、请求参数等
     * 
     * 功能特点：
     * - 生成唯一请求ID并存入MDC，实现请求链路追踪
     * - 提取并格式化请求参数（取数组第一个值）
     * - 区分已认证和未认证用户，记录不同级别的身份信息
     * - 使用IpUtils获取真实客户端IP（支持反向代理）
     * 
     * @param request HTTP请求对象
     */
    public void logRequestStart(HttpServletRequest request){
        // 生成唯一请求ID并存入MDC上下文
        long reqId = generator.nextId();
        MDC.put("reqId", String.valueOf(reqId));
        
        // 提取并格式化请求参数
        JSONObject object = new JSONObject();
        request.getParameterMap().forEach((k, v) -> object.put(k, v.length > 0 ? v[0] : null));
        Integer userId = (Integer)request.getAttribute(Const.ATTR_USER_ID);

        if(userId != null) {
            log.info("请求URL: \"{}\" ({}) | 远程IP地址: {} │ 用户ID: {} | 请求参数列表: {}",
                    request.getServletPath(), request.getMethod(), IpUtils.getRealClientIp(request),
                    userId, object);
        } else {
            // 未认证用户：记录基础请求信息
            log.info("请求URL: \"{}\" ({}) | 远程IP地址: {} │ 身份: 未验证 | 请求参数列表: {}",
                    request.getServletPath(), request.getMethod(), IpUtils.getRealClientIp(request), object);
        }
        //TODO : 整体代码修复添加新IP工具类， 用来适配部署服务器后Nginx反代导致IP错误的问题，本工具类没有测试临时添加TODO
    }
}
