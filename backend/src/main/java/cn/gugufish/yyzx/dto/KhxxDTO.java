package cn.gugufish.yyzx.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "KhxxDTO-客户信息查询条件", description = "KhxxDTO")
public class KhxxDTO {
    @Schema(description = "客户姓名")
    private String customerName;

    @Schema(description = "页码")
    private Integer pageNum;

    @Schema(description = "每页大小")
    private Integer pageSize;

    @Schema(description = "老人类型 1-自理老人 2-护理老人 3-无管家")
    private Integer manType;//老人类型 1-自理老人 2-护理老人 3-无管家

    @Schema(description = "系统健康管家（护工)")
    private Integer userId;

}