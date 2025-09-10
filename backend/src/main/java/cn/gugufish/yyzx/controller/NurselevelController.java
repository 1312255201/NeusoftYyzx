package cn.gugufish.yyzx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.gugufish.yyzx.pojo.Nursecontent;
import cn.gugufish.yyzx.pojo.Nurselevel;
import cn.gugufish.yyzx.pojo.Nurselevelitem;
import cn.gugufish.yyzx.service.NursecontentService;
import cn.gugufish.yyzx.service.NurselevelService;
import cn.gugufish.yyzx.service.NurselevelitemService;
import cn.gugufish.yyzx.utils.ResultVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nurselevel")
@Tag(name = "护理级别管理")
@CrossOrigin
public class NurselevelController {
    @Autowired
    private NurselevelService nurselevelService;

    @Autowired
    private NurselevelitemService nurselevelitemService;

    @Autowired
    private NursecontentService nursecontentService;

    @Operation(summary = "添加护理级别")
    @PostMapping("/addNurseLevel")
    public ResultVo addNurseLevel(@RequestBody Nurselevel nurselevel) throws Exception {
        nurselevelService.save(nurselevel);
        return ResultVo.ok("添加护理级别成功");
    }

    @Operation(summary = "更新护理级别")
    @PostMapping("/updateNurseLevel")
    public ResultVo updateNurseLevel(@RequestBody Nurselevel nurselevel) throws Exception {
        nurselevelService.updateById(nurselevel);
        return ResultVo.ok("更新护理级别成功");
    }

    @Operation(summary = "删除护理级别")
    @GetMapping("/removeNurseLevel")
    public ResultVo removeNurseLevel(Integer id) throws Exception {
        nurselevelService.removeById(id);
        return ResultVo.ok("删除护理级别成功");
    }

    @Operation(summary = "查询护理级别列表（分页）")
    @GetMapping("/listNurseLevel")
    public ResultVo listNurseLevel(Nurselevel nurselevel,
                                   @RequestParam(defaultValue = "1") int pageNo,
                                   @RequestParam(defaultValue = "9999") int pageSize) throws Exception {
        QueryWrapper<Nurselevel> qw = new QueryWrapper<>();
        if (nurselevel.getLevelStatus() != null) {
            qw.eq("level_status", nurselevel.getLevelStatus());
        }
        if (nurselevel.getLevelName() != null && !nurselevel.getLevelName().isEmpty()) {
            qw.like("level_name", nurselevel.getLevelName());
        }

        Page<Nurselevel> page = new Page<>(pageNo, pageSize);
        nurselevelService.page(page, qw);

        return ResultVo.ok(page);
    }

    @Operation(summary = "根据护理级别查询护理项目-不分页")
    @GetMapping("/listNurseItemByLevel")
    public ResultVo<List<Nursecontent>> listNurseItemByLevel(Integer levelId) throws Exception {
        return ResultVo.ok(nursecontentService.listNurseItemByLevel(levelId));
    }

    @Operation(summary = "护理项目的配置")
    @PostMapping("/addItemToLevel")
    public ResultVo addItemToLevel(@RequestBody Nurselevelitem nurselevelitem) throws Exception {
        // 判断当前级别是否存在相同的护理项目
        QueryWrapper<Nurselevelitem> qw = new QueryWrapper<>();
        qw.eq("level_id", nurselevelitem.getLevelId());
        qw.eq("item_id", nurselevelitem.getItemId());
        long row = nurselevelitemService.count(qw);

        if (row > 0) {
            return ResultVo.fail("当前级别已存在相同项目");
        }

        nurselevelitemService.save(nurselevelitem);
        return ResultVo.ok("添加成功");
    }

    @Operation(summary = "删除护理级别的护理项目")
    @GetMapping("/removeNurseLevelItem")
    public ResultVo removeNurseLevelItem(Integer levelId, Integer itemId) throws Exception {
        QueryWrapper<Nurselevelitem> qw = new QueryWrapper<>();
        qw.eq("level_id", levelId);
        qw.eq("item_id", itemId);
        nurselevelitemService.remove(qw);
        return ResultVo.ok("删除成功");
    }
}