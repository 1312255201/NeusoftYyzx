package cn.gugufish.yyzx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.gugufish.yyzx.dto.CustomerPreferenceDTO;
import cn.gugufish.yyzx.pojo.Customerpreference;
import cn.gugufish.yyzx.service.CustomerpreferenceService;
import cn.gugufish.yyzx.utils.ResultVo;
import cn.gugufish.yyzx.vo.CustomerPreferenceVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/customerpreference")
@CrossOrigin
@Tag(name = "客户喜好管理")
public class CustomerpreferenceController {

    @Resource
    private CustomerpreferenceService customerpreferenceService;

    @PostMapping("/addCustomerpreference")
    @Operation(summary = "为顾客单个添加喜好")
    public ResultVo<Void>  addCustomerpreference(@RequestBody Customerpreference customerpreference) throws Exception {
        customerpreferenceService.save(customerpreference);
        return ResultVo.ok("添加顾客喜好");
    }

    @Operation(summary = "更新顾客喜好")
    @PostMapping("/updateCustomerpreference")
    public ResultVo<Void>  updateCustomerpreference(@RequestBody Customerpreference customerpreference) throws Exception {
        customerpreferenceService.updateById(customerpreference);
        return ResultVo.ok("更新顾客喜好");
    }

    @Operation(summary = "删除顾客喜好")
    @GetMapping("/removeCustomerpreference")
    public ResultVo<Void>  removeCustomerpreference(Integer id) throws Exception {
        customerpreferenceService.removeById(id);
        return ResultVo.ok("删除顾客喜好");
    }

    @GetMapping("/listCustomerpreferencePage")
    @Operation(summary = "顾客喜好查询（分页）/可以根据顾客姓名查询")
    public ResultVo<Page<CustomerPreferenceVo>> listCustomerpreferencePage(CustomerPreferenceDTO customerPreferenceDTO) throws Exception {
        return customerpreferenceService.ListCustomerpreferenceVoPage(customerPreferenceDTO);
    }
}
