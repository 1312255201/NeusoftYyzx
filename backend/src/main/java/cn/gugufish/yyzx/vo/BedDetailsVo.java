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
@Schema(name = "BedDetailsVo对象", description = "")
public class BedDetailsVo {
    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "逻辑删除标记（0：显示；1：隐藏）")
    private Integer isDeleted;

    @Schema(description = "床位起始日期")
    private Date startDate;

    @Schema(description = "床位结束日期")
    private Date endDate;

    @Schema(description = "床位详情信息")
    private String bedDetails;

    @Schema(description = "顾客ID")
    private Integer customerId;

    @Schema(description = "床位ID")
    private Integer bedId;

    @Schema(description = "客户姓名")
    private String customerName;

    @Schema(description = "性别  0：男  1：女")
    private Integer customerSex;

    @Schema(description = "房间号")
    private String roomNo;

}
