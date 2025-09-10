package cn.gugufish.yyzx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.gugufish.yyzx.dto.CustomerNurseItemDTO;
import cn.gugufish.yyzx.pojo.Customernurseitem;
import cn.gugufish.yyzx.utils.ResultVo;
import cn.gugufish.yyzx.vo.CustomerNurseItemVo;

import java.util.List;

public interface CustomernurseitemService extends IService<Customernurseitem> {
    ResultVo addItemToCustomer(Integer customerId, List<Customernurseitem> customernurseitems) throws Exception;

    ResultVo removeCustomerLevelAndItem(Integer levelId, Integer customerId) throws Exception;

    ResultVo<Page<CustomerNurseItemVo>> listCustomerItem(CustomerNurseItemDTO customerNurseItemDTO) throws Exception;
}