package cn.gugufish.yyzx.controller;

import cn.gugufish.yyzx.pojo.Room;
import cn.gugufish.yyzx.service.RoomService;
import cn.gugufish.yyzx.utils.ResultVo;
import cn.gugufish.yyzx.vo.CwsyBedVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/room")
@Tag(name = "房间管理")
@CrossOrigin
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/findCwsyBedVo")
    @Operation(summary = "查询床位示意视图数据")
    @Parameters({
            @Parameter(name = "floor", description = "楼层", required = true)
    })
    public ResultVo<CwsyBedVo> findCwsyBedVo(String floor) throws Exception {
        return roomService.findCwsyBedVo(floor);
    }

    @GetMapping("/listRoom")
    @Operation(summary = "查询房间列表")
    public ResultVo<List<Room>> listRoom() {
        return ResultVo.ok(roomService.list());
    }
}
