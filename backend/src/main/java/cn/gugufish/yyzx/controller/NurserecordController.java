package cn.gugufish.yyzx.controller;

import cn.gugufish.yyzx.pojo.vo.OutwardVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.gugufish.yyzx.pojo.dto.NurseRecordDTO;
import cn.gugufish.yyzx.pojo.dto.OutwardDTO;
import cn.gugufish.yyzx.pojo.Nurserecord;
import cn.gugufish.yyzx.service.NurserecordService;
import cn.gugufish.yyzx.service.OutwardService;
import cn.gugufish.yyzx.utils.ResultVo;
import cn.gugufish.yyzx.pojo.vo.NurseRecordsVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nurserecord")
@CrossOrigin
@Tag(name = "护理记录管理")
public class NurserecordController {

    @Resource
    private NurserecordService nurserecordService;

    @Resource
    private OutwardService outwardService;

    @Operation(summary = "添加护理记录")
    @PostMapping("/addNurseRecord")
    public ResultVo<Void> addNurseRecord(@RequestBody Nurserecord nurserecord) throws Exception {
        return nurserecordService.addNurseRecord(nurserecord);
    }

    @Operation(summary = "客户护理记录信息动态查询（分页）")
    @GetMapping("/listNurseRecordsVo")
    public ResultVo<Page<NurseRecordsVo>> listNurseRecordsVo(NurseRecordDTO nurseRecordDTO) throws Exception {
        return nurserecordService.queryNurseRecordsVo(nurseRecordDTO);
    }

    @Operation(summary = "软删除护理记录")
    @GetMapping("/softDeleteCustomerRecord")
    public ResultVo<Void> softDeleteCustomerRecord(@RequestParam Integer id) {
        Nurserecord nurseRecord = new Nurserecord();
        nurseRecord.setIsDeleted(1);
        nurseRecord.setId(id);
        nurserecordService.updateById(nurseRecord);
        return ResultVo.ok("移除成功");
    }

    @Operation(summary = "查询外出记录")
    @PostMapping("/queryOutwardVo")
    public ResultVo<Page<OutwardVo>> queryOutwardVo(@RequestBody OutwardDTO outwardDTO) throws Exception {
        return outwardService.queryOutwardVo(outwardDTO);
    }
}
