package cn.gugufish.yyzx.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "NurseItemDTO-护理项目查询条件", description = "NurseItemDTO")
public class NurseItemDTO {
    @Schema(description = "状态 1:启用;2:停用")
    private Integer status;

    @Schema(description = "页码")
    private Integer pageNo;

    @Schema(description = "页码")
    private Integer pageSize;

    @Schema(description = "名称")
    private String itemName;
}
