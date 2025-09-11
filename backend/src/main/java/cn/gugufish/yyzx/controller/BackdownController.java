package cn.gugufish.yyzx.controller;

import cn.gugufish.yyzx.pojo.vo.BackdownVo;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import cn.gugufish.yyzx.pojo.dto.BackdownDTO;
import cn.gugufish.yyzx.pojo.Backdown;
import cn.gugufish.yyzx.pojo.Bed;
import cn.gugufish.yyzx.pojo.Customer;
import cn.gugufish.yyzx.service.BackdownService;
import cn.gugufish.yyzx.service.BedService;
import cn.gugufish.yyzx.service.CustomerService;
import cn.gugufish.yyzx.utils.ResultVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/backdown")
@CrossOrigin
@Tag(name = "退住管理")
public class BackdownController {
    @Resource
    BackdownService backdownService;

    @Resource
    BedService bedService;

    @Resource
    CustomerService customerService;

    @Operation(summary = "查询退住信息")
    @PostMapping("/listBackdown")
    public ResultVo<Page<BackdownVo>> listBackdown(@RequestBody BackdownDTO backdownDTO) throws Exception {
        return backdownService.listBackdownVo(backdownDTO);
    }

    @Operation(summary = "添加退住审批")
    @PostMapping("/addBackdown")
    public ResultVo<Void> addBackdown(@RequestBody Backdown backdown) throws Exception {
        backdownService.save(backdown);
        return ResultVo.ok("添加成功");
    }

    @Operation(summary = "审批退住")
    @PostMapping("/examineBackdown")
    public ResultVo<Void> examineBackdown(@RequestBody Backdown backdown) throws Exception {
        Backdown bd = backdownService.getById(backdown.getId());
        // 审批通过
        if (backdown.getAuditstatus() == 1) {
            // 修改床铺记录，对应床铺状态改为空闲
            Customer cs = customerService.getById(bd.getCustomerId());
            Bed bed = new Bed();
            bed.setId(cs.getBedId());
            bed.setBedStatus(1);
            bedService.updateById(bed);
        }
        return backdownService.examineBackdown(backdown);
    }

    @Operation(summary = "撒回退住申请")
    @PostMapping("/delBackdown")
    public ResultVo<Void> delBackdown(@RequestBody Integer id) throws Exception {
        UpdateWrapper<Backdown> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("is_deleted", 1);
        backdownService.update(updateWrapper);
        return ResultVo.ok("撒回成功");
    }
}
