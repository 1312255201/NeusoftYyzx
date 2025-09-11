package cn.gugufish.yyzx.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "OutwardDTO-外出登记查询条件", description = "OutwardDTO")
public class OutwardDTO {
    @Schema(description = "操作人编号")
    private Integer userId;
    @Schema(description = "用户编号")
    private Integer customerId;
    @Schema(description = "每页信息数")
    private Integer pageSize;
    @Schema(description = "当前页码")
    private Integer pageNum;
}