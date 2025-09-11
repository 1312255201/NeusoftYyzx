package cn.gugufish.yyzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.gugufish.yyzx.pojo.Outward;
import cn.gugufish.yyzx.vo.OutwardVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OutwardMapper extends BaseMapper<Outward> {
    
    @Select("<script>" +
            "SELECT DISTINCT " +
            "u.nickname, " +
            "u.phone_number, " +
            "ow.id, " +
            "ow.remarks, " +
            "ow.is_deleted, " +
            "ow.customer_id, " +
            "ow.outgoingreason, " +
            "ow.outgoingtime, " +
            "ow.expectedreturntime, " +
            "ow.actualreturntime, " +
            "ow.escorted, " +
            "ow.relation, " +
            "ow.escortedtel, " +
            "ow.auditstatus, " +
            "ow.auditperson, " +
            "ow.audittime, " +
            "cs.customer_name " +
            "FROM " +
            "outward ow, " +
            "`user` u, " +
            "customer cs " +
            "WHERE " +
            "cs.user_id = u.id " +
            "AND ow.customer_id = cs.id " +
            "AND ow.is_deleted = 0 " +
            "<if test='userId!=null and userId!=\"\"'>" +
            "and cs.user_id=#{userId}" +
            "</if>" +
            "</script>")
    Page<OutwardVo> selectOutwardVo(@Param("page") Page<OutwardVo> page, @Param("userId") Integer userId) throws Exception;
}