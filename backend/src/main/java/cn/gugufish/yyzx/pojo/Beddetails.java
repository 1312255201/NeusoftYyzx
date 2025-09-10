package cn.gugufish.yyzx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "Beddetails对象", description = "Beddetails实体类")
public class Beddetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "逻辑删除标记（0:显示;1：隐藏）")
    private Integer isDeleted;

    @Schema(description = "床位起始日期")
    private Date startDate;

    @Schema(description = "床位结束日期")
    private Date endDate;

    @Schema(description = "床位详情信息")
    private String bedDetails;

    @Schema(description = "顾客ID")
    private Integer customerId;

    @Schema(description = "床位ID")
    private Integer bedId;
}
