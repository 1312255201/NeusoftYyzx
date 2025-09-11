package cn.gugufish.yyzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.gugufish.yyzx.pojo.Nurserecord;
import cn.gugufish.yyzx.pojo.vo.NurseRecordsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface NurserecordMapper extends BaseMapper<Nurserecord> {
    
    @Select("SELECT nr.id, " +
            "nr.is_deleted, " +
            "nr.customer_id, " +
            "nr.item_id, " +
            "nr.nursing_time, " +
            "nr.nursing_content, " +
            "nr.nursing_count, " +
            "nr.user_id, " +
            "u.nickname, " +
            "u.phone_number, " +
            "nc.serial_number, " +
            "nc.nursing_name " +
            "FROM nurserecord nr " +
            "JOIN " +
            "user u ON nr.user_id = u.id " +
            "JOIN " +
            "nursecontent nc ON nr.item_id = nc.id " +
            "WHERE nr.is_deleted = 0 " +
            "AND nr.customer_id = #{customerId}")
    Page<NurseRecordsVo> selectNurseRecordsVo(@Param("page") Page<NurseRecordsVo> page, @Param("customerId") Integer customerId);
}
