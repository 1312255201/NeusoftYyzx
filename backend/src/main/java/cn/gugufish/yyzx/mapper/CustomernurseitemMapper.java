package cn.gugufish.yyzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.gugufish.yyzx.pojo.Customernurseitem;
import cn.gugufish.yyzx.pojo.vo.CustomerNurseItemVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CustomernurseitemMapper extends BaseMapper<Customernurseitem> {
    
    @Select("SELECT cni.id, " +
            "cni.item_id, " +
            "cni.customer_id, " +
            "cni.level_id, " +
            "cni.nurse_number, " +
            "cni.is_deleted, " +
            "cni.buy_time, " +
            "cni.maturity_time, " +
            "c.customer_name, " +
            "nc.serial_number, " +
            "nc.nursing_name, " +
            "nc.service_price " +
            "FROM customernurseitem cni " +
            "JOIN customer c ON (cni.customer_id = c.id) " +
            "JOIN nursecontent nc on (cni.item_id = nc.id) " +
            "WHERE cni.is_deleted = 0 " +
            "and cni.customer_id = #{customerId}")
    Page<CustomerNurseItemVo> selectCustomerItemVo(@Param("page") Page<CustomerNurseItemVo> page, @Param("customerId") Integer customerId) throws Exception;
}
