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
@Schema(name = "Bed对象", description = "Bed实体类")
public class Bed implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "房间编号")
    private Integer roomNo;

    @Schema(description = "房间状态 1：空闲 2:有人 3:外出")
    private Integer bedStatus;

    @Schema(description = "备注")
    private String remarks;

    @Schema(description = "床位编号")
    private String bedNo;
}
