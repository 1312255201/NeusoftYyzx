package cn.gugufish.yyzx.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerNurseItemDTO {
    
    @Schema(description = "客户编号")
    private Integer customerId;
    
    @Schema(description = "页码")
    private Integer pageNum;

    @Schema(description = "页面大小")
    private Integer pageSize;
}
