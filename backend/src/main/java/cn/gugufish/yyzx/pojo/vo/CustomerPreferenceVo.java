package cn.gugufish.yyzx.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "CustomerPreferenceVo对象", description = "")
public class CustomerPreferenceVo {
    @Schema(description = "主键")
    private Integer id;

    @Schema(description = "顾客ID")
    private Integer customerId;

    @Schema(description = "饮食喜好")
    private String preferences;

    @Schema(description = "注意事项")
    private String attention;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "逻辑删除标记（0：显示；1：隐藏）")
    private Integer isDeleted;

    @Schema(description = "顾客姓名")
    private String customerName;

    @Schema(description = "顾客年龄")
    private Integer customerAge;

    @Schema(description = "顾客性别")
    private Integer customerSex;

}
