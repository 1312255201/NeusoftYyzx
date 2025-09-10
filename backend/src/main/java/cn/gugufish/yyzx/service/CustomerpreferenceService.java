package cn.gugufish.yyzx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.gugufish.yyzx.dto.CustomerPreferenceDTO;
import cn.gugufish.yyzx.pojo.Customerpreference;
import cn.gugufish.yyzx.utils.ResultVo;
import cn.gugufish.yyzx.vo.CustomerPreferenceVo;

public interface CustomerpreferenceService extends IService<Customerpreference> {
    ResultVo<Page<CustomerPreferenceVo>> ListCustomerpreferenceVoPage(CustomerPreferenceDTO customerPreferenceDTO) throws Exception;
}