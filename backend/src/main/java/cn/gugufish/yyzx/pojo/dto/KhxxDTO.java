package cn.gugufish.yyzx.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class KhxxDTO {

    @Schema(description = "客户姓名")
    private String customerName;

    @Schema(description = "管理类型")
    private Integer manType;

    @Schema(description = "用户ID")
    private Integer userId;

    @Schema(description = "页码")
    private Integer pageNum;

    @Schema(description = "页面大小")
    private Integer pageSize;
}