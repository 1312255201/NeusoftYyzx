package cn.gugufish.yyzx.handler;

import cn.gugufish.yyzx.utils.ResultVo;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SignatureException.class)
    @ResponseBody
    public ResultVo<String> handleSignatureException(SignatureException e) {
        log.error("token的非法操作" + e.getMessage());
        return ResultVo.fail("token的非法操作，请自重", "token_error");
    }

    @ExceptionHandler(MalformedJwtException.class)
    @ResponseBody
    public ResultVo<String> handleMalformedJwtException(MalformedJwtException e) {
        log.error("token解析异常" + e.getMessage());
        return ResultVo.fail("token解析异常", "token_error");
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseBody
    public ResultVo<String> handleExpiredJwtException(ExpiredJwtException e) {
        return ResultVo.fail("登录超时，请重新登录", "token_error");
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ResultVo<String> handleNullPointerException(NullPointerException e) {
        log.error("发生空指针异常" + e);
        return ResultVo.fail("系统内部错误: 空指针异常", "system_error");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public  ResultVo<String>  handleException(Exception e) {
        log.error("系统异常", e);
        String message = e.getMessage();
        if (message != null && message.contains("token")) {
            return ResultVo.fail(message, "token_error");
        }
        return ResultVo.fail(message != null ? message : "系统发生未知错误");
    }
}