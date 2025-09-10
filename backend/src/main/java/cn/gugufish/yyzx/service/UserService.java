package cn.gugufish.yyzx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.gugufish.yyzx.pojo.User;
import cn.gugufish.yyzx.utils.ResultVo;

public interface UserService extends IService<User> {
    public ResultVo<User> login(String username, String password) throws Exception;
}