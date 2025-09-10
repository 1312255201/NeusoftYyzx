package cn.gugufish.yyzx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.gugufish.yyzx.pojo.Bed;
import cn.gugufish.yyzx.service.BedService;
import cn.gugufish.yyzx.utils.ResultVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/bed")
@Tag(name = "床位管理")
@CrossOrigin
public class BedController {

    @Resource
    BedService bedService;

    @GetMapping("/findBed")
    @Operation(summary = "查询床位信息")
    public ResultVo<List<Bed>> findBed(Bed bed) {
        QueryWrapper<Bed> qw = new QueryWrapper<>();
        if (bed.getRoomNo() != null) {
            qw.eq("room_no", bed.getRoomNo());
        }
        if (bed.getBedStatus() != null) {
            qw.eq("bed_status", bed.getBedStatus());
        }
        return ResultVo.ok(bedService.list(qw));
    }
}
