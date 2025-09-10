package cn.gugufish.yyzx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "Customerpreference对象", description = "Customerpreference实体类")
public class Customerpreference implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "顾客ID")
    private Integer customerId;

    @Schema(description = "饮食喜好")
    private String preferences;

    @Schema(description = "注意事项")
    private String attention;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "逻辑删除标记（0:显示; 1：隐藏）")
    private Integer isDeleted;
}
