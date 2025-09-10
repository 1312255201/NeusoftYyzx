package cn.gugufish.yyzx.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.gugufish.yyzx.dto.NurseRecordDTO;
import cn.gugufish.yyzx.pojo.Nurserecord;
import cn.gugufish.yyzx.utils.ResultVo;
import cn.gugufish.yyzx.vo.NurseRecordsVo;

public interface NurserecordService extends IService<Nurserecord> {
    ResultVo addNurseRecord(Nurserecord nurserecord) throws Exception;

    ResultVo<Page<NurseRecordsVo>> queryNurseRecordsVo(NurseRecordDTO nurseRecordDTO) throws Exception;
}