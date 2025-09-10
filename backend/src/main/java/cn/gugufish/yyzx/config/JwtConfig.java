package cn.gugufish.yyzx.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

/**
 * JWT配置类，用于集中管理JWT相关配置
 */
@Configuration
public class JwtConfig {

    /**
     * 创建一个安全的JWT密钥
     * 使用Keys.secretKeyFor方法生成符合HS256算法要求的安全密钥（至少256位）
     * 该密钥在应用运行期间保持不变，应用重启后会重新生成
     * 
     * @return JWT签名用的密钥
     */
    @Bean
    public SecretKey jwtSecretKey() {
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }
}