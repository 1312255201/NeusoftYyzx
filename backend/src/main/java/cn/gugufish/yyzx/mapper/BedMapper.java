package cn.gugufish.yyzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.gugufish.yyzx.pojo.Bed;
import cn.gugufish.yyzx.vo.CwsyBedVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BedMapper extends BaseMapper<Bed> {
    
    @Select("SELECT " +
            "(SELECT COUNT(*) FROM bed) AS zcw, " +
            "(SELECT COUNT(*) FROM bed WHERE bed_status = 1) AS kx, " +
            "(SELECT COUNT(*) FROM bed WHERE bed_status = 2) AS yr, " +
            "(SELECT COUNT(*) FROM bed WHERE bed_status = 3) AS wc")
    CwsyBedVo selectBedCount();
}
