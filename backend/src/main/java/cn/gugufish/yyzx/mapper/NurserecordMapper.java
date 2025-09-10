package cn.gugufish.yyzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.gugufish.yyzx.pojo.Nurserecord;
import cn.gugufish.yyzx.vo.NurseRecordsVo;
import org.apache.ibatis.annotations.Param;

public interface NurserecordMapper extends BaseMapper<Nurserecord> {
    Page<NurseRecordsVo> selectNurseRecordsVo(@Param("page") Page<NurseRecordsVo> page, @Param("customerId") Integer customerId);
}
