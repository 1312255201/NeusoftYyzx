package cn.gugufish.yyzx.service.impl;

import cn.gugufish.yyzx.mapper.BedMapper;
import cn.gugufish.yyzx.pojo.Bed;
import cn.gugufish.yyzx.pojo.Customer;
import cn.gugufish.yyzx.service.BedService;
import cn.gugufish.yyzx.service.CustomerService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.gugufish.yyzx.pojo.dto.OutwardDTO;
import cn.gugufish.yyzx.mapper.OutwardMapper;
import cn.gugufish.yyzx.pojo.Outward;
import cn.gugufish.yyzx.service.OutwardService;
import cn.gugufish.yyzx.utils.ResultVo;
import cn.gugufish.yyzx.pojo.vo.OutwardVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;

import java.util.Date;

@Service
public class OutwardServiceImpl extends ServiceImpl<OutwardMapper, Outward> implements OutwardService {
    @Resource
    private OutwardMapper outwardMapper;
    @Resource
    BedService bedService;
    @Resource
    CustomerService customerService;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultVo<Void> examineOutward(Outward outward,Integer userId) throws Exception {
        Outward outward1 = outwardMapper.selectById(outward.getId());
        outward1.setAuditperson(userId.toString()  );
        outward1.setAudittime(new Date());
        outward1.setAuditstatus(outward.getAuditstatus());
        outwardMapper.updateById(outward1);
        Customer cs = customerService.getById(outward1.getCustomerId());
        Bed bed = new Bed();
        bed.setId(cs.getBedId());
        bed.setBedStatus(3);
        bedService.updateById(bed);
        return ResultVo.ok("审批成功");
    }

    @Override
    public ResultVo<Page<OutwardVo>> queryOutwardVo(OutwardDTO outwardDTO) throws Exception {
        // 1. 检查参数是否为null
        if (outwardDTO == null) {
            return ResultVo.fail("查询参数不能为空");
        }

        // 2. 处理分页参数
        Integer pageNum = outwardDTO.getPageNum();
        if (pageNum == null || pageNum <= 0) {
            pageNum = 1; // 设置默认页码
        }
        
        Integer pageSize = outwardDTO.getPageSize();
        if (pageSize == null || pageSize <= 0) {
            pageSize = 10; // 设置默认分页大小
        }

        // 3. 处理userId参数
        Integer userId = outwardDTO.getUserId();
        Integer customerId= outwardDTO.getCustomerId();
        // 4. 创建分页对象
        Page<OutwardVo> page = new Page<>(pageNum, pageSize);

        // 5. 调用Mapper方法
        outwardMapper.selectOutwardVo(page, customerId,userId);

        return ResultVo.ok(page);
    }
}