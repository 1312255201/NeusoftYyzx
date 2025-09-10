package cn.gugufish.yyzx.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "CustomerPreferenceDTO-顾客喜好查询条件", description = "CustomerPreferenceDTO")
public class CustomerPreferenceDTO {
    @Schema(description = "喜好编号")
    private Integer customerPreferenceId;

    @Schema(description = "顾客姓名")
    private String customerName;

    @Schema(description = "页码")
    private Integer pageSize;
}