package cn.gugufish.yyzx.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "MealVo对象", description = "")
public class MealVo {
    @Schema(description = "主键")
    private Integer id;

    @Schema(description = "星期几")
    private String weekDay;

    @Schema(description = "食品ID")
    private Integer foodId;

    @Schema(description = "食品类型（1.早餐、2.午餐、3.晚餐）")
    private Integer mealType;

    @Schema(description = "口味（多糖、多盐、少糖、少盐）")
    private String taste;

    @Schema(description = "逻辑删除标记（0：显示；1：隐藏）")
    private Integer isDeleted;

    @Schema(description = "食品名称")
    private String foodName;

    @Schema(description = "食品类型")
    private String foodType;

    @Schema(description = "价格")
    private Double price;

    @Schema(description = "是否清真")
    private Integer isHalal;

    @Schema(description = "图片路径")
    private String foodImg;
}
