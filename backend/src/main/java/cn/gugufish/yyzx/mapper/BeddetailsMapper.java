package cn.gugufish.yyzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.gugufish.yyzx.dto.BedDetailsDTO;
import cn.gugufish.yyzx.pojo.Beddetails;
import cn.gugufish.yyzx.vo.BedDetailsVo;
import org.apache.ibatis.annotations.Param;

public interface BeddetailsMapper extends BaseMapper<Beddetails> {
    Page<BedDetailsVo> selectBedDetailsVo(@Param("page") Page<BedDetailsVo> page, @Param("detailsDTO") BedDetailsDTO bedDetailsDTO);
}
