package cn.gugufish.yyzx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.gugufish.yyzx.dto.BackdownDTO;
import cn.gugufish.yyzx.pojo.Backdown;
import cn.gugufish.yyzx.utils.ResultVo;
import cn.gugufish.yyzx.vo.BackdownVo;

public interface BackdownService extends IService<Backdown> {
    ResultVo<Page<BackdownVo>> listBackdownVo(BackdownDTO backdownDTo) throws Exception;

    ResultVo<Void> examineBackdown(Backdown backdown) throws Exception;
}