package cn.gugufish.yyzx.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "CustomerNurseItemVo对象", description = "")
public class CustomerNurseItemVo {
    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "护理项目编号")
    private Integer itemId;

    @Schema(description = "客户编号")
    private Integer customerId;

    @Schema(description = "购买数量")
    private Integer nurseNumber;

    @Schema(description = "逻辑删除标记（0：显示；1：隐藏）")
    private Integer isDeleted;

    @Schema(description = "服务购买日期")
    private Date buyTime;

    @Schema(description = "服务到期日")
    private Date maturityTime;

    @Schema(description = "客户姓名")
    private String customerName;

    @Schema(description = "编号")
    private String serialNumber;

    @Schema(description = "名称")
    private String nursingName;

    @Schema(description = "价格")
    private String servicePrice;

}
