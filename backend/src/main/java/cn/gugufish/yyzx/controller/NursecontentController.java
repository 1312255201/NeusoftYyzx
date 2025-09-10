package cn.gugufish.yyzx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.gugufish.yyzx.dto.NurseItemDTO;
import cn.gugufish.yyzx.pojo.Nursecontent;
import cn.gugufish.yyzx.service.NursecontentService;
import cn.gugufish.yyzx.utils.ResultVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/nursecontent")
@CrossOrigin
@Tag(name = "护理项目管理")
public class NursecontentController {

    @Resource
    private NursecontentService nursecontentservice;

    @Operation(summary = "添加护理项目")
    @PostMapping("/addNurseItem")
    public ResultVo<Void> addNurseItem(@RequestBody Nursecontent nursecontent) throws Exception {
        nursecontent.setIsDeleted(0);
        nursecontentservice.save(nursecontent);
        return ResultVo.ok("添加成功");
    }

    @Operation(summary = "查询护理项目-分页")
    @GetMapping("/findNurseItemPage")
    public ResultVo<Page<Nursecontent>> findNurseItemPage(NurseItemDTO nurseItemDTO) throws Exception {
        // 安全处理分页参数
        long current = 1; // 默认第一页
        long size = 10;    // 默认每页6条

        // 检查 DTO 是否为空（防止整个对象为 null）
        if (nurseItemDTO == null) {
            nurseItemDTO = new NurseItemDTO();
        }

        // 如果前端传递了分页参数
        if (nurseItemDTO.getPageNo() != null) {
            current = nurseItemDTO.getPageNo();
        }
        if (nurseItemDTO.getPageSize() != null) {
            size = nurseItemDTO.getPageSize();
        }

        Page<Nursecontent> page = new Page<>(current, size);

        QueryWrapper<Nursecontent> qw = new QueryWrapper<>();
        if (nurseItemDTO.getItemName() != null &&
                StringUtils.isNotBlank(nurseItemDTO.getItemName())) {
            qw.like("nursing_name", "%" + nurseItemDTO.getItemName() + "%");
        }
        if (nurseItemDTO.getStatus() != null) {
            qw.eq("status", nurseItemDTO.getStatus());
        }
        qw.eq("is_deleted", 0); // 只查询未删除数据

        nursecontentservice.page(page, qw);
        return ResultVo.ok(page);
    }

    @Operation(summary = "修改护理项目")
    @PostMapping("/updateNurseItem")
    public ResultVo<Void> updateNurseItem(@RequestBody Nursecontent nursecontent) throws Exception {
        return nursecontentservice.updateNurseItem(nursecontent);
    }

    @Operation(summary = "删除护理项目")
    @GetMapping("/delNurseItem")
    public ResultVo<Void> delNurseItem(@RequestParam Integer id) throws Exception {
        return nursecontentservice.delNurseItem(id);
    }
}