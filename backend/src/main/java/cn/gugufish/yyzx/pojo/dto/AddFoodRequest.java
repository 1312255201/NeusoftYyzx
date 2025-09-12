package cn.gugufish.yyzx.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;

/**
 * 添加食物请求DTO
 * 用于封装添加食物时的请求参数
 */
@Data
@Schema(name = "AddFoodRequest", description = "添加食物请求参数")
public class AddFoodRequest {
    
    @Schema(description = "食品名称")
    @NotBlank(message = "食品名称不能为空")
    private String foodName;
    
    @Schema(description = "食品类型")
    @NotBlank(message = "食品类型不能为空")
    private String foodType;
    
    @Schema(description = "价格")
    @NotNull(message = "价格不能为空")
    @DecimalMin(value = "0.0", inclusive = false, message = "价格必须大于0")
    private Double price;
    
    @Schema(description = "是否清真（0：否，1：是）")
    @NotNull(message = "是否清真不能为空")
    private Integer isHalal;
}