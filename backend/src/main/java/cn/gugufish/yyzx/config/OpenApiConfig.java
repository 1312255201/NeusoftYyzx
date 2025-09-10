package cn.gugufish.yyzx.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("《东软颐养中心》后端接口说明")
                        .description("《东软颐养中心》后端接口文档")
                        .version("v 0.0.1")
                        .contact(new Contact()
                                .name("咕咕鱼")
                                .email("1312255201@qq.com")));
    }
}