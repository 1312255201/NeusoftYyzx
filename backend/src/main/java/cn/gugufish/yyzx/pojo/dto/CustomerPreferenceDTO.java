package cn.gugufish.yyzx.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerPreferenceDTO {
    @Schema(description = "顾客偏好ID")
    private Integer customerPreferenceId;

    @Schema(description = "客户姓名")
    private String customerName;

    @Schema(description = "页码")
    private Integer pageNum;

    @Schema(description = "页面大小")
    private Integer pageSize;
}