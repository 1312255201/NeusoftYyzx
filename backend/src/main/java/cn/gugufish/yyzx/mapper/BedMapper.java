package cn.gugufish.yyzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.gugufish.yyzx.pojo.Bed;
import cn.gugufish.yyzx.vo.CwsyBedVo;

public interface BedMapper extends BaseMapper<Bed> {
    CwsyBedVo selectBedCount();
}
