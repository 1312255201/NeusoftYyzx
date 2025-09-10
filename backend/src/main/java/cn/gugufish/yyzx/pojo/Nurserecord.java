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
@Schema(name = "Nurserecord对象", description = "Nurserecord实体类")
public class Nurserecord implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "逻辑删除标记（0:显示；1：隐藏）")
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
}
