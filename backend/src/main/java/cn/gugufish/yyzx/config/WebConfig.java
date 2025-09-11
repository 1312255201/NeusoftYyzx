package cn.gugufish.yyzx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web 应用配置类，用于配置拦截器和数据格式化规则
 * 
 * 注意：JWT验证现在由JwtAuthenticationFilter过滤器处理，不再使用拦截器方式
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 配置拦截器注册
     *
     * <p>用于添加跨域拦截器或其他自定义拦截器，
     * 实现请求的预处理和后处理逻辑</p>
     * 
     * <p>注意：JWT验证现在由JwtAuthenticationFilter过滤器处理，
     * 不再在此处配置JWT相关拦截器</p>
     *
     * @param registry 拦截器注册器，用于注册自定义拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // JWT验证现在由JwtAuthenticationFilter过滤器处理
        // 如需添加其他拦截器，可在此处配置
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