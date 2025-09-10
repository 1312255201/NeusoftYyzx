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
@Schema(name = "Customer对象", description = "Customer实体类")
public class Customer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "逻辑删除标记（θ:显示; 1：隐藏》")
    private Integer isDeleted;

    @Schema(description = "客户姓名")
    private String customerName;

    @Schema(description = "年龄")
    private Integer customerAge;

    @Schema(description = "性别 0:男 1:女")
    private Integer customerSex;

    @Schema(description = "身份证号")
    private String idcard;

    @Schema(description = "房间号")
    private String roomNo;

    @Schema(description = "所属楼房")
    private String buildingNo;

    @Schema(description = "入住时间")
    private Date checkinDate;

    @Schema(description = "合同到期时间")
    private Date expirationDate;

    @Schema(description = "联系电话")
    private String contactTel;

    @Schema(description = "床号")
    private Integer bedId;

    @Schema(description = "身心状况")
    private String psychosomaticState;

    @Schema(description = "注意事项")
    private String attention;

    @Schema(description = "出生曰期")
    private Date birthday;

    @Schema(description = "身高")
    private String height;

    @Schema(description = "体重")
    private String weight;

    @Schema(description = "血型")
    private String bloodType;

    @Schema(description = "头像路径")
    private String filepath;

    @Schema(description = "关联系统健康管家（护工)")
    private Integer userId;

    @Schema(description = "护理等级")
    private Integer levelId;

    @Schema(description = "家属")
    private String familyMember;
}
