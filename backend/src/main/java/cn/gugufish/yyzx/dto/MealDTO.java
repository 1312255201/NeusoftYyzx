package cn.gugufish.yyzx.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "MealDTO - 食谱日历查询条件", description = "MealDTO")
public class MealDTO {
    @Schema(description = "食谱编号")
    private Integer mealId;

    @Schema(description = "星期")
    private String weekDay;

    @Schema(description = "页码")
    private Integer pageNum;

    @Schema(description = "每页信息数")
    private Integer pageSize;

    @Schema(description = "餐饮类型（早餐/午餐/晚餐）")
    private Integer mealType;
}