package cn.gugufish.yyzx.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "BedDetailsDTO-床位管理查询条件", description = "BedDetailsDTO")
public class BedDetailsDTO {
    @Schema(description = "客户姓名")
    private String customerName;

    @Schema(description = "页码")
    private Integer pageSize;

    @Schema(description = "查询类型 0-生效床位信息 1-失效床位信息（历史记录）")
    private Integer isDeleted;

    @Schema(description = "<UNK>")
    private Date startDate;

    @Schema(description = "客户姓名")
    private Date endDate;
}
