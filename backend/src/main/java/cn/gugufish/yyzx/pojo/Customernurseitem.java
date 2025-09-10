package cn.gugufish.yyzx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "Customernurseitem对象", description = "Customernurseitem实体类")
public class Customernurseitem implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "护理项目编号")
    private Integer itemId;

    @Schema(description = "客户编号")
    private Integer customerId;

    @Schema(description = "护理级别编号")
    private Integer levelId;

    @Schema(description = "购买数量")
    private Integer nurseNumber;

    @Schema(description = "逻辑删除标记（0：显示；1：隐藏）")
    private Integer isDeleted;

    @Schema(description = "服务购买日期")
    private Date buyTime;

    @Schema(description = "服务到期曰")
    private Date maturityTime;
}
