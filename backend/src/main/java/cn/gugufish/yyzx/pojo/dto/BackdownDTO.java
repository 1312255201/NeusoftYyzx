package cn.gugufish.yyzx.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "BackdownDTO-退住查询条件", description = "BackdownDTO")
public class BackdownDTO {
    @Schema(description = "用户编号")
    private Integer userId;

    @Schema(description = "客户ID")
    private Integer customerId;

    @Schema(description = "当前页码")
    private Integer pageNum;

    @Schema(description = "每页显示条数")
    private Integer pageSize;
}