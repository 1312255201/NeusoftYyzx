package cn.gugufish.yyzx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.gugufish.yyzx.dto.KhxxDTO;
import cn.gugufish.yyzx.pojo.Customer;
import cn.gugufish.yyzx.utils.ResultVo;
import cn.gugufish.yyzx.vo.KhxxCustomerVo;

public interface CustomerService extends IService<Customer> {

    ResultVo addCustomer(Customer customer) throws Exception;

    ResultVo<Page<KhxxCustomerVo>> khxxFindCustomer(KhxxDTO khxxDTO) throws Exception;

    ResultVo removeCustomer(Integer id, Integer bedId) throws Exception;

    ResultVo editCustomer(Customer customer) throws Exception;
}