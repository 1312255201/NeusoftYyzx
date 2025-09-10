package cn.gugufish.yyzx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "Meal对象", description = "Meal实体类")
public class Meal implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
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
}
