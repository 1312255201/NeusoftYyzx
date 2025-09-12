package cn.gugufish.yyzx.config;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MinioConfiguration {
    @Value("${spring.minio.endpoint}")
    String endpoint;
    @Value("${spring.minio.username}")
    String username;
    @Value("${spring.minio.password}")
    String password;
    /**
     * 创建MinIO客户端Bean
     * 配置并初始化MinIO客户端，用于与MinIO服务器进行交互
     * 包括文件上传、下载、删除等操作
     *
     * @return 配置完成的MinioClient实例
     */
    @Bean
    public MinioClient minioClient(){
        log.info("正在初始化MinioClient，服务端点：{}", endpoint);
        return MinioClient.builder()
                // 设置MinIO服务器端点地址
                .endpoint(endpoint)
                // 设置访问凭据（用户名和密码）
                .credentials(username, password)
                .build();
    }
}
