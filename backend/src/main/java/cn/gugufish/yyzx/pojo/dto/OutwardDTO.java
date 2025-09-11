package cn.gugufish.yyzx.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "OutwardDTO-外出登记查询条件", description = "OutwardDTO")
public class OutwardDTO {
    @Schema(description = "用户编号")
    private Integer userId;

    @Schema(description = "每页信息数")
    private Integer pageSize;
}