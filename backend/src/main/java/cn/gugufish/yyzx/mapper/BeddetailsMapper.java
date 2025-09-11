package cn.gugufish.yyzx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.gugufish.yyzx.pojo.dto.BedDetailsDTO;
import cn.gugufish.yyzx.pojo.Beddetails;
import cn.gugufish.yyzx.pojo.vo.BedDetailsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BeddetailsMapper extends BaseMapper<Beddetails> {
    
    @Select("<script>" +
            "SELECT " +
            "b.id, " +
            "b.is_deleted, " +
            "b.start_date, " +
            "b.end_date, " +
            "b.bed_details, " +
            "b.customer_id, " +
            "b.bed_id, " +
            "c.customer_name, " +
            "c.customer_sex " +
            "FROM " +
            "beddetails b " +
            "LEFT JOIN customer c ON ( b.customer_id = c.id ) " +
            "<where>" +
            "<if test='detailsDTO !=null and detailsDTO.customerName!=null and detailsDTO.customerName!=\"\"'>" +
            "and c.customer_name like concat('%','${detailsDTO.customerName}','%')" +
            "</if>" +
            "<if test='detailsDTO!=null and detailsDTO.startDate!=null and detailsDTO.endDate!=null'>" +
            "and b.start_date>=#{detailsDTO.startDate} and #{detailsDTO.endDate}>=b.start_date" +
            "</if>" +
            " and b.is_deleted=#{detailsDTO.isDeleted}" +
            "</where>" +
            "order by b.end_date desc" +
            "</script>")
    Page<BedDetailsVo> selectBedDetailsVo(@Param("page") Page<BedDetailsVo> page, @Param("detailsDTO") BedDetailsDTO bedDetailsDTO);
}
