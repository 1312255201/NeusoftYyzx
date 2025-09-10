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
@Schema(name = "Food对象", description = "Food实体类")
public class Food implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

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
