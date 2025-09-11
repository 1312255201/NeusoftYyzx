package cn.gugufish.yyzx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.gugufish.yyzx.pojo.dto.KhxxDTO;
import cn.gugufish.yyzx.pojo.Customer;
import cn.gugufish.yyzx.utils.ResultVo;
import cn.gugufish.yyzx.pojo.vo.KhxxCustomerVo;

public interface CustomerService extends IService<Customer> {

    ResultVo<Void>  addCustomer(Customer customer) throws Exception;

    ResultVo<Page<KhxxCustomerVo>> khxxFindCustomer(KhxxDTO khxxDTO) throws Exception;

    ResultVo<Void>  removeCustomer(Integer id, Integer bedId) throws Exception;

    ResultVo<Void>  editCustomer(Customer customer) throws Exception;
}