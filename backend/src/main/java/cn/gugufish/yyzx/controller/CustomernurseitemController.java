package cn.gugufish.yyzx.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.gugufish.yyzx.dto.CustomerNurseItemDTO;
import cn.gugufish.yyzx.pojo.Customernurseitem;
import cn.gugufish.yyzx.service.CustomernurseitemService;
import cn.gugufish.yyzx.utils.ResultVo;
import cn.gugufish.yyzx.vo.CustomerNurseItemVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/customernurseitem")
@CrossOrigin
@Tag(name = "服务关注管理")
public class CustomernurseitemController {

    @Resource
    private CustomernurseitemService customernurseitemService;

    @Operation(summary = "为顾客单个/批量添加护理项目")
    @PostMapping("/addItemToCustomer/{customerId}")
    public ResultVo addItemToCustomer(@PathVariable Integer customerId, @RequestBody List<Customernurseitem> customernurseitems) throws Exception {
        return customernurseitemService.addItemToCustomer(customerId, customernurseitems);
    }

    @Operation(summary = "移除客户护理级别级联移除用户当前级别的护理项目")
    @GetMapping("/removeCustomerLevelAndItem")
    @Parameters({
            @Parameter(name = "levelId", description = "护理级别编号", required = true),
            @Parameter(name = "customerId", description = "用户编号", required = true)
    })
    public ResultVo removeCustomerLevelAndItem(Integer levelId, Integer customerId) throws Exception {
        return customernurseitemService.removeCustomerLevelAndItem(levelId, customerId);
    }

    @Operation(summary = "查询某个顾客的护理项目列表-分页")
    @GetMapping("/listCustomerItem")
    public ResultVo<Page<CustomerNurseItemVo>> listCustomerItem(CustomerNurseItemDTO customerNurseItemDTO) throws Exception {
        return customernurseitemService.listCustomerItem(customerNurseItemDTO);
    }

    @Operation(summary = "客户续费")
    @PostMapping("/updateNurseItem")
    public ResultVo updateNurseItem(Customernurseitem Customernurseitem) throws Exception {
        customernurseitemService.updateById(Customernurseitem);
        return ResultVo.ok("续费成功");
    }

    @Operation(summary = "判断用户是否已经配置了某个指定项目")
    @GetMapping("/isIncludesItemCustomer")
    @Parameters({
            @Parameter(name = "customerId", description = "用户编号", required = true),
            @Parameter(name = "itemId", description = "护理项目编号", required = true)
    })
    public ResultVo isIncludesItemCustomer(Integer customerId, Integer itemId) throws Exception {
        QueryWrapper qw = new QueryWrapper();
        qw.eq("item_id", itemId);
        qw.eq("customer_id", customerId);
        qw.eq("is_deleted", 0);
        long row = customernurseitemService.count(qw);
        if (row > 0) {
            return ResultVo.fail("当前用户已存在该项目");
        }
        return ResultVo.ok("");

    }

    @Operation(summary = "移除客户护理项目")
    @GetMapping("/removeCustomerItem")
    @Parameters({
            @Parameter(name = "id", description = "主键key", required = true)
    })
    public ResultVo removeCustomerItem(Integer id) throws Exception {
        Customernurseitem customernurseitem = new Customernurseitem();
        customernurseitem.setIsDeleted(1);
        customernurseitem.setId(id);
        customernurseitemService.updateById(customernurseitem);
        return ResultVo.ok("移除成功");
    }
}
