package cn.gugufish.yyzx.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.gugufish.yyzx.pojo.dto.BedDetailsDTO;
import cn.gugufish.yyzx.pojo.dto.ExchangeDTO;
import cn.gugufish.yyzx.pojo.Beddetails;
import cn.gugufish.yyzx.service.BeddetailsService;
import cn.gugufish.yyzx.utils.ResultVo;
import cn.gugufish.yyzx.pojo.vo.BedDetailsVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/beddetails")
@Tag(name = "床位详情管理")
@CrossOrigin
public class BeddetailsController {
    @Resource
    private BeddetailsService beddetailsService;

    @GetMapping("/listBedDetailsVoPage")
    @Operation(summary = "床位详细列表动态查询（分页）")
    public ResultVo<Page<BedDetailsVo>> listBedDetailsVoPage(BedDetailsDTO bedDetailsDTO) throws Exception {
        return beddetailsService.listBedDetailsVoPage(bedDetailsDTO);
    }

    @PostMapping("/updateBedDetails")
    @Operation(summary = "更新床位使用详情-只能修改床位使用结束时间")
    public ResultVo<Void> updateBedDetails(@RequestBody Beddetails beddetails) throws Exception {
        beddetailsService.updateById(beddetails);
        return ResultVo.ok("编辑成功");
    }

    @PostMapping("/exchangeBed")
    @Operation(summary = "床位调换")
    public ResultVo<Void>  exchangeBed(@RequestBody ExchangeDTO exchangeDTO) throws Exception {
        return beddetailsService.exchangeBed(exchangeDTO);
    }

    @PostMapping("/delBedDetails")
    @Operation(summary = "删除记录")
    public ResultVo<Void> delBedDetails(@RequestBody Integer id) throws Exception {
        beddetailsService.remove(new LambdaQueryWrapper<Beddetails>().eq(Beddetails::getId, id));
        return ResultVo.ok("删除成功");
    }

    @GetMapping("/getBedPersonInfo/{bedId}")
    @Operation(summary = "根据床位ID查询床位人员信息")
    public ResultVo<BedDetailsVo> getBedPersonInfo(@PathVariable Integer bedId) throws Exception {
        return beddetailsService.getBedPersonInfoByBedId(bedId);
    }
}

