package cn.gugufish.yyzx.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "User对象", description = "User实体类")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "创建者")
    private Integer createBy;

    @Schema(description = "更新时间")
    private Date updateTime;

    @Schema(description = "更新者")
    private Integer updateBy;

    @Schema(description = "逻辑删除标记（0:显示;1:隐藏）")
    private Integer isDeleted;

    @Schema(description = "真实姓名")
    private String nickname;

    @Schema(description = "系统账号")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "性别（0：女 1，男）")
    private Integer sex;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "电话号码")
    private String phoneNumber;

    @Schema(description = "系统角色编号")
    private Integer roleId;

    @Schema(description = "系统角色编号")
    @TableField(exist = false)//表示该属性不为数据库字段信息
    private List<Menu> menuList;
}
