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
@Schema(name = "NurseRecordsVo对象", description = "")
public class NurseRecordsVo {
    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "逻辑删除标记（0：显示；1：隐藏）")
    private Integer isDeleted;

    @Schema(description = "客户ID")
    private Integer customerId;

    @Schema(description = "护理项目ID")
    private Integer itemId;

    @Schema(description = "护理时间")
    private Date nursingTime;

    @Schema(description = "护理内容")
    private String nursingContent;

    @Schema(description = "护理数量")
    private Integer nursingCount;

    @Schema(description = "护理人员ID")
    private Integer userId;

    @Schema(description = "护理人姓名")
    private String nickname;

    @Schema(description = "护理人电话号码")
    private String phoneNumber;

    @Schema(description = "护理项目编号")
    private String serialNumber;

    @Schema(description = "护理项目名称")
    private String nursingName;
}
