package cn.gugufish.yyzx.controller;

import cn.gugufish.yyzx.vo.OutwardVo;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import cn.gugufish.yyzx.dto.OutwardDTO;
import cn.gugufish.yyzx.pojo.Outward;
import cn.gugufish.yyzx.service.OutwardService;
import cn.gugufish.yyzx.utils.ResultVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/outward")
@CrossOrigin
@Tag(name = "外出管理")
public class OutwardController {

    @Resource
    private OutwardService outwardService;

    @Operation(summary = "查询外出记录")
    @PostMapping("/queryOutwardVo")
    public ResultVo<Page<OutwardVo>> queryOutwardVo(@ModelAttribute OutwardDTO outwardDTO) throws Exception {
        return outwardService.queryOutwardVo(outwardDTO);
    }

    @Operation(summary = "添加外出审批")
    @PostMapping("/addOutward")
    public ResultVo<Void> addOutward(@RequestBody Outward outward) throws Exception {
        // 设置预期返回时间
        if (outward.getExpectedreturntime() == null) {
            // 使用 java.util.Date 类型，而不是 LocalDateTime
            java.util.Date tomorrow = new java.util.Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000);
            outward.setExpectedreturntime(tomorrow);
        }
        outwardService.save(outward);
        return ResultVo.ok("添加成功");
    }

    @Operation(summary = "审批外出申请")
    @PostMapping("/examineOutward")
    public ResultVo examineOutward(Outward outward) throws Exception {
        return outwardService.examineOutward(outward);
    }

    @Operation(summary = "撒回外出申请")
    @PostMapping("/delOutward")
    public ResultVo delOutward(Integer id) throws Exception {
        UpdateWrapper<Outward> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("is_deleted", 1);
        outwardService.update(updateWrapper);
        return ResultVo.ok("撒回成功");
    }

    @Operation(summary = "登记回院时间")
    @PostMapping("/updateBackTime")
    public ResultVo updateBackTime(Outward outward) throws Exception {
        UpdateWrapper<Outward> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", outward.getId());
        updateWrapper.set("actual_return_time", outward.getActualreturntime());
        outwardService.update(updateWrapper);
        return ResultVo.ok("登记成功");
    }
}