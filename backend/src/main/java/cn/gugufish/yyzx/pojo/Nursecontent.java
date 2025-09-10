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
@Schema(name = "Nursecontent对象", description = "Nursecontent实体类")
public class Nursecontent implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "编号")
    private String serialNumber;

    @Schema(description = "名称")
    private String nursingName;

    @Schema(description = "价格")
    private String servicePrice;

    @Schema(description = "描述")
    private String message;

    @Schema(description = "状态1:启用;2:停用")
    private Integer status;

    @Schema(description = "执行周期")
    private String executionCycle;

    @Schema(description = "执行次数")
    private String executionTimes;

    @Schema(description = "逻辑册除标记（0:显示;1:隐藏）")
    private Integer isDeleted;
}
