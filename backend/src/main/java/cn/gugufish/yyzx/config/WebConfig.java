package cn.gugufish.yyzx.config;

import cn.gugufish.yyzx.interceptor.CheckTokenInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web 应用配置类，用于配置拦截器和数据格式化规则
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Resource
    private CheckTokenInterceptor checkTokenInterceptor;

    /**
     * 配置拦截器注册
     *
     * <p>用于添加跨域拦截器或其他自定义拦截器，
     * 实现请求的预处理和后处理逻辑</p>
     *
     * @param registry 拦截器注册器，用于注册自定义拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册JWT令牌验证拦截器
        registry.addInterceptor(checkTokenInterceptor)
                .addPathPatterns("/**") // 拦截所有请求
                .excludePathPatterns("/user/login") // 排除登录接口
                .excludePathPatterns("/images/**") // 排除静态资源
                .excludePathPatterns("/swagger-ui/**", "/v3/api-docs/**"); // 排除Swagger相关路径
    }
    /**
     * 配置数据格式化规则
     *
     * <p>用于注册自定义的日期、数字等类型的格式化处理器，
     * 解决请求参数和响应结果的格式转换问题</p>
     *
     * @param registry 格式化注册器，用于注册自定义格式化器
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        WebMvcConfigurer.super.addFormatters(registry);
    }
}