package cn.gugufish.yyzx.pojo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Schema(name = "NurseRecordDTO-客户护理记录查询条件", description = "NurseRecordDTO")
public class NurseRecordDTO {
    @Schema(description = "客户编号")
    private Integer customerId;

    @Schema(description = "页码")
    private Integer pageSize;
}