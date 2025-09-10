package cn.gugufish.yyzx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
//配置mapper的路径  mapper包扫描
@MapperScan(basePackages = "cn.gugufish.yyzx.mapper")
@ServletComponentScan // 启用对WebFilter、WebServlet等注解的支持
public class YyzxApplication {
    public static void main(String[] args) {
        SpringApplication.run(YyzxApplication.class, args);
    }
}
