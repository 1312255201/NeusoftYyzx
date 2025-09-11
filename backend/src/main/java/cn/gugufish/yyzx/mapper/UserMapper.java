package cn.gugufish.yyzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.mapper.Mapper;
import cn.gugufish.yyzx.pojo.User;
@org.apache.ibatis.annotations.Mapper

public interface UserMapper extends Mapper<User>, BaseMapper<User> {
}
