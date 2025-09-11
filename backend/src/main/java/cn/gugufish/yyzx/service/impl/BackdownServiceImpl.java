package cn.gugufish.yyzx.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.gugufish.yyzx.pojo.dto.BackdownDTO;
import cn.gugufish.yyzx.mapper.BackdownMapper;
import cn.gugufish.yyzx.pojo.Backdown;
import cn.gugufish.yyzx.service.BackdownService;
import cn.gugufish.yyzx.utils.ResultVo;
import cn.gugufish.yyzx.pojo.vo.BackdownVo;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

@Service
public class BackdownServiceImpl extends ServiceImpl<BackdownMapper, Backdown> implements BackdownService {
    @Resource
    private BackdownMapper backdownMapper;

    @Override
    public ResultVo<Page<BackdownVo>> listBackdownVo(BackdownDTO backdownDTo) throws Exception {
        // 设置默认值
        Integer pageNum = backdownDTo.getPageNum() != null ? backdownDTo.getPageNum() : 1;
        Integer pageSize = backdownDTo.getPageSize() != null ? backdownDTo.getPageSize() : 10;
        
        Page<BackdownVo> page = new Page<>(pageNum, pageSize);
        backdownMapper.selectBackdownVo(page, backdownDTo.getCustomerId());
        return ResultVo.ok(page);
    }

    @Override
    public ResultVo<Void> examineBackdown(Backdown backdown) throws Exception {
        UpdateWrapper<Backdown> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", backdown.getId());
        updateWrapper.set("auditstatus", backdown.getAuditstatus());
        updateWrapper.set("audittime", backdown.getAudittime());
        updateWrapper.set("auditperson", backdown.getAuditperson());
        backdownMapper.update(backdown, updateWrapper);
        return ResultVo.ok("审批成功");
    }
}