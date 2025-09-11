package cn.gugufish.yyzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.gugufish.yyzx.pojo.Backdown;
import cn.gugufish.yyzx.pojo.vo.BackdownVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BackdownMapper extends BaseMapper<Backdown> {
    
    @Select("<script>" +
            "SELECT DISTINCT " +
            "bdn.id, " +
            "bdn.remarks, " +
            "bdn.is_deleted, " +
            "bdn.customer_id, " +
            "bdn.retreattime, " +
            "bdn.retreattype, " +
            "bdn.retreatreason, " +
            "bdn.auditstatus, " +
            "bdn.auditperson, " +
            "bdn.audittime, " +
            "bdd.bed_details, " +
            "cs.checkin_date, " +
            "cs.bed_id, " +
            "cs.customer_name " +
            "FROM " +
            "backdown bdn, " +
            "user u, " +
            "beddetails bdd, " +
            "customer cs " +
            "WHERE " +
            "cs.user_id = u.id " +
            "AND cs.id = bdn.customer_id " +
            "AND bdn.customer_id = bdd.customer_id " +
            "AND bdn.is_deleted = 0 " +
            "<if test='customerId != null and customerId != \"\"'>" +
            "AND bdn.customer_id = #{customerId}" +
            "</if>" +
            "</script>")
    Page<BackdownVo> selectBackdownVo(@Param("page") Page<BackdownVo> page, @Param("customerId") Integer customerId);
}