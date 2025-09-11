package cn.gugufish.yyzx.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)

public class NurseItemDTO {
    private Integer pageNo;
    private Integer status;

    private Integer pageSize;

    @Schema(description = "名称")
    private String itemName;
}
