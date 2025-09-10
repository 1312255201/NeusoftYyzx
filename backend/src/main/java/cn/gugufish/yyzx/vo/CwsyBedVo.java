package cn.gugufish.yyzx.vo;

import cn.gugufish.yyzx.pojo.Room;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "CwsyBedVo对象", description = "")
public class CwsyBedVo implements Serializable {
    @Schema(description = "总床位")
    private Integer zcw;

    @Schema(description = "空闲床位")
    private Integer kx;

    @Schema(description = "有人床位")
    private Integer yr;

    @Schema(description = "外出床位")
    private Integer wc;

    @Schema(description = "房间和床位列表")
    private List<Room> roomList;
}
