package cn.gugufish.yyzx.config;

import cn.gugufish.yyzx.utils.DateConverter;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Resource
    private DateConverter dateConverter;

    /**
     * 配置数据格式化规则
     *
     * 用于注册自定义的日期、数字等类型的格式化处理器，
     * 解决请求参数和响应结果的格式转换问题
     *
     * @param registry 格式化注册器，用于注册自定义格式化器
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(dateConverter);
        WebMvcConfigurer.super.addFormatters(registry);
    }
}