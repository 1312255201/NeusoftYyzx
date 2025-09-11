package cn.gugufish.yyzx.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "BackdownVo对象", description = "")
public class BackdownVo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Integer id;

    @Schema(description = "备注")
    private String remarks;

    @Schema(description = "逻辑删除标记（0：显示；1：隐藏）")
    private Integer isDeleted;

    @Schema(description = "客户ID")
    private Integer customerId;

    @Schema(description = "退住时间")
    private Date retreatTime;

    @Schema(description = "退住类型 0-正常退住 1-死亡退住 2-保留床位")
    private Integer retreatType;

    @Schema(description = "退住原因")
    private String retreatReason;

    @Schema(description = "审批状态  0-已提交 1-同意  2-拒绝")
    private Integer auditStatus;

    @Schema(description = "审批人")
    private String auditPerson;

    @Schema(description = "审批时间")
    private Date auditTime;

    @Schema(description = "床位")
    private String bedDetails;

    @Schema(description = "入住时间")
    private Date checkinDate;

    @Schema(description = "床位")
    private Integer bedId;

    @Schema(description = "客户名称")
    private String customerName;
}
