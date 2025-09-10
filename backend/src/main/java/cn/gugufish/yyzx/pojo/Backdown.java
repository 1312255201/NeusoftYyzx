package cn.gugufish.yyzx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "Backdown对象", description = "Backdown实体类")
public class Backdown implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "备注")
    private String remarks;

    @Schema(description = "逻辑删除标记（0:显示;1:隐藏）")
    private Integer isDeleted;

    @Schema(description = "客户ID")
    private Integer customerId;

    @Schema(description = "退住时间")
    private Date retreattime;

    @Schema(description = "退住类型 0-正常退住 1-死亡退住 2-保留床位")
    private Integer retreattype;

    @Schema(description = "退住原因")
    private String retreatreason;

    @Schema(description = "审批状态 0-已提交 1-同意 2-拒绝")
    private Integer auditstatus;

    @Schema(description = "审批人")
    private String auditperson;

    @Schema(description = "审批时间")
    private Date audittime;
}
