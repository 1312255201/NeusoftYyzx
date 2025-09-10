package cn.gugufish.yyzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.gugufish.yyzx.pojo.Customerpreference;
import cn.gugufish.yyzx.vo.CustomerPreferenceVo;
import org.apache.ibatis.annotations.Param;

public interface CustomerpreferenceMapper extends BaseMapper<Customerpreference> {
    Page<CustomerPreferenceVo> selectCustomerPreferenceVo(@Param("page") Page<CustomerPreferenceVo> page, @Param("customer_name") String customer_name) throws Exception;
}
