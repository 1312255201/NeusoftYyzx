package cn.gugufish.yyzx.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(description = "床位详情查询参数")
public class BedDetailsDTO {

    @Schema(description = "客户姓名")
    private String customerName;

    @Schema(description = "页码")
    private Integer pageSize;

    @Schema(description = "床位起始日期")
    private Date startDate;

    @Schema(description = "床位结束日期")
    private Date endDate;

    @Schema(description = "逻辑删除标记（0：显示；1：隐藏）")
    private Integer isDeleted;
}
