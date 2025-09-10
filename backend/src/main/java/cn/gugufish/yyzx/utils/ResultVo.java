package cn.gugufish.yyzx.utils;

/**
 * 响应实体类封装，Rest风格
 * @param code 状态码
 * @param data 响应数据
 * @param message 其他消息
 * @param <T> 响应数据类型
 */
public record ResultVo<T> (boolean flag, T data, String message) {

    /**
     * 成功不添加data
     *
     * @return
     */
    public static <T> ResultVo<T> ok(String message){
        return new ResultVo<>(true, null, message);
    }
    /**
     * 成功添加data
     *
     * @return
     */
    public static <T> ResultVo<T> ok(T data){
        return new ResultVo<>(true, data, "");
    }
    /**
     * 成功添加data和message
     *
     * @return
     */
    public static <T> ResultVo<T> ok(T data,String message){
        return new ResultVo<>(true, data, message);
    }
    /**
     * 失败
     *
     * @param message
     * @return
     */
    public static <T> ResultVo<T> fail(String message){
        return new ResultVo<>(false, null, message);
    }
    /**
     * 失败返回状态数据
     *
     * @param message
     * @return
     */
    public static <T> ResultVo<T> fail(String message,T data){
        return new ResultVo<>(false, data, message);
    }

    /**
     * 异常
     *
     * @param e
     * @return
     */
    public static <T> ResultVo<T> error(Exception e){
        return new ResultVo<>(false, null, "系统异常:" + e.getMessage());
    }
}