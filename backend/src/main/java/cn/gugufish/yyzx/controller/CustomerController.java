package cn.gugufish.yyzx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.gugufish.yyzx.dto.KhxxDTO;
import cn.gugufish.yyzx.pojo.Customer;
import cn.gugufish.yyzx.service.CustomerService;
import cn.gugufish.yyzx.utils.ResultVo;
import cn.gugufish.yyzx.vo.KhxxCustomerVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/customer")
@CrossOrigin
@Tag(name = "客户管理")
public class CustomerController {
    @Resource
    private CustomerService customerService;

    @Operation(summary = "入住登记")
    @PostMapping("/rzdj")
    public ResultVo addCustomer(Customer customer) throws Exception {
        return customerService.addCustomer(customer);
    }

    @Operation(summary = "客户信息动态查询（分页）")
    @GetMapping("/listKhxxPage")
    public ResultVo<Page<KhxxCustomerVo>> listKhxxPage(KhxxDTO khxxDTO) throws Exception {
        return customerService.khxxFindCustomer(khxxDTO);
    }

    @Operation(summary = "根据key删除")
    @GetMapping("/remove")
    public ResultVo remove(Integer id, Integer bedId) throws Exception {
        return customerService.removeCustomer(id, bedId);
    }

    @Operation(summary = "编辑客户信息")
    @PostMapping("/editKhxx")
    public ResultVo editKhxx(Customer customer) throws Exception {
        return customerService.editCustomer(customer);
    }
}