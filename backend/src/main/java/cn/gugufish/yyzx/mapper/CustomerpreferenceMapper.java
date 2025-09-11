package cn.gugufish.yyzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.gugufish.yyzx.pojo.Customerpreference;
import cn.gugufish.yyzx.vo.CustomerPreferenceVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CustomerpreferenceMapper extends BaseMapper<Customerpreference> {
    
    @Select("<script>" +
            "SELECT " +
            "cp.id, " +
            "cp.customer_id, " +
            "c.customer_name, " +
            "c.customer_sex, " +
            "c.customer_age, " +
            "cp.preferences, " +
            "cp.attention, " +
            "cp.remark " +
            "FROM " +
            "customerpreference cp " +
            "JOIN " +
            "customer c " +
            "ON " +
            "cp.customer_id = c.id " +
            "<where>" +
            "cp.is_deleted = 0 " +
            "<if test='customer_name != null and customer_name != \"\"'>" +
            "AND c.customer_name = #{customer_name}" +
            "</if>" +
            "</where>" +
            "</script>")
    Page<CustomerPreferenceVo> selectCustomerPreferenceVo(@Param("page") Page<CustomerPreferenceVo> page, @Param("customer_name") String customer_name) throws Exception;
}
