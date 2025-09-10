package cn.gugufish.yyzx.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "CustomerNurseItemDTO-顾客护理项目查询条件", description = "CustomerNurseItemDTO")
public class CustomerNurseItemDTO {
    @Schema(description = "客户编号")
    private Integer customerId;

    @Schema(description = "页码")
    private Integer pageSize;
}
