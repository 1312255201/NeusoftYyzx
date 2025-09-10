package cn.gugufish.yyzx.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "UserDTO-用户查询条件", description = "UserDTO")
public class UserDTO {
    @Schema(description = "系统角色 1:管理员;2:健康管家")
    private Integer roleId;

    @Schema(description = "页码")
    private Integer pageNum;

    @Schema(description = "每页信息数")
    private Integer pageSize;

    @Schema(description = "用户真实姓名")
    private String nickname;
}