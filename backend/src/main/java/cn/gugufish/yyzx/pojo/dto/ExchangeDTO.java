package cn.gugufish.yyzx.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "ExchangeDTO-床位调换参数", description = "ExchangeDTO")
public class ExchangeDTO {
    @Schema(description = "床位详情编号")
    private Integer id;

    @Schema(description = "客户编号")
    private Integer customerId;

    @Schema(description = "新楼号")
    private String buildingNo;

    @Schema(description = "新房间号")
    private String newRoomNo;

    @Schema(description = "新床位编号")
    private Integer newBedId;

    @Schema(description = "旧床位编号")
    private Integer oldBedId;

    @Schema(description = "床位使用结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "GMT+8")
    private Date endDate;
}