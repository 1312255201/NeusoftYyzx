package cn.gugufish.yyzx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.gugufish.yyzx.pojo.Nursecontent;
import cn.gugufish.yyzx.utils.ResultVo;

import java.util.List;

public interface NursecontentService extends IService<Nursecontent> {
    List<Nursecontent> listNurseItemByLevel(Integer levelId) throws Exception;

    ResultVo updateNurseItem(Nursecontent nursecontent) throws Exception;

    ResultVo delNurseItem(Integer id) throws Exception;
}