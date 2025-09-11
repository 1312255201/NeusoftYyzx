package cn.gugufish.yyzx.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

/**
 * JWT配置类，用于集中管理JWT相关配置
 */
@Configuration
public class JwtConfig {

    // 固定的JWT密钥字符串，确保应用重启后密钥保持一致
    private static final String JWT_SECRET = "mySecretKeyForJWTTokenGenerationAndValidation2025";

    /**
     * 创建一个固定的JWT密钥
     * 使用固定字符串生成密钥，确保应用重启后能够验证之前生成的JWT
     * 
     * @return JWT签名用的密钥
     */
    @Bean
    public SecretKey jwtSecretKey() {
        return Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
    }
}