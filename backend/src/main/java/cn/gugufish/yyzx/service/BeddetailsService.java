package cn.gugufish.yyzx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.gugufish.yyzx.dto.BedDetailsDTO;
import cn.gugufish.yyzx.dto.ExchangeDTO;
import cn.gugufish.yyzx.pojo.Beddetails;
import cn.gugufish.yyzx.utils.ResultVo;
import cn.gugufish.yyzx.vo.BedDetailsVo;

public interface BeddetailsService extends IService<Beddetails> {

    ResultVo<Page<BedDetailsVo>> listBedDetailsVoPage(BedDetailsDTO bedDetailsDTO) throws Exception;

    ResultVo<Void> exchangeBed(ExchangeDTO exchangeDTO) throws Exception;
}
