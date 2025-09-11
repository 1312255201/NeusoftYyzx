package cn.gugufish.yyzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.gugufish.yyzx.pojo.Customer;
import cn.gugufish.yyzx.pojo.vo.KhxxCustomerVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {
    
    @Select("<script>" +
            "SELECT " +
            "c.id, " +
            "c.is_deleted, " +
            "customer_name, " +
            "customer_age, " +
            "customer_sex, " +
            "idcard, " +
            "c.room_no, " +
            "building_no, " +
            "checkin_date, " +
            "expiration_date, " +
            "contact_tel, " +
            "c.bed_id, " +
            "psychosomatic_state, " +
            "attention, " +
            "birthday, " +
            "height, " +
            "weight, " +
            "blood_type, " +
            "filepath, " +
            "c.user_id, " +
            "c.level_id, " +
            "family_member, " +
            "n.level_name, " +
            "u.nickname, " +
            "b.bed_no " +
            "FROM " +
            "customer c " +
            "LEFT JOIN nurselevel n ON (c.level_id = n.id) " +
            "LEFT JOIN user u ON (c.user_id = u.id) " +
            "LEFT JOIN bed b ON (c.bed_id = b.id) " +
            "<where>" +
            "<if test='manType!=null and manType==1'>" +
            "and (select count(1) from customernurseitem cni where cni.customer_id = c.id and cni.is_deleted = 0) = 0 " +
            "</if>" +
            "<if test='manType!=null and manType==2'>" +
            "and (select count(1) from customernurseitem cni where cni.customer_id = c.id and cni.is_deleted = 0 ) > 0 " +
            "</if>" +
            "<if test='customerName!=null and customerName!=\"\"'>" +
            "and customer_name like concat('%', #{customerName}, '%')" +
            "</if>" +
            "<if test='manType!=null and manType==3'>" +
            "and user_id = -1 " +
            "</if>" +
            "<if test='userId!=null'>" +
            "and user_id =#{userId} " +
            "</if>" +
            "and c.is_deleted=0 " +
            "</where>" +
            "order by c.id desc" +
            "</script>")
    Page<KhxxCustomerVo> selectPageVo(@Param("page") Page<KhxxCustomerVo> page, 
                                     @Param("customerName") String customerName, 
                                     @Param("manType") Integer manType, 
                                     @Param("userId") Integer userId);
}
