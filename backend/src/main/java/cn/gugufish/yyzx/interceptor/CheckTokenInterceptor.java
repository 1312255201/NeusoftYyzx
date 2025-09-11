package cn.gugufish.yyzx.interceptor;

import cn.gugufish.yyzx.utils.Const;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.crypto.SecretKey;

@Slf4j
@Component
public class CheckTokenInterceptor implements HandlerInterceptor {
    
    @Resource
    private SecretKey jwtSecretKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //放行预检请求
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        //关于图片的静态资源的访问
        if (request.getRequestURI().contains("/images")) {
            return true;
        }
        if (request.getRequestURI().contains("/swagger")) {
            return true;
        }
        //获取token
        String token = request.getHeader("token");
        //检验token
        if (token == null) {
            throw new Exception("token为空，请登录！！！");
        } else {
            //校验token的有效性（正确性，时效性）
            JwtParser parser = Jwts.parserBuilder().setSigningKey(jwtSecretKey).build();
            //如果token正确，则正常执行，否则抛出异常
            Jws<Claims> claimsJws = parser.parseClaimsJws(token);
            log.info(claimsJws.getBody().get("userId").toString());
            request.setAttribute(Const.ATTR_USER_ID, claimsJws.getBody().get("userId"));
            return true;
        }
    }
}
