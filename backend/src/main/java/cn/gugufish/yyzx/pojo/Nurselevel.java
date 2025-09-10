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
@Schema(name = "Nurselevel对象", description = "Nurselevel实体类")
public class Nurselevel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "护理级别")
    private String levelName;

    @Schema(description = "级别状态 1:启用;2:停用")
    private Integer levelStatus;

    @Schema(description = "逻辑册除标记（0:显示;1: 隐藏）")
    private Integer isDeleted;
}
