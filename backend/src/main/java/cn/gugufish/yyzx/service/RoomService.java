package cn.gugufish.yyzx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.gugufish.yyzx.pojo.Room;
import cn.gugufish.yyzx.utils.ResultVo;
import cn.gugufish.yyzx.vo.CwsyBedVo;

public interface RoomService extends IService<Room> {
    public ResultVo<CwsyBedVo> findCwsyBedVo(String floor) throws Exception;
}