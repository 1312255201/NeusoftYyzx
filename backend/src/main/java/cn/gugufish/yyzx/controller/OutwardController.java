package cn.gugufish.yyzx.controller;

import cn.gugufish.yyzx.pojo.Bed;
import cn.gugufish.yyzx.pojo.Customer;
import cn.gugufish.yyzx.pojo.vo.OutwardVo;
import cn.gugufish.yyzx.service.BedService;
import cn.gugufish.yyzx.service.CustomerService;
import cn.gugufish.yyzx.service.UserService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import cn.gugufish.yyzx.pojo.dto.OutwardDTO;
import cn.gugufish.yyzx.pojo.Outward;
import cn.gugufish.yyzx.service.OutwardService;
import cn.gugufish.yyzx.utils.ResultVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/outward")
@CrossOrigin
@Tag(name = "外出管理")
public class OutwardController {

    @Resource
    OutwardService outwardService;
    @Resource
    BedService bedService;
    @Resource
    CustomerService customerService;

    @Operation(summary = "查询外出记录")
    @PostMapping("/queryOutwardVo")
    public ResultVo<Page<OutwardVo>> queryOutwardVo(@RequestBody OutwardDTO outwardDTO) throws Exception {
        return outwardService.queryOutwardVo(outwardDTO);
    }

    @Operation(summary = "添加外出审批")
    @PostMapping("/addOutward")
    public ResultVo<Void> addOutward(@RequestBody Outward outward) throws Exception {
        // 设置预期返回时间
        if (outward.getExpectedreturntime() == null) {
            java.util.Date tomorrow = new java.util.Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
            outward.setExpectedreturntime(tomorrow);
        }
        outwardService.save(outward);
        return ResultVo.ok("添加成功");
    }

    @Operation(summary = "审批外出申请")
    @PostMapping("/examineOutward")
    public ResultVo<Void> examineOutward(@RequestBody Outward outward) throws Exception {
        return outwardService.examineOutward(outward);
    }

    @Operation(summary = "撒回外出申请")
    @PostMapping("/delOutward")
    public ResultVo<Void> delOutward(@RequestBody Integer id) throws Exception {
        UpdateWrapper<Outward> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("is_deleted", 1);
        outwardService.update(updateWrapper);
        return ResultVo.ok("撒回成功");
    }

    @Operation(summary = "登记回院时间")
    @PostMapping("/updateBackTime")
    public ResultVo<Object> updateBackTime(@RequestBody Outward outward) throws Exception {
        UpdateWrapper<Outward> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", outward.getId());
        updateWrapper.set("actualreturntime", outward.getActualreturntime());

        outwardService.update(updateWrapper);
        Customer cs = customerService.getById(outward.getCustomerId());
        Bed bed = new Bed();
        bed.setId(cs.getBedId());
        bed.setBedStatus(1);
        bedService.updateById(bed);
        return ResultVo.ok("登记成功");
    }
}