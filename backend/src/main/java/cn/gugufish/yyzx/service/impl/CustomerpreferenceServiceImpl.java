package cn.gugufish.yyzx.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.gugufish.yyzx.dto.CustomerPreferenceDTO;
import cn.gugufish.yyzx.mapper.CustomerpreferenceMapper;
import cn.gugufish.yyzx.pojo.Customerpreference;
import cn.gugufish.yyzx.service.CustomerpreferenceService;
import cn.gugufish.yyzx.utils.ResultVo;
import cn.gugufish.yyzx.vo.CustomerPreferenceVo;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

@Service
public class CustomerpreferenceServiceImpl extends ServiceImpl<CustomerpreferenceMapper, Customerpreference> implements CustomerpreferenceService {
    @Resource
    private CustomerpreferenceMapper customerpreferenceMapper;

    @Override
    public ResultVo<Page<CustomerPreferenceVo>> ListCustomerpreferenceVoPage(CustomerPreferenceDTO customerPreferenceDTO) throws Exception {
        Page<CustomerPreferenceVo> page = new Page<>(customerPreferenceDTO.getPageSize(), 6);
        customerpreferenceMapper.selectCustomerPreferenceVo(page, customerPreferenceDTO.getCustomerName());
        return ResultVo.ok(page);
    }
}