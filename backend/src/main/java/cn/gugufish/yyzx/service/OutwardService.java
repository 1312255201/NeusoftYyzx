package cn.gugufish.yyzx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.gugufish.yyzx.dto.OutwardDTO;
import cn.gugufish.yyzx.pojo.Outward;
import cn.gugufish.yyzx.utils.ResultVo;
import cn.gugufish.yyzx.vo.OutwardVo;

public interface OutwardService extends IService<Outward> {
    ResultVo<Void> examineOutward(Outward outward) throws Exception;

    ResultVo<Page<OutwardVo>> queryOutwardVo(OutwardDTO outwardDTO) throws Exception;
}
